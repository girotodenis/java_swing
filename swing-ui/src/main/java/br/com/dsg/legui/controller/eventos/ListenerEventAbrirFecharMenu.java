package br.com.dsg.legui.controller.eventos;

import javax.swing.SwingUtilities;

import br.com.dsg.legui.ControllerEventListener;
import br.com.dsg.legui.componentes.LeGuiView;
import br.com.dsg.legui.controller.LeGuiController;

public class ListenerEventAbrirFecharMenu implements ControllerEventListener<EventAbrirFecharMenu> {

	private LeGuiController leGuiController;
	private LeGuiView leGuiView;
	
	public ListenerEventAbrirFecharMenu(LeGuiController leGuiController,
			LeGuiView leGuiView) {
		super();
		this.leGuiController = leGuiController;
		this.leGuiView = leGuiView;
	}
	
	@Override
	public void handleEvent(EventAbrirFecharMenu event) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (int i = 0; i <= 100; i++) {
					try {
						Thread.sleep(1);
						leGuiController.fireEvent(new EventProgressBar(i));
					} catch (Exception e) {
					}
				}
			}
		});
		
		if(event.getFechar()!=null) {
			if(event.getFechar()) {
				this.leGuiView.getMenu().encolherItens();
			}else {
				this.leGuiView.getMenu().expandirItens();
			}
		}else {
			boolean abrir = !this.leGuiView.getMenu().isAberto();
			if (abrir) {
				this.leGuiView.getMenu().expandirItens();
			} else {
				this.leGuiView.getMenu().encolherItens();
			}
		}
	}
}
