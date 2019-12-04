/**
 * 
 */
package br.com.dsg.swing.tela;

import javax.swing.JFrame;

import br.com.dsg.swing.tela.layout.AbsoluteLayout;

/**
 * @author Denis Giroto
 *
 */
public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4304807636437465026L;

	/**
	 * 
	 */
	public Principal() {
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));
//		
		//centralizar
        setLocationByPlatform(true);
        
        //retirar barra
        setUndecorated(true);
        getContentPane().setLayout(new AbsoluteLayout());
        
        pack();
        setSize(1000, 600);
        
	}

	

}
