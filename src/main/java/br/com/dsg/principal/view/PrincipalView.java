/**
 * 
 */
package br.com.dsg.principal.view;

import javax.swing.JFrame;

import br.com.dsg.principal.view.componente.AppBar;
import br.com.dsg.swing.tela.layout.AbsoluteConstraints;
import br.com.dsg.swing.tela.layout.AbsoluteLayout;
import br.com.dsg.swing.util.Constantes;

/**
 * @author Denis Giroto
 *
 */
public class PrincipalView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4304807636437465026L;
	
	private AppBar appBar;
	
	private java.awt.Button botao;
	private javax.swing.JPanel target;
	/**
	 * 
	 */
	public PrincipalView() {
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(Constantes.COR_FUNDO_APP);
		setLocationByPlatform(true);//centralizar
		setUndecorated(true);//retirar barra
		getContentPane().setLayout(new AbsoluteLayout());
		setResizable(false);
		setSize(Constantes.LARGURA_APP, Constantes.ALTURA_APP);
//		
		appBar = new AppBar();
		getContentPane().add(appBar, new AbsoluteConstraints(Constantes.LARGURA_MENU_ABERTO, 0, Constantes.LARGURA_APP_BAR, Constantes.ALTURA_APP_BAR));
        
		
		
		
		
		
        botao = new java.awt.Button();
        botao.setName("botao01");
        botao.setLabel("Acao");
        getContentPane().add(botao, new AbsoluteConstraints(100, 80, 100, 40));
        
        
        target = new javax.swing.JPanel();
        target.setBackground(new java.awt.Color(23, 35, 51));
        getContentPane().add(target, new AbsoluteConstraints(400, 80, 100, 100));
	}
	
	
	public java.awt.Button getBotao() {
		return botao;
	}
	
	public javax.swing.JPanel getTarget() {
		return target;
	}


	public void atualizar() {
		this.invalidate();
		this.validate();
		this.repaint();
	}


	public AppBar getAppBar() {
		return appBar;
	}

	

}
