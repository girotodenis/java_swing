package br.com.dsg.legui.controller.eventos;

import javax.swing.SwingUtilities;

import br.com.dsg.legui.ControllerEventListener;
import br.com.dsg.legui.ExecutarEvento;

public class ListenerEventAbrirFecharMenu implements ControllerEventListener<EventAbrirFecharMenu> {

	public ListenerEventAbrirFecharMenu() {
		super();
	}
	
	@Override
	public void handleEvent(EventAbrirFecharMenu event) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (int i = 0; i <= 100; i++) {
					try {
						Thread.sleep(1);
						
						ExecutarEvento.get().lancar(
								new EventProgressBar(i)
						).executar();
						
					} catch (Exception e) {
					}
				}
			}
		});
		
		if(event.getFechar()!=null) {
			if(event.getFechar()) {
				event.getControllerAlvo().getPanel().getMenu().encolherItens();
			}else {
				event.getControllerAlvo().getPanel().getMenu().expandirItens();
			}
		}else {
			boolean abrir = !event.getControllerAlvo().getPanel().getMenu().isAberto();
			if (abrir) {
				event.getControllerAlvo().getPanel().getMenu().expandirItens();
			} else {
				event.getControllerAlvo().getPanel().getMenu().encolherItens();
			}
		}
	}
}
