package br.com.dsg.principal.view.componente;

import br.com.dsg.swing.controller.action.AbstractAction;
import br.com.dsg.swing.tela.layout.AbsoluteConstraints;
import br.com.dsg.swing.tela.layout.AbsoluteLayout;
import br.com.dsg.swing.util.Constantes;

public class NomeSistema extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1923843665779481525L;
	private javax.swing.JLabel jLabel = new javax.swing.JLabel();
	
	
	public NomeSistema(String nome, int fonteSize) {
		
		setName(nome);
		
		setBackground(Constantes.COR_FUNDO_ITEM_MENU);
		setLayout(new AbsoluteLayout());
		
		jLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel.setForeground(Constantes.COR_FUNDO_LABEL_ITEM_MENU);
        jLabel.setText(nome);
		
        add(jLabel,new AbsoluteConstraints(0, 0, Constantes.LARGURA_MENU_ABERTO, -1));
		
		
		

	}

	
}
