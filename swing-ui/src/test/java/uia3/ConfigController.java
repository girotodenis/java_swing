package uia3;

import org.apache.log4j.Logger;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.controller.eventos.EventVoltarController;

/**
 * @author Denis Giroto
 *
 */
public class ConfigController extends AbstractController<ConfigView> {

	private final static Logger LOG = Logger.getLogger(ConfigController.class);

	public ConfigController(AbstractController<?> controlerPai) {
		super(controlerPai, new ConfigView());

		registerAction(getPanel().btCancelar,
				(event) -> fireEvent(new EventVoltarController(this, "Voltei do ConfigController")));
	}
	
}
