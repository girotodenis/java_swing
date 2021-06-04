package br.com.dsg.legui.componentes.eventos;

import java.util.List;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.event.MouseClickEvent;
import org.liquidengine.legui.event.MouseClickEvent.MouseClickAction;
import org.liquidengine.legui.listener.EventListener;

import br.com.dsg.legui.controller.AbstractController;
import br.com.dsg.legui.controller.Action;

public class ActionClickListener implements EventListener<MouseClickEvent> {

	private static Logger LOG = Logger.getLogger(AbstractController.class);
	
	private AbstractController<?> controller;
	private Component source;
	private Action action;
	
	public ActionClickListener(AbstractController<?> controller, Component source, Action action) {
		this.source = source;
		this.action = action;
		this.controller = controller;
	}
	
	@Override
	public void process(MouseClickEvent event) {
		if(event.getAction().equals(MouseClickAction.CLICK)) {
			source.setEnabled(false);
			source.setPressed(true);
			List<EventListener<MouseClickEvent>> eventListeners =  source.getListenerMap().getListeners(MouseClickEvent.class);
			source.getListenerMap().removeAllListeners(MouseClickEvent.class);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try {
						
						LOG.info(String.format("Executando click %s ", source.toString() ));
						action.executar(event);
						source.setEnabled(true);
						source.setPressed(false);
						
						for (EventListener<MouseClickEvent> eventListener : eventListeners) {
							source.getListenerMap().addListener(MouseClickEvent.class, eventListener);
						}
						
					} catch (Exception e) {
						controller.handlerException(e);
					}
				}
			});
		}
	}

}
