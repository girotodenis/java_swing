package br.com.dsg.principal.controller;

import br.com.dsg.principal.view.PrincipalView;
import br.com.dsg.swing.tela.layout.AbsoluteConstraints;
import br.com.dsg.swing.util.Constantes;

public class EventAbrirFecharMenu {

	public EventAbrirFecharMenu(Boolean abrir, PrincipalView principal, java.awt.event.MouseAdapter mouseAdapter) {

		if (abrir) {
			
			abrir(principal);

		} else {
			
			fechar(principal);

		}
		principal.getMenu().getBotaoMenu().addMouseListener(mouseAdapter);
	}

	private void fechar(PrincipalView principal) {
		principal.getMenu().encolheItens();
		
		int LARGURA_APP_BAR_FULL = principal.getWidth() - Constantes.LARGURA_MENU_FECHADO;
		principal.remove(principal.getPrincipalJpane());
		principal.getContentPane().add(principal.getPrincipalJpane(), 
				new AbsoluteConstraints(Constantes.LARGURA_MENU_FECHADO,
				0, LARGURA_APP_BAR_FULL, principal.getHeight()));

		principal.remove(principal.getMenu());
		principal.getContentPane().add(principal.getMenu(),
				new AbsoluteConstraints(0, 0, Constantes.LARGURA_MENU_FECHADO, principal.getHeight()));

	}

	private void abrir(PrincipalView principal) {
		principal.getMenu().expandeItens();
		
		int LARGURA_APP_BAR = principal.getWidth() - Constantes.LARGURA_MENU_ABERTO;
		principal.remove(principal.getPrincipalJpane());
		principal.getContentPane().add(principal.getPrincipalJpane(), 
				new AbsoluteConstraints(Constantes.LARGURA_MENU_ABERTO,
				0, LARGURA_APP_BAR, principal.getHeight()));
		
		principal.remove(principal.getMenu());
		principal.getContentPane().add(principal.getMenu(),
				new AbsoluteConstraints(0, 0, Constantes.LARGURA_MENU_ABERTO, principal.getHeight()));

	}

}
