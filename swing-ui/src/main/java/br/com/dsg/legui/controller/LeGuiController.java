package br.com.dsg.legui.controller;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import br.com.dsg.legui.componentes.ItemMenu;
import br.com.dsg.legui.componentes.LeGuiView;
import br.com.dsg.legui.controller.eventos.EventAbrirFecharMenu;
import br.com.dsg.legui.controller.eventos.EventAtualizarConteudoEvento;
import br.com.dsg.legui.controller.eventos.EventProgressBar;
import br.com.dsg.legui.controller.eventos.EventVoltarController;
import br.com.dsg.legui.controller.eventos.ListenerEventAbrirFecharMenu;
import br.com.dsg.legui.controller.eventos.ListenerEventAtualizarConteudo;
import br.com.dsg.legui.controller.eventos.ListenerEventProgressBar;
import br.com.dsg.legui.controller.eventos.ListenerEventVoltarController;

/**
 * @author Denis Giroto
 *
 */
public class LeGuiController extends AbstractController<LeGuiView> {

	private final static Logger LOG = Logger.getLogger(LeGuiController.class);
	
	private Boolean loadMenuFechado = Boolean.FALSE;

	/**
	 * 
	 */
	public LeGuiController(LeGuiView leGuiView) {
		super(leGuiView);
		
		registerControllerEventListener(EventAbrirFecharMenu.class, new ListenerEventAbrirFecharMenu(this, getPanel()));
		registerControllerEventListener(EventAtualizarConteudoEvento.class, new ListenerEventAtualizarConteudo(this, getPanel()));
		registerControllerEventListener(EventVoltarController.class, new ListenerEventVoltarController(this, getPanel()));
		registerControllerEventListener(EventProgressBar.class, new ListenerEventProgressBar(this, getPanel()));
		loadMenuFechado();
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
					fireEvent(new EventAtualizarConteudoEvento(controllerMenu.getNomeController(), controllerMenu));
				}
			};
			action.executar(this);
		});
		if (inicializar) {
			getPanel().getMenu().seleciona(item);
			fireEvent(new EventAtualizarConteudoEvento(nome, cController.criar(LeGuiController.this)));
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
							fireEvent(new EventProgressBar(i));
						} catch (Exception e) {}
					}
				}
			});
			action.executar(this);
		});
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
					fireEvent(new EventAbrirFecharMenu(LeGuiController.this.loadMenuFechado));
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
					fireEvent(new EventAbrirFecharMenu());
				} catch (Exception e) {
				}
			}
		});
		return this;
	}

	public void fecharMenu() {
		this.loadMenuFechado = Boolean.FALSE;
	}

}
