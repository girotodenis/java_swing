package br.com.dsg.legui.controller.eventos;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.liquidengine.legui.component.Dialog;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.ControllerEventListener;
import br.com.dsg.legui.ExecutarEvento;
import br.com.dsg.legui.componentes.ItemMenu;
import br.com.dsg.legui.componentes.MenuLeGui;
import br.com.dsg.legui.controller.ActionMenu;
import br.com.dsg.legui.controller.GerarController;
import br.com.dsg.legui.controller.LeGuiController;
import br.com.dsg.util.Constantes;

public class ListenerEventAdicionarItemMenu implements ControllerEventListener<EventAdicionarItemMenu> {

	private final static Logger LOG = Logger.getLogger(ListenerEventAdicionarItemMenu.class);
	
	public ListenerEventAdicionarItemMenu() {
		super();
	}
	
	@Override
	public void handleEvent(EventAdicionarItemMenu event) {
		
		LeGuiController controller = event.getControllerAlvo();
		MenuLeGui menu = event.getControllerAlvo().getPanel().getMenu();
		
		final ItemMenu item;
		if(event.action!=null) {
			item =addItemMenuAcao(controller, menu, 
					event.nome, 
					event.imageA, 
					event.imageB, 
					event.imageHorizontalAlignRIGHT, 
					event.desabilitarSelecaoMenu, 
					event.action,
					null);
			
			
		}else {
			item = addItemMenu(controller, menu, 
					event.nome, 
					event.imageA, 
					event.imageB, 
					event.imageHorizontalAlignRIGHT, 
					event.cController, 
					event.inicializar,
					null);
		}
		
		addMenuFlutuante(event, controller, menu, item);
	}

	public void addMenuFlutuante(EventAdicionarItemMenu event, LeGuiController controller, MenuLeGui menu,
			final ItemMenu item) {
		if(!event.menuFlutuante.isEmpty()) {
			controller.registerActionMouseButton2(item, (evento)->{
				
				float h = menu.hi() * event.menuFlutuante.size()+new Dialog().getTitleHeight();
				Dialog menuFrame = new Dialog("", menu.wo(), h);
				menuFrame.setCloseable(true);
				menuFrame.setDraggable(true);
				menuFrame.setResizable(false);
				MenuLeGui menuFlutuante = new MenuLeGui(
						0,0,
						menu.wo(), 
						menu.hi()*event.menuFlutuante.size(),
						menu.wo(),
						menu.wo(), 
						menu.hi(), 
						null);
				
				//menuFrame.clearChildComponents();
				menuFrame.getContainer().add(menuFlutuante);
				event.menuFlutuante.forEach(si->{
					if(si.action!=null) {
						ItemMenu itemf =addItemMenuAcao(
								controller, menuFlutuante, 
								si.nome, 
								si.imageA, 
								si.imageB, 
								si.imageHorizontalAlignRIGHT, 
								si.desabilitarSelecaoMenu, 
								si.action,
								menuFrame);
					}else {
						ItemMenu itemf = addItemMenu(controller, menuFlutuante, 
								si.nome, 
								si.imageA, 
								si.imageB, 
								si.imageHorizontalAlignRIGHT, 
								si.cController, 
								si.inicializar,
								menuFrame);
					}
				});
				menuFrame.show(menu.getFrame());
				
				menuFrame.setPosition(
						menu.wc(), 
						item.getPosition().y() + (menu.hi()/2)
						);
			});
		}
	}
	
	/**
	 * @param nome
	 * @param imageA
	 * @param cController
	 * @param inicializar
	 * @return
	 */
	public ItemMenu addItemMenu(LeGuiController controller,MenuLeGui menu, String nome, String imageA, String imageB,boolean imageHorizontalAlignRIGHT, GerarController<?> cController, Boolean inicializar,Dialog menuFrame) {
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
			if(menuFrame!=null) {
				menuFrame.close();
			}
		});
		if (inicializar) {
			menu.seleciona(item);
			
			ExecutarEvento.get().lancar(
					new EventAtualizarConteudoEvento(nome, controllerMenu)
			).executar();
			
		}
		return item;
	}

	public ItemMenu addItemMenuAcao(LeGuiController controller, MenuLeGui menu, String nome, String imageA, String imageB, boolean imageHorizontalAlignRIGHT, boolean desabilitarSelecaoMenu, ActionMenu<LeGuiController> action, Dialog menuFrame) {
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
			if(menuFrame!=null) {
				menuFrame.close();
			}
		});
		
		if(menu.isAberto()) {
			menu.expandirItens();
		}else {
			menu.encolherItens();
		}
		return item;
	}
	
}
