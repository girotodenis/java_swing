package uia3;

import org.apache.log4j.Logger;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.controller.eventos.EventAtualizarConteudoEvento;
import br.com.dsg.legui.controller.seguranca.SeguracaController;
import br.com.dsg.legui.controller.seguranca.Usuario;

/**
 * @author Denis Giroto
 *
 */
public class LoginController extends SeguracaController<LoginView>  {

	private final static Logger LOG = Logger.getLogger(LoginController.class);

	public LoginController(AbstractController<?> controlerPai) {
		super(controlerPai, new LoginView());
		
		registerAction(getPanel().btLogar, (event) -> {
			LOG.info("sdsdsdsdsds");
			login("asasasa", "asasasa");
//			if(getSession().isAutenticado()) {
//				fireEvent(new EventAtualizarConteudoEvento(new HomeController(this)));
//			}
		});
	}
	
	@Override
	protected Usuario logar(String... parametros) {
		if(parametros[0].equals(parametros[1])) 
			return new Usuario();
		return null;
	}
	
}
