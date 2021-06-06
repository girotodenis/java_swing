package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.EventoController;
import br.com.dsg.legui.controller.LeGuiController;

public class EventAbrirFecharMenu extends EventoController<LeGuiController>{
	
	private Boolean fechar;

	public EventAbrirFecharMenu(Boolean fechar) {
		super(LeGuiController.get());
		this.fechar = fechar;
	}
	
	public EventAbrirFecharMenu() {
		this(null);
	}

	protected Boolean getFechar() {
		return fechar;
	}
	
}
