package br.com.dsg.legui.controller.eventos;

public class EventAbrirFecharMenu {
	
	private Boolean fechar;

	public EventAbrirFecharMenu(Boolean fechar) {
		this.fechar = fechar;
	}
	
	public EventAbrirFecharMenu() {
		this(null);
	}

	protected Boolean getFechar() {
		return fechar;
	}
	
}
