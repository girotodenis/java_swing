package br.com.dsg.legui.controller;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.ExecutarEvento;
import br.com.dsg.legui.componentes.LeGuiView;
import br.com.dsg.legui.controller.eventos.EventAbrirFecharMenu;
import br.com.dsg.legui.controller.eventos.EventAdicionarItemMenu;
import br.com.dsg.legui.controller.eventos.EventAtualizarConteudoEvento;
import br.com.dsg.legui.controller.eventos.EventLoginApp;
import br.com.dsg.legui.controller.eventos.EventProgressBar;
import br.com.dsg.legui.controller.eventos.EventVoltarController;
import br.com.dsg.legui.controller.eventos.ListenerEventAbrirFecharMenu;
import br.com.dsg.legui.controller.eventos.ListenerEventAdicionarItemMenu;
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
	
	private Sessao<?> session = new Sessao<>(false);
	
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
		registerControllerEventListener(EventAdicionarItemMenu.class, new ListenerEventAdicionarItemMenu());
		
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
		
		ExecutarEvento.get().lancar(
			new EventAdicionarItemMenu( nome,  imageA,  imageB, imageHorizontalAlignRIGHT,  cController,  inicializar)
		);

		return this;
	}

	public LeGuiController addItemMenu(String nome, String imageA, String imageB, boolean imageHorizontalAlignRIGHT, boolean desabilitarSelecaoMenu, ActionMenu<LeGuiController> action) {
		ExecutarEvento.get().lancar(
			new EventAdicionarItemMenu( nome,  imageA,  imageB, imageHorizontalAlignRIGHT,  desabilitarSelecaoMenu,  action)
		);
		return this;
	}
	
	public <T extends SeguracaController<?>> LeGuiController autenticacao(GerarController<T> cController ) {
		getSession().setLoginAtivo(true);
		final SeguracaController<?> controllerSeguranca= cController.criar(LeGuiController.this);
		controllerSeguranca.setNomeController("SeguracaController_"+System.currentTimeMillis());
		registerControllerEventListener(EventLoginApp.class, new ListenerEventLoginApp( controllerSeguranca ));
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

	public void menuFechado() {
		this.loadMenuFechado = Boolean.TRUE;
	}

	public <T extends Usuario> Sessao<T> getSession() {
		return (Sessao<T>) session;
	}

	public <T extends Usuario> void setSession(Sessao<T> session) {
		this.session = session;
	}

	protected boolean isAppFinalizado() {
		return appFinalizado;
	}
	
	public void setAppFinalizado(boolean appFinalizado) {
		this.appFinalizado = appFinalizado;
	}

}
