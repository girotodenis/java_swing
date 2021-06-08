package br.com.dsg.legui.controller.eventos;

import javax.swing.SwingUtilities;

import br.com.dsg.legui.ControllerEventListener;
import br.com.dsg.legui.ExecutarEvento;
import br.com.dsg.legui.componentes.MenuLeGui;

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
		
		MenuLeGui menu = event.getControllerAlvo().getPanel().getMenu();
		if(event.removerMenu || menu.isEmpty()) {
			menu.esconder();
		}else {
			if(event.fechar!=null) {
				if(event.fechar) {
					menu.encolherItens();
				}else {
					menu.expandirItens();
				}
			}else {
				boolean abrir = !menu.isAberto();
				if (abrir) {
					menu.expandirItens();
				} else {
					menu.encolherItens();
				}
			}
		}
	}
}
