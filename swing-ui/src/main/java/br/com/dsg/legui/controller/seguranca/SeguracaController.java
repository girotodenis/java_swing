package br.com.dsg.legui.controller.seguranca;

import org.liquidengine.legui.component.Panel;

import br.com.dsg.legui.AbstractController;

public abstract class SeguracaController<T extends Panel> extends AbstractController<T>{

	private Seguranca callback;
	
	public SeguracaController(AbstractController<?> controllerPai, T panel) {
		super(controllerPai, panel);
	}

	protected boolean login(String... parametros) {
		
		Usuario usuario = logar(parametros);
		Sessao sessao = new Sessao( usuario != null );
		if(sessao.isAutenticado()) {
			sessao.setUsuario(usuario);
		}
		callback.call(sessao);
		return sessao.isAutenticado();
	}
	
	protected abstract Usuario logar(String... parametros) ;

	public void setCallback(Seguranca callback) {
		this.callback = callback;
	}

	

}
