/**
 * 
 */
package br.com.dsg.principal.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.dsg.principal.view.componente.AppBar;
import br.com.dsg.principal.view.componente.Menu;
import br.com.dsg.principal.view.componente.PrincipalJpanel;
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
	
	private Menu menu;
	private AppBar appBar;
	
	private PrincipalJpanel principalJpane;
	
	private java.awt.Button botao;
	private javax.swing.JPanel target;
	/**
	 * 
	 */
	public PrincipalView() {
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(Constantes.COR_FUNDO_APP);
		setLocationByPlatform(true);//centralizar
		
		getContentPane().setLayout(new AbsoluteLayout());
		setResizable(true);
		setSize(Constantes.LARGURA_APP, Constantes.ALTURA_APP);

		this.menu = new Menu();
		add(menu, new AbsoluteConstraints(0, 0, Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_APP));
		
		this.principalJpane = new PrincipalJpanel();
		getContentPane().add(principalJpane, new AbsoluteConstraints(Constantes.LARGURA_MENU_ABERTO, 0, Constantes.LARGURA_APP_BAR, Constantes.ALTURA_APP_BAR));
		atualizar();
		
//		appBar = new AppBar();
//		appBar.setSize(Constantes.LARGURA_APP_BAR, Constantes.ALTURA_APP_BAR);
//		
//		principalJpane.setBackground(Constantes.COR_FUNDO_ITEM_MENU);
//		
//		principalJpane.setLayout(new GridBagLayout());
//		GridBagConstraints gbcAppBar = new GridBagConstraints();
//		gbcAppBar.anchor = GridBagConstraints.NORTHWEST;
//		gbcAppBar.gridx = GridBagConstraints.RELATIVE;
//		gbcAppBar.gridy = GridBagConstraints.RELATIVE;
//		gbcAppBar.fill = GridBagConstraints.HORIZONTAL;
//		gbcAppBar.weightx = 1.0;
//		gbcAppBar.weighty = 1.0;
//		principalJpane.add(appBar, gbcAppBar);
		
		
        
		
		botao = new java.awt.Button();
        botao.setName("botao01");
        botao.setLabel("Acao");
        //getContentPane().add(botao, new AbsoluteConstraints(300, 80, 100, 40));
        
        
        target = new javax.swing.JPanel();
        target.setBackground(new java.awt.Color(23, 35, 51));
       // getContentPane().add(target, new AbsoluteConstraints(400, 80, 100, 100));
        
        addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		    	
		    	PrincipalView.this.remove(menu);
		    	PrincipalView.this.add(menu, new AbsoluteConstraints(0, 0, PrincipalView.this.getMenu().getWidth(), PrincipalView.this.getHeight()));
		    	
		    	PrincipalView.this.remove(principalJpane);
		    	int largura  = PrincipalView.this.getWidth() - PrincipalView.this.getMenu().getWidth();
		    	PrincipalView.this.add(principalJpane, new AbsoluteConstraints(PrincipalView.this.getMenu().getWidth(), 0, largura, PrincipalView.this.getHeight()));
		    	
		    	PrincipalView.this.atualizar();
		    	
		    }
		});
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


	public PrincipalJpanel getPrincipalJpane() {
		return principalJpane;
	}


	public Menu getMenu() {
		return menu;
	}

	public static void main(String[] args) {
		new PrincipalView().setVisible(true);
	}

}
