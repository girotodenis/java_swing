package br.com.dsg.principal.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.dsg.swing.util.Constantes;

public class HomeView extends javax.swing.JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5615207212602152683L;
	
	private java.awt.Button botao;
	private Brick target = new Brick() ;
	
	public class Brick extends JPanel {

      Brick() {
         setBackground(Color.YELLOW);
         setBorder(BorderFactory.createLineBorder(Color.GREEN));
      }
   }

	public HomeView() {
		setBackground(Constantes.COR_FUNDO_APP_BAR);
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc01 = new GridBagConstraints();
		gbc01.anchor = GridBagConstraints.NORTHWEST;
		gbc01.gridx = GridBagConstraints.RELATIVE;
		gbc01.gridy = GridBagConstraints.RELATIVE;
		gbc01.fill = GridBagConstraints.BOTH;
		gbc01.weightx = 1.0;
		gbc01.weighty = 1.0;
		
		botao = new java.awt.Button();
		botao.setSize(10,5);
        botao.setName("botao01");
        botao.setLabel("Acao");
        
        Brick brickBotao = new Brick();
        brickBotao.setBackground(Color.pink);
        brickBotao.setLayout(new GridBagLayout());
        GridBagConstraints gbc02 = new GridBagConstraints();
        gbc02.anchor = GridBagConstraints.NORTHWEST;
        gbc02.gridx = GridBagConstraints.RELATIVE;
        gbc02.gridy = GridBagConstraints.RELATIVE;
//        gbc02.fill = GridBagConstraints.BOTH;
        brickBotao.add(botao,gbc02);
        
        target.setBackground(Color.gray);
        target.setLayout(new FlowLayout());
        JLabel jLabel = new JLabel("0");
		jLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
		jLabel.setForeground(new java.awt.Color(0, 0, 0));
		target.add(jLabel);
        
        add(brickBotao, gbc01);
        add(new Brick(), gbc01);
        add(new Brick(), gbc01);
        add(new Brick(), gbc01);
        add(new Brick(), gbc01);
        
        gbc01.gridy = 1;
    
        add(new Brick(), gbc01);
        add(new Brick(), gbc01);
        add(new Brick(), gbc01);
        add(new Brick(), gbc01);
        add(target, gbc01);
        
        gbc01.gridy = 2;
        
        add(new Brick(), gbc01);
        add(new Brick(), gbc01);
        add(new Brick(), gbc01);
        add(new Brick(), gbc01);
        add(new Brick(), gbc01);
	}
	
	
	
	public java.awt.Button getBotao() {
		return botao;
	}

	public Brick getTarget() {
		return target;
	}



	public void atualizar() {
		this.invalidate();
		this.validate();
		this.repaint();
	}

}
