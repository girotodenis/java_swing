package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.componentes.LeGuiView;

public class EventAbrirFecharMenu {
	
	private LeGuiView principal;

	public EventAbrirFecharMenu(LeGuiView principal) {
		this.principal = principal;
	}

	protected LeGuiView getPrincipal() {
		return principal;
	}

	
}
