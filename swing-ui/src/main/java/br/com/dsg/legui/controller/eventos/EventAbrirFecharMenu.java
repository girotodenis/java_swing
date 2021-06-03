package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.componentes.LeGuiView;

public class EventAbrirFecharMenu {
	
	private LeGuiView principal;
	private Boolean fechar;

	public EventAbrirFecharMenu(LeGuiView principal, Boolean fechar) {
		this.principal = principal;
		this.fechar = fechar;
	}
	
	public EventAbrirFecharMenu(LeGuiView principal) {
		this(principal, null);
	}

	protected LeGuiView getPrincipal() {
		return principal;
	}

	protected Boolean getFechar() {
		return fechar;
	}
	
}
