package br.com.dsg.appteste.controller;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import br.com.dsg.appteste.views.HomeView;
import br.com.dsg.principal.controller.EventItemMenu;
import br.com.dsg.swing.controller.AbstractController;

/**
 * @author Denis Giroto
 *
 */
public class HomeController extends AbstractController<HomeView>{
	
	
	
	private final static Logger LOG = Logger.getLogger(HomeController.class);
	
	
	class MeuEvento {

		public MeuEvento( String valor, JPanel target) {
			
			((JLabel)target.getComponent(0)).setText(valor);
		}
		
	}
	
	static int i = 1;
	
	public HomeController(AbstractController<?> controlerPai) {
		super(controlerPai, new HomeView());
		
		registerEventListener(MeuEvento.class, (event) -> System.out.print(event.toString()));
		
		
		registerAction(getPanel().getBotao(), ()-> {
			LOG.info("action");
			
			fireEvent(new MeuEvento("Teste "+(i++), getPanel().getTarget() ));
		});

		registerAction(getPanel().getBotao2(),  ()-> {
			fireEvent(new EventItemMenu("Configuração", new ConfigController(this))) ;
			}
		);
		
	}
	
	

}
