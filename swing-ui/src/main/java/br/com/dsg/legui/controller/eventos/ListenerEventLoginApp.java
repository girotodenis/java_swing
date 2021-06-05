package br.com.dsg.legui.controller.eventos;

import org.apache.log4j.Logger;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.ControllerEventListener;
import br.com.dsg.legui.componentes.LeGuiView;
import br.com.dsg.legui.controller.LeGuiController;
import br.com.dsg.legui.controller.seguranca.SeguracaController;

public class ListenerEventLoginApp implements ControllerEventListener<EventLoginApp> {

	private final static Logger LOG = Logger.getLogger(LeGuiController.class);
	
	private LeGuiController leGuiController;
	private LeGuiView leGuiView;
	private SeguracaController<?> controllerSeguranca;
	
	public ListenerEventLoginApp(LeGuiController leGuiController,
			LeGuiView leGuiView, SeguracaController<?> controllerSeguranca) {
		super();
		this.leGuiController = leGuiController;
		this.leGuiView = leGuiView;
		this.controllerSeguranca=controllerSeguranca;
	}

	@Override
	public void handleEvent(EventLoginApp event) {
		
		AbstractController newController = event.getNewController();
		controllerSeguranca.setCallback((sessao)->{
			leGuiController.setSession(sessao);
			if(sessao.isAutenticado()) {
				this.leGuiView.getMenu().exibir();
				leGuiController.fireEvent(new EventAtualizarConteudoEvento(newController));
			}
		});
		leGuiView.addPanel(controllerSeguranca.getPanel());
	}
}
