package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.AbstractController;

public class EventLoginApp {
	
	private AbstractController newController;
	
	public EventLoginApp(AbstractController newController) {
		this.newController = newController;
	}

	protected AbstractController getNewController() {
		return newController;
	}
	
	
}
