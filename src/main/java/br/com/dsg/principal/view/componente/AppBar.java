package br.com.dsg.principal.view.componente;

import br.com.dsg.swing.util.Constantes;

public class AppBar extends javax.swing.JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5615207212602152683L;

	public AppBar() {
		setBackground(Constantes.COR_FUNDO_APP_BAR);
	}
	
	
	public void atualizar() {
		this.invalidate();
		this.validate();
		this.repaint();
	}

}
