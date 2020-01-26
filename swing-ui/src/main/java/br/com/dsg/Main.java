/**
 * 
 */
package br.com.dsg;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

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
					.habilitaMovimentavaoAppBar()
					.addItemMenu("Home","/imagens/home_house_10811.png", (controllerPai)->new HomeController(controllerPai), Boolean.TRUE)
					.addItemMenu("Configuração","/imagens/setting-configure.png",  (controllerPai)->new ConfigController(controllerPai))
					.addItemMenu("Sair","/imagens/icons8-exit-sign-64.png",  () -> System.exit(0))
					.fecharMenu()
					.visualizarApp();
				LOG.info("PrincipalController criado");
			}
		});

	}
	
	public static void reset() {
		ArrayList<String> commands = new ArrayList<String>();
        List<String> jvmArgs = ManagementFactory.getRuntimeMXBean().getInputArguments();

        // Java
        commands.add(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java");


        // Classpath
        commands.add("-cp");
        commands.add(ManagementFactory.getRuntimeMXBean().getClassPath());

        // Class to be executed
        commands.add(Main.class.getName());

        File workingDir = null; // Null working dir means that the child uses the same working directory

        String[] env = null; // Null env means that the child uses the same environment

        String[] commandArray = new String[commands.size()];
        commandArray = commands.toArray(commandArray);

        try {
            Runtime.getRuntime().exec(commandArray, env, workingDir);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
