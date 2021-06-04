package uia3;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.liquidengine.legui.component.Dialog;
import org.liquidengine.legui.component.Label;

import br.com.dsg.legui.controller.AbstractController;
import br.com.dsg.legui.controller.eventos.EventAtualizarConteudoEvento;
import br.com.dsg.legui.controller.eventos.EventProgressBar;

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

		registerAction(getPanel().botao1, (event) -> fireEvent(

				new EventAtualizarConteudoEvento(new ConfigController(this)))

		);

		registerTarefa(getPanel().botao2, (event) -> {
			for (int i = 0; i <= 100; i++) {
				try {
					Thread.sleep(50);
				} catch (Exception e) {
				}
				fireEvent(new EventProgressBar(i));
			}
		});

		registerAction(getPanel().botao3, (event) -> {
			Dialog dialog = new Dialog("Erro", 300, 100);
			dialog.setDraggable(false);
			dialog.setResizable(false);
			Label erro = new Label("botão 3", 10, 10, 300, 20);
			dialog.getContainer().add(erro);
			dialog.show(getPanel().getFrame());
		});

	}

}
