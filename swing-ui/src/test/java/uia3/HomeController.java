package uia3;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import br.com.dsg.legui.controller.AbstractController;
import br.com.dsg.legui.controller.eventos.EventAtualizarConteudoEvento;

/**
 * @author Denis Giroto
 *
 */
public class HomeController extends AbstractController<HomeView> {

	private final static Logger LOG = Logger.getLogger(HomeController.class);

	class MeuEvento {

		public MeuEvento(String valor, JPanel target) {

			((JLabel) target.getComponent(0)).setText(valor);
		}

	}

	private int iValor = 1;

	public HomeController(AbstractController<?> controlerPai) {
		super(controlerPai, new HomeView());
		
		registerAction(getPanel().botao1, (event)-> fireEvent(
				
			new EventAtualizarConteudoEvento(new ConfigController(this))) 
		
		);

	}

}
