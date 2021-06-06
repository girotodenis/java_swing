package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.EventoController;
import br.com.dsg.legui.controller.LeGuiController;

public class EventAtualizarConteudoEvento extends EventoController<LeGuiController>{
	
	private String nome;
	private AbstractController<?> controller;
	
	public EventAtualizarConteudoEvento(String nomeItem, 
			AbstractController<?> controller) {
		super(LeGuiController.get());
		this.nome = nomeItem;
		this.controller = controller;
	}
	
	public EventAtualizarConteudoEvento(AbstractController<?> controller) {
		super(LeGuiController.get());
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
