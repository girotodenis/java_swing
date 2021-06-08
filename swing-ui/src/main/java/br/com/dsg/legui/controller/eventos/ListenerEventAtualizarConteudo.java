package br.com.dsg.legui.controller.eventos;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.liquidengine.legui.component.Panel;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.ControllerEventListener;
import br.com.dsg.legui.ExecutarEvento;
import br.com.dsg.legui.componentes.ItemMenu;
import br.com.dsg.legui.componentes.LeGuiView;
import br.com.dsg.legui.componentes.MenuLeGui;
import br.com.dsg.legui.controller.LeGuiController;

public class ListenerEventAtualizarConteudo implements ControllerEventListener<EventAtualizarConteudoEvento> {

	private final static Logger LOG = Logger.getLogger(LeGuiController.class);
	
	public ListenerEventAtualizarConteudo() {
	}

	@Override
	public void handleEvent(EventAtualizarConteudoEvento event) {
		
		AbstractController<?> newController = event.getController();
		Panel newView = newController.getPanel();
		
		LeGuiView leGuiView = event.getControllerAlvo().getPanel();
		MenuLeGui menu = leGuiView.getMenu();
		
		if(leGuiView.getConteudoLeGui().getChildComponents().size()>0 && leGuiView.getConteudoLeGui().getChildComponents().get(0).getClass().equals(newView.getClass())){
			LOG.info(String.format(" tela {%s} já está ativa ", newView.getClass().getSimpleName() ));
			return;
		}
		
		LOG.info(String.format("Atualizar tela {%s} do controller {%s} ", newView.getClass().getSimpleName(), newController.getClass().getSimpleName()));
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (int i = 0; i <= 100; i++) {
					try {
						Thread.sleep(1);
						ExecutarEvento.get().lancar(
								new EventProgressBar(i)
						).executar();
					} catch (Exception e) {
					}
				}
			}
		});
		
		if(event.getControllerAlvo().getSession()!= null && event.getControllerAlvo().getSession().isLoginAtivo() && !event.getControllerAlvo().getSession().isAutenticado()) {
			LOG.info(String.format("indo para login volta para controller {%s} ", newController.getClass().getSimpleName()));
			
			ExecutarEvento.get().lancar(
					new EventLoginApp(newController)
			).executar();
			
			return;
		}
		
		if(menu.isEmpty()) {
			menu.esconder();
		}
		
		ItemMenu itemMenu = menu.getItem(newView.getClass());
		if(itemMenu!=null) {
			menu.seleciona(itemMenu);
		}
		
		leGuiView.addPanel(newView);
		
	}
}
