package br.com.dsg.principal.view.componente;

import javax.swing.JLabel;

import br.com.dsg.swing.tela.layout.AbsoluteConstraints;
import br.com.dsg.swing.tela.layout.AbsoluteLayout;
import br.com.dsg.swing.util.Constantes;
import br.com.dsg.swing.util.PropertiesUtil;

public class AppBar extends javax.swing.JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5615207212602152683L;

	public AppBar() {
		setBackground(Constantes.COR_FUNDO_APP_BAR);
		setLayout(new AbsoluteLayout());
		
		JLabel jLabel = new JLabel(PropertiesUtil.get("sistema"));
		jLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
		jLabel.setForeground(new java.awt.Color(255, 255, 255));
		add(jLabel, new AbsoluteConstraints(0, 0, 180, Constantes.ALTURA_APP_BAR));
	}
	
	
	public void atualizar() {
		this.invalidate();
		this.validate();
		this.repaint();
	}

}
