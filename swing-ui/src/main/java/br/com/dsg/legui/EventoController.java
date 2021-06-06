package br.com.dsg.legui;

public class EventoController<E extends AbstractController<?>> {
	
	private E controllerAlvo;

	public EventoController(E controllerAlvo) {
		this.controllerAlvo = controllerAlvo;
	}
	
	public E getControllerAlvo() {
		return controllerAlvo;
	}
	
}
