package br.com.dsg.principal.view;

import br.com.dsg.swing.tela.layout.AbsoluteConstraints;
import br.com.dsg.swing.util.Constantes;

public class ConfView extends javax.swing.JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5615207212602152683L;
	
	private java.awt.Button botao;

	public ConfView() {
		setBackground(Constantes.COR_FUNDO_APP);
		
		setSize(500,500);
		
		botao = new java.awt.Button();
        botao.setName("botao01");
        botao.setLabel("Acao");
        add(botao, new AbsoluteConstraints(0, 0, 100, 40));
	}
	
	
	
	public java.awt.Button getBotao() {
		return botao;
	}



	public void atualizar() {
		this.invalidate();
		this.validate();
		this.repaint();
	}

}
