package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.EventoController;
import br.com.dsg.legui.controller.LeGuiController;

public class EventPrincipalController extends EventoController<LeGuiController>{

	private AbstractController<?> controllerAtual;
	
	public EventPrincipalController(AbstractController<?> controllerAtual) {
		super(LeGuiController.get());
		this.controllerAtual = controllerAtual;
	}

	public AbstractController<?> getControllerAtual() {
		return controllerAtual;
	}

	

}
