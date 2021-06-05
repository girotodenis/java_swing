package br.com.dsg.legui.controller.seguranca;

public class Sessao<T extends Usuario> {
	
	private boolean loginAtivo;
	private boolean autenticado;
	private T usuario;
	
	public Sessao(boolean autenticado) {
		this.autenticado = autenticado;
		this.loginAtivo = autenticado;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public T getUsuario() {
		return usuario;
	}
	
	public void setUsuario(T usuario) {
		this.usuario = usuario;
	}

	public boolean isLoginAtivo() {
		return loginAtivo;
	}

	public void setLoginAtivo(boolean loginAtivo) {
		this.loginAtivo = loginAtivo;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}
	
	

}
