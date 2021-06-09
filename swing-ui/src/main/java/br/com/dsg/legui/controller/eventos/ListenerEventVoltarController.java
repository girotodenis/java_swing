package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.ControllerEventListener;
import br.com.dsg.legui.ExecutarEvento;
import br.com.dsg.legui.controller.LeGuiController;

public class ListenerEventVoltarController implements ControllerEventListener<EventVoltarController> {

	public ListenerEventVoltarController() {
		super();
	}
	
	@Override
	public void handleEvent(EventVoltarController event) {
		AbstractController<?> controllerAtual = event.getController();
		AbstractController<?> controllerAnterior = event.getController().getControllerPai();
		
		if (controllerAnterior == null || controllerAnterior instanceof LeGuiController) {
			
			ExecutarEvento.get().lancar(
					new EventPrincipalController(controllerAtual)
			).executar();
			
			return;
		}
		
		if(controllerAnterior!=null && controllerAnterior.getPanel()!=null ){
			controllerAnterior.remove(controllerAtual);
			
			ExecutarEvento.get().lancar(
					new EventAtualizarConteudoEvento(controllerAnterior)
			).executar();
			
		}
	}
}
