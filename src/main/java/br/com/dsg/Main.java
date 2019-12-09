/**
 * 
 */
package br.com.dsg;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import br.com.dsg.appteste.controller.ConfigController;
import br.com.dsg.appteste.controller.HomeController;
import br.com.dsg.principal.controller.PrincipalController;
import br.com.dsg.swing.util.PropertiesUtil;

/**
 * @author Denis Giroto
 *
 */
public class Main {
	
	private final static Logger LOG = Logger.getLogger(Main.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		LOG.info(PropertiesUtil.get("sistema"));
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
        	
        	LOG.error(ex.getMessage(), ex);
        }
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LOG.info("PrincipalController criando...");
				new PrincipalController()
					//.habilitaMovimentavaoAppBar()
					.addItemMenu("Home","/imagens/home_house_10811.png", (controllerPai)->new HomeController(controllerPai), Boolean.TRUE)
					.addItemMenu("Configuração","/imagens/setting-configure.png",  (controllerPai)->new ConfigController(controllerPai))
					.visualizarApp();
				LOG.info("PrincipalController criado");
			}
		});

	}

}
