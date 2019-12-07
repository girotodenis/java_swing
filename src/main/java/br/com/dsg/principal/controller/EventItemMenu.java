package br.com.dsg.principal.controller;

import br.com.dsg.swing.controller.AbstractController;

public class EventItemMenu {
	
	private String nome;
	private AbstractController<?> controller;
	
	public EventItemMenu(String nomeItem, AbstractController<?> controller) {
		this.nome = nomeItem;
		this.controller = controller;
	}

	public String getNome() {
		return nome;
	}

	public AbstractController<?> getController() {
		return controller;
	}
	
	

}
