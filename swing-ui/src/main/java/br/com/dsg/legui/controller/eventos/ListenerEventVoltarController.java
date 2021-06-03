package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.componentes.LeGuiView;
import br.com.dsg.legui.controller.AbstractController;
import br.com.dsg.legui.controller.ControllerEventListener;
import br.com.dsg.legui.controller.LeGuiController;

public class ListenerEventVoltarController implements ControllerEventListener<EventVoltarController> {

	private LeGuiController LeGuiController;
	private LeGuiView leGuiView;
	
	public ListenerEventVoltarController(LeGuiController leGuiController,
			LeGuiView leGuiView) {
		super();
		LeGuiController = leGuiController;
		this.leGuiView = leGuiView;
	}
	
	@Override
	public void handleEvent(EventVoltarController event) {
		AbstractController<?> controllerAtual = event.getController();
		AbstractController<?> controllerAnterior = event.getController().getControllerPai();

		if(controllerAnterior!=null && controllerAnterior.getPanel()!=null ){
			controllerAnterior.remove(controllerAtual);
			LeGuiController.fireEvent(new EventAtualizarConteudoEvento(controllerAnterior));
		}
	}
}
