package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.AbstractController;

public class EventAtualizarConteudoEvento {
	
	private String nome;
	private AbstractController<?> controller;
	
	public EventAtualizarConteudoEvento(String nomeItem, 
			AbstractController<?> controller) {
		this.nome = nomeItem;
		this.controller = controller;
	}
	
	public EventAtualizarConteudoEvento(AbstractController<?> controller) {
		this.nome = controller.getNomeController();
		this.controller = controller;
	}

	public String getNome() {
		return nome;
	}

	public AbstractController<?> getController() {
		return controller;
	}

}
