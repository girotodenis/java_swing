/**
 * 
 */
package br.com.dsg.swing;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import br.com.dsg.swing.tela.Principal;
import br.com.dsg.swing.util.PropertiesUtil;

/**
 * @author Denis Giroto
 *
 */
public class Main {
	
	private final static Logger LOGGER = Logger.getLogger(Main.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		LOGGER.info(PropertiesUtil.get("sistema"));
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
        	
        	LOGGER.error(ex.getMessage(), ex);
        }
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Principal().setVisible(true);;
			}
		});

	}

}
