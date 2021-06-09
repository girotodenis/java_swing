package uia3.componentes;

import org.apache.log4j.Logger;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.ExecutarEvento;
import br.com.dsg.legui.controller.eventos.EventAdicionarItemMenu;
import br.com.dsg.legui.controller.eventos.EventVoltarController;
import br.com.dsg.legui.controller.seguranca.TemPermissao;

/**
 * @author Denis Giroto
 *
 */
@TemPermissao("configurar")
public class ConfigController extends AbstractController<ConfigView> {

	private final static Logger LOG = Logger.getLogger(ConfigController.class);

	public ConfigController(AbstractController<?> controlerPai) {
		super(controlerPai, new ConfigView());

		registerAction(getPanel().btCriarMenu,
				(event) -> {
					EventAdicionarItemMenu event2 = new EventAdicionarItemMenu(
							"nome_"+System.currentTimeMillis(),
							"imagens/icons8-cardapio-16.png",
							"imagens/icons8-cardapio-fechado-16.png",
							false,
							true,
							(c)->c.setAppFinalizado(true));
					event2.addMenuFlutuante(
							new EventAdicionarItemMenu(
									"1_"+System.currentTimeMillis(),
									"imagens/icons8-cardapio-16.png",
									"imagens/icons8-cardapio-fechado-16.png",
									false,
									true,
									(c)->System.out.print("--_"+System.currentTimeMillis()))
							);
					event2.addMenuFlutuante(
							new EventAdicionarItemMenu(
									"2_"+System.currentTimeMillis(),
									"imagens/icons8-cardapio-16.png",
									"imagens/icons8-cardapio-fechado-16.png",
									false,
									true,
									(c)->System.out.print("++_"+System.currentTimeMillis()))
							);
					event2.addMenuFlutuante(
							new EventAdicionarItemMenu(
									"3_"+System.currentTimeMillis(),
									"imagens/icons8-cardapio-16.png",
									"imagens/icons8-cardapio-fechado-16.png",
									false,
									true,
									(c)->System.out.print("**_"+System.currentTimeMillis()))
							);
					event2.addMenuFlutuante(
							new EventAdicionarItemMenu(
									"4_"+System.currentTimeMillis(),
									"imagens/icons8-cardapio-16.png",
									"imagens/icons8-cardapio-fechado-16.png",
									false,
									true,
									(c)->System.out.print("**_"+System.currentTimeMillis()))
							);
					event2.addMenuFlutuante(
							new EventAdicionarItemMenu(
									"5_"+System.currentTimeMillis(),
									"imagens/icons8-cardapio-16.png",
									"imagens/icons8-cardapio-fechado-16.png",
									false,
									true,
									(c)->System.out.print("**_"+System.currentTimeMillis()))
							);
					event2.addMenuFlutuante(
							new EventAdicionarItemMenu(
									"6_"+System.currentTimeMillis(),
									"imagens/icons8-cardapio-16.png",
									"imagens/icons8-cardapio-fechado-16.png",
									false,
									true,
									(c)->System.out.print("**_"+System.currentTimeMillis()))
							);
					event2.addMenuFlutuante(
							new EventAdicionarItemMenu(
									"7_"+System.currentTimeMillis(),
									"imagens/icons8-cardapio-16.png",
									"imagens/icons8-cardapio-fechado-16.png",
									false,
									true,
									(c)->System.out.print("**_"+System.currentTimeMillis()))
							);
					
					ExecutarEvento.get().lancar(
							event2
							).executar();
				}
		);
		
		registerAction(getPanel().btCancelar,
				(event) -> ExecutarEvento.get().lancar(
						new EventVoltarController(this, "Voltei do ConfigController")
						).executar()
		);
	}
	
}
