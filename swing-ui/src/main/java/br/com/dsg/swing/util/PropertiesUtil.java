package br.com.dsg.swing.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author Denis Giroto
 *
 */
public class PropertiesUtil {
	
	private static ResourceBundle config = ResourceBundle.getBundle("config");
	private static File external;
	private static Properties properties = new Properties();
	
	static {
		try {
			external = new File("./config/config.properties");
			if (external.exists()) {
				
				properties.load(new FileInputStream(external));
			}
			else {
				File dir = new File("./config");
				
				if(!dir.exists()) {
					dir.mkdir();
				}
				
				for(String chave : config.keySet()) {
					properties.setProperty(chave, config.getString(chave));
				}
				
				//properties.store(new FileOutputStream(external), "epol prehom");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String chave) {
		return properties.getProperty(chave);
		
	}

	public static int getInt(String string) {
		return Integer.valueOf(get(string).replaceAll("[^0-9]", ""));
	}

}
