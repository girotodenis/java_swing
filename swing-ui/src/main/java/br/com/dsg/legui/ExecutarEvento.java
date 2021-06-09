package br.com.dsg.legui;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import br.com.dsg.legui.controller.eventos.EventAtualizarConteudoEvento;
import br.com.dsg.legui.controller.eventos.EventProgressBar;

public class ExecutarEvento {
	
	private static ExecutarEvento instance = new ExecutarEvento();
	
	private ExecutarEvento() {
	}
	
	private static Logger LOG = Logger.getLogger(ExecutarEvento.class);

	private Queue<EventoController<?>> primario = new ConcurrentLinkedQueue<>();
	private Queue<EventoController<?>> secundario = new ConcurrentLinkedQueue<>();

	public void executar() {
		swap();

		for (EventoController<?> evento = secundario.poll(); evento != null; evento = secundario.poll()) {
			AbstractController<?> targetComponent = evento.getControllerAlvo();
			if (targetComponent == null) {
				return;
			}
			List<? extends ControllerEventListener<?>> lista = targetComponent.getEventosControllers().get(evento.getClass().getName());
			if(lista!=null) {
				for (ControllerEventListener listener : lista) {
					LOG.debug( String.format("executando listener %s ", listener.getClass().getSimpleName()) );
					listener.handleEvent(evento);
				}
			}else {
				LOG.error("sem eventos");
			}
		}
	}

	private void swap() {
		Queue<EventoController<?>> temp = primario;
		primario = secundario;
		secundario = temp;
	}

	public ExecutarEvento lancar(EventoController<?> event ) {
		LOG.debug("add Evento: " + event.getClass().getSimpleName());
		primario.add(event);
		return this;
	}
	
	public static ExecutarEvento get() {
		return instance;
	}
	
}
