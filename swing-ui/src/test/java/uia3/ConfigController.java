package uia3;

import org.apache.log4j.Logger;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.ExecutarEvento;
import br.com.dsg.legui.controller.eventos.EventAdicionarItemMenu;
import br.com.dsg.legui.controller.eventos.EventVoltarController;

/**
 * @author Denis Giroto
 *
 */
public class ConfigController extends AbstractController<ConfigView> {

	private final static Logger LOG = Logger.getLogger(ConfigController.class);

	public ConfigController(AbstractController<?> controlerPai) {
		super(controlerPai, new ConfigView());

		registerAction(getPanel().btCriarMenu,
				(event) -> ExecutarEvento.get().lancar(
						new EventAdicionarItemMenu(
								"nome_"+System.currentTimeMillis(),
								"imagens/icons8-cardapio-16.png",
								"imagens/icons8-cardapio-fechado-16.png",
								false,
								true,
								(c)->c.setAppFinalizado(true))
						).executar()
		);
		
		registerAction(getPanel().btCancelar,
				(event) -> ExecutarEvento.get().lancar(
						new EventVoltarController(this, "Voltei do ConfigController")
						).executar()
		);
	}
	
}
