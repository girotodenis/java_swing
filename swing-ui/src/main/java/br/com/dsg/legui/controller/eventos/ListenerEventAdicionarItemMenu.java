package br.com.dsg.legui.controller.eventos;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.ControllerEventListener;
import br.com.dsg.legui.ExecutarEvento;
import br.com.dsg.legui.componentes.ItemMenu;
import br.com.dsg.legui.componentes.MenuLeGui;
import br.com.dsg.legui.controller.ActionMenu;
import br.com.dsg.legui.controller.GerarController;
import br.com.dsg.legui.controller.LeGuiController;

public class ListenerEventAdicionarItemMenu implements ControllerEventListener<EventAdicionarItemMenu> {

	private final static Logger LOG = Logger.getLogger(ListenerEventAdicionarItemMenu.class);
	
	public ListenerEventAdicionarItemMenu() {
		super();
	}
	
	@Override
	public void handleEvent(EventAdicionarItemMenu event) {
		
		LeGuiController controller = event.getControllerAlvo();
		MenuLeGui menu = event.getControllerAlvo().getPanel().getMenu();
		
		if(event.action!=null) {
			addItemMenuAcao(controller, menu, 
					event.nome, 
					event.imageA, 
					event.imageB, 
					event.imageHorizontalAlignRIGHT, 
					event.desabilitarSelecaoMenu, 
					event.action);
		}else {
			addItemMenu(controller, menu, event.nome, event.imageA, event.imageB, event.imageHorizontalAlignRIGHT, event.cController, event.inicializar);
		}
	}
	
	/**
	 * @param nome
	 * @param imageA
	 * @param cController
	 * @param inicializar
	 * @return
	 */
	public void addItemMenu(LeGuiController controller,MenuLeGui menu, String nome, String imageA, String imageB,boolean imageHorizontalAlignRIGHT, GerarController<?> cController, Boolean inicializar) {
		LOG.info("menu " + nome + " criado");
		
		final AbstractController<?> controllerMenu = cController.criar(controller);
		controllerMenu.setNomeController(nome+"_menu_"+System.currentTimeMillis());
		
		ItemMenu item = menu.criarItemMenu(nome, imageA, imageB, imageHorizontalAlignRIGHT);
		item.setPanel(controllerMenu.getPanel().getClass());
		
		controller.registerAction(item, (event) -> {
			menu.seleciona(item);
			ActionMenu<LeGuiController> action = (controllerPai)->{
				
				ExecutarEvento.get().lancar(
						new EventAtualizarConteudoEvento(controllerMenu.getNomeController(), controllerMenu)
				).executar();
				
			};
					
			action.executar(controller);
		});
		if (inicializar) {
			menu.seleciona(item);
			
			ExecutarEvento.get().lancar(
					new EventAtualizarConteudoEvento(nome, controllerMenu)
			).executar();
			
		}
	}

	public void addItemMenuAcao(LeGuiController controller, MenuLeGui menu, String nome, String imageA, String imageB, boolean imageHorizontalAlignRIGHT, boolean desabilitarSelecaoMenu, ActionMenu<LeGuiController> action) {
		LOG.info("menu " + nome + " criado");
		ItemMenu item = menu.criarItemMenu(nome, imageA, imageB, imageHorizontalAlignRIGHT);
		controller.registerAction(item, (event) -> {
			
			if(!desabilitarSelecaoMenu) {
				menu.seleciona(item);
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
			action.executar(controller);
		});
		
		if(menu.isAberto()) {
			menu.expandirItens();
		}else {
			menu.encolherItens();
		}
	}
	
}
