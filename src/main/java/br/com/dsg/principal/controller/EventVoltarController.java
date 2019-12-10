package br.com.dsg.principal.controller;

import br.com.dsg.swing.controller.AbstractController;

public class EventVoltarController {

	private AbstractController<?> controller;

	public EventVoltarController(AbstractController<?> controller) {

		this.controller = controller;
	}

	public AbstractController<?> getController(){
		return controller;
	}

}
