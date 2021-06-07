package br.com.dsg.legui.controller.eventos;

//import org.apache.log4j.Logger;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.ControllerEventListener;
import br.com.dsg.legui.ExecutarEvento;
import br.com.dsg.legui.controller.seguranca.SeguracaController;

public class ListenerEventLoginApp implements ControllerEventListener<EventLoginApp> {

	//private final static Logger LOG = Logger.getLogger(LeGuiController.class);
	
	private SeguracaController<?> controllerSeguranca;
	
	public ListenerEventLoginApp(SeguracaController<?> controllerSeguranca) {
		super();
		this.controllerSeguranca=controllerSeguranca;
	}

	@Override
	public void handleEvent(EventLoginApp event) {
		
		AbstractController<?> newController = event.getNewController();
		controllerSeguranca.setCallback((sessao)->{
			event.getControllerAlvo().setSession(sessao);
			if(sessao.isAutenticado()) {
				event.getControllerAlvo().getPanel().getMenu().exibir();
				
				ExecutarEvento.get().lancar(
					new EventAtualizarConteudoEvento(newController)
				).executar();
			}
		});
		event.getControllerAlvo().getPanel().addPanel(controllerSeguranca.getPanel());
	}
}
