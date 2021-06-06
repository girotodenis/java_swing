package br.com.dsg.legui.controller;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.ExecutarEvento;
import br.com.dsg.legui.componentes.ItemMenu;
import br.com.dsg.legui.componentes.LeGuiView;
import br.com.dsg.legui.controller.eventos.EventAbrirFecharMenu;
import br.com.dsg.legui.controller.eventos.EventAtualizarConteudoEvento;
import br.com.dsg.legui.controller.eventos.EventLoginApp;
import br.com.dsg.legui.controller.eventos.EventProgressBar;
import br.com.dsg.legui.controller.eventos.EventVoltarController;
import br.com.dsg.legui.controller.eventos.ListenerEventAbrirFecharMenu;
import br.com.dsg.legui.controller.eventos.ListenerEventAtualizarConteudo;
import br.com.dsg.legui.controller.eventos.ListenerEventLoginApp;
import br.com.dsg.legui.controller.eventos.ListenerEventProgressBar;
import br.com.dsg.legui.controller.eventos.ListenerEventVoltarController;
import br.com.dsg.legui.controller.seguranca.SeguracaController;
import br.com.dsg.legui.controller.seguranca.Sessao;
import br.com.dsg.legui.controller.seguranca.Usuario;

/**
 * @author Denis Giroto
 *
 */
public class LeGuiController extends AbstractController<LeGuiView> {

	private final static Logger LOG = Logger.getLogger(LeGuiController.class);
	
	private Boolean loadMenuFechado = Boolean.FALSE;
	
	private Sessao session = new Sessao<>(false);
	
	private  boolean appFinalizado = false;
	
	private static LeGuiController instance;
	

	/**
	 * 
	 */
	protected LeGuiController(LeGuiView leGuiView) {
		super(leGuiView);
		instance = this;
		
		registerControllerEventListener(EventAbrirFecharMenu.class, new ListenerEventAbrirFecharMenu());
		registerControllerEventListener(EventAtualizarConteudoEvento.class, new ListenerEventAtualizarConteudo());
		registerControllerEventListener(EventVoltarController.class, new ListenerEventVoltarController());
		registerControllerEventListener(EventProgressBar.class, new ListenerEventProgressBar());
		loadMenuFechado();
	}

	public static LeGuiController get() {
		return instance;
	}
	
	/**
	 * @author Denis Giroto Classe utilizada para disparar um evento que atualiza a
	 *         tela Principal.
	 */
	public class AtualizarAppEvento {
	}

	/**
	 * @param nome
	 * @param imageA
	 * @param cController
	 * @param inicializar
	 * @return
	 */
	public LeGuiController addItemMenu(String nome, String imageA, String imageB,boolean imageHorizontalAlignRIGHT, GerarController cController, Boolean inicializar) {
		LOG.info("menu " + nome + " criado");
		
		final AbstractController<?> controllerMenu = cController.criar(this);
		controllerMenu.setNomeController(nome+"_menu_"+System.currentTimeMillis());
		
		ItemMenu item = getPanel().getMenu().criarItemMenu(nome, imageA, imageB, imageHorizontalAlignRIGHT);
		item.setPanel(controllerMenu.getPanel().getClass());
		
		registerAction(item, (event) -> {
			getPanel().getMenu().seleciona(item);
			ActionMenu<LeGuiController> action = new ActionMenu<LeGuiController>() {
				public void executar(LeGuiController controller) {
//					AbstractController<?> controllerMenu = cController.criar(controller);
//					controllerMenu.setNomeController(nome);
					
					ExecutarEvento.get().lancar(
						new EventAtualizarConteudoEvento(controllerMenu.getNomeController(), controllerMenu)
					).executar();
					
				}
			};
			action.executar(this);
		});
		if (inicializar) {
			getPanel().getMenu().seleciona(item);
			
			ExecutarEvento.get().lancar(
					new EventAtualizarConteudoEvento(nome, controllerMenu)
			).executar();
			
		}
		return this;
	}

	public LeGuiController addItemMenu(String nome, String imageA, String imageB, boolean imageHorizontalAlignRIGHT, boolean desabilitarSelecaoMenu, ActionMenu<LeGuiController> action) {
		LOG.info("menu " + nome + " criado");
		ItemMenu item = getPanel().getMenu().criarItemMenu(nome, imageA, imageB, imageHorizontalAlignRIGHT);
		registerAction(item, (event) -> {
			
			if(!desabilitarSelecaoMenu) {
				getPanel().getMenu().seleciona(item);
			}
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					for (int i = 0; i <= 100; i++) {
						try {
							Thread.sleep(1);
							
							ExecutarEvento.get().lancar(
									new EventProgressBar(i)
							).executar();
							
						} catch (Exception e) {}
					}
				}
			});
			action.executar(this);
		});
		return this;
	}
	
	public <T extends SeguracaController<?>> LeGuiController autenticacao(GerarController<T> cController ) {
		getSession().setLoginAtivo(true);
		final SeguracaController<?> controllerSeguranca= cController.criar(LeGuiController.this);
		controllerSeguranca.setNomeController("SeguracaController_"+System.currentTimeMillis());
		registerControllerEventListener(EventLoginApp.class, new ListenerEventLoginApp( controllerSeguranca ));
		
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Thread.sleep(100);
//					fireEvent(new EventAtualizarConteudoEvento(controllerSeguranca.getNomeController(), controllerSeguranca));
//				} catch (Exception e) {
//				}
//			}
//		});
		return this;
	}
	

	/**
	 * @return
	 */
	private LeGuiController loadMenuFechado() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(100);
					
					ExecutarEvento.get().lancar(
						new EventAbrirFecharMenu(LeGuiController.this.loadMenuFechado)
					).executar();
					
					if(LeGuiController.this.getSession()!= null && LeGuiController.this.getSession().isLoginAtivo() && !LeGuiController.this.getSession().isAutenticado()) {
						LeGuiController.this.getPanel().getMenu().esconder();
					}
						
				} catch (Exception e) {
				}
			}
		});
		return this;
	}
	
	public LeGuiController menuAbrirFecharMenu() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ExecutarEvento.get().lancar(
							new EventAbrirFecharMenu()
					).executar();
					
				} catch (Exception e) {}
			}
		});
		return this;
	}

	public void fecharMenu() {
		this.loadMenuFechado = Boolean.TRUE;
	}

	public Sessao<Usuario> getSession() {
		return session;
	}

	public void setSession(Sessao<Usuario> session) {
		this.session = session;
	}

	protected boolean isAppFinalizado() {
		return appFinalizado;
	}
	
	protected void setAppFinalizado(boolean appFinalizado) {
		this.appFinalizado = appFinalizado;
	}

}
