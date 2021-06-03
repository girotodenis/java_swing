package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.controller.ControllerEventListener;

public class ListenerEventAbrirFecharMenu implements ControllerEventListener<EventAbrirFecharMenu> {

	
	@Override
	public void handleEvent(EventAbrirFecharMenu event) {
		
		if(event.getFechar()!=null) {
			if(event.getFechar()) {
				event.getPrincipal().getMenu().encolherItens();
			}else {
				event.getPrincipal().getMenu().expandirItens();
			}
		}else {
			boolean abrir = !event.getPrincipal().getMenu().isAberto();
			if (abrir) {
				event.getPrincipal().getMenu().expandirItens();
			} else {
				event.getPrincipal().getMenu().encolherItens();
			}
		}
	}
}
