package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.ControllerEventListener;
import br.com.dsg.legui.controller.LeGuiEventos;

public class ListenerEventPrincipalController implements ControllerEventListener<EventPrincipalController> {

	
	private AbstractController<?> principal;

	public ListenerEventPrincipalController(AbstractController<?> primeiro) {
		super();
		this.principal = primeiro;
	}

	@Override
	public void handleEvent(EventPrincipalController event) {
		AbstractController<?> controllerAtual = event.getControllerAtual();
		controllerAtual.cleanUp();
		LeGuiEventos.irPara(principal);
	}
}
