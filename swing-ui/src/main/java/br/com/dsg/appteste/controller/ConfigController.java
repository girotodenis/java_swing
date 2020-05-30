package br.com.dsg.appteste.controller;

import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.dsg.Main;
import br.com.dsg.appteste.views.ConfigView;
import br.com.dsg.principal.controller.EventVoltarController;
import br.com.dsg.swing.controller.AbstractController;
import br.com.dsg.swing.util.PropertiesUtil;

/**
 * @author Denis Giroto
 *
 */
public class ConfigController extends AbstractController<ConfigView> {

	private final static Logger LOG = Logger.getLogger(ConfigController.class);

	public ConfigController(AbstractController<?> controlerPai) {
		super(controlerPai, new ConfigView());

		if (controlerPai != null && controlerPai.getPanel() != null) {

			registerAction(getPanel().getBtCancelar(),
					() -> fireEvent(new EventVoltarController(this, "Voltei do ConfigController")));
		} else {

			getPanel().getBtCancelar().setVisible(false);
		}
		
		registerAction(getPanel().getBtGravar(),
				() -> gravarAlteracoes());
		
	}
	
	public void gravarAlteracoes() {
//		PropertiesUtil.
		Main.reset();
	}
	
	public void carregarCofiguracoes() {
	
	}

}
