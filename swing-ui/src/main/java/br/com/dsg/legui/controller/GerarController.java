package br.com.dsg.legui.controller;

import br.com.dsg.legui.AbstractController;

public interface GerarController<T extends AbstractController<?> > {

	
	T criar(AbstractController<?> controllerPai);
}
