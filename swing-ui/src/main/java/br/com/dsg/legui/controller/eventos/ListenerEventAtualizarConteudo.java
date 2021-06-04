package br.com.dsg.legui.controller.eventos;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.liquidengine.legui.component.Panel;

import br.com.dsg.legui.componentes.ItemMenu;
import br.com.dsg.legui.componentes.LeGuiView;
import br.com.dsg.legui.controller.AbstractController;
import br.com.dsg.legui.controller.ControllerEventListener;
import br.com.dsg.legui.controller.LeGuiController;

public class ListenerEventAtualizarConteudo implements ControllerEventListener<EventAtualizarConteudoEvento> {

	private final static Logger LOG = Logger.getLogger(LeGuiController.class);
	
	private LeGuiController leGuiController;
	private LeGuiView leGuiView;
	
	public ListenerEventAtualizarConteudo(LeGuiController leGuiController,
			LeGuiView leGuiView) {
		super();
		this.leGuiController = leGuiController;
		this.leGuiView = leGuiView;
	}

	@Override
	public void handleEvent(EventAtualizarConteudoEvento event) {
		
		AbstractController newController = event.getController();
		Panel newView = newController.getPanel();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (int i = 0; i <= 100; i++) {
					try {
						Thread.sleep(1);
						leGuiController.fireEvent(new EventProgressBar(i));
					} catch (Exception e) {
					}
				}
			}
		});
		
		ItemMenu itemMenu = leGuiView.getMenu().getItem(newView.getClass());
		if(itemMenu!=null) {
			itemMenu.seleciona();
			this.leGuiView.getMenu().resetOutros(itemMenu);
			//leGuiView.getMenu().resetOutros(itemMenu);
		}
		
		LOG.info(String.format("Atualizar tela {%s} do controller {%s} ", newView.getClass().getSimpleName(), newController.getClass().getSimpleName()));
		leGuiView.addPanel(newView);
	}
}
