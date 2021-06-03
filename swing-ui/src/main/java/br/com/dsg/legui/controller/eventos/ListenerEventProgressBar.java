package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.componentes.LeGuiView;
import br.com.dsg.legui.controller.AbstractController;
import br.com.dsg.legui.controller.ControllerEventListener;
import br.com.dsg.legui.controller.LeGuiController;

public class ListenerEventProgressBar implements ControllerEventListener<EventProgressBar> {

	private LeGuiController LeGuiController;
	private LeGuiView leGuiView;
	
	public ListenerEventProgressBar(LeGuiController leGuiController,
			LeGuiView leGuiView) {
		super();
		LeGuiController = leGuiController;
		this.leGuiView = leGuiView;
	}
	
	@Override
	public void handleEvent(EventProgressBar event) {
	    leGuiView.setValueProgressBar(event.getValor());
	    if(event.getValor()>=100) {
	    	leGuiView.setValueProgressBar(0);
	    }
	}
}
