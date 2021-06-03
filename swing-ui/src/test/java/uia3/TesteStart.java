package uia3;

import org.testng.annotations.Test;

import br.com.dsg.legui.controller.StartLeGui;


public class TesteStart {

	@Test
	public void deve_iniciar_app() {
		StartLeGui.get(800, 600, "App test")
		.addItemActionMenu(
							"",
							"imagens/icons8-cardapio-30.png",
							"imagens/icons8-cardapio-fechado-30.png", 
							true, 
							(c) -> c.menuAbrirFecharMenu() 
		)
		.addItemMenu("Configuração",
						"imagens/setting-configure.png",  
						(controllerPai)->new ConfigController(controllerPai)
		)
		.addItemMenu("Home",
						"imagens/home_house_10811.png", 
						(controllerPai)->new HomeController(controllerPai), true
		)
		.addItemActionMenu( 
				"Sair",
				"imagens/icons8-exit-sign-64.png",  
				(c) -> System.exit(2) 
		)
		.fecharMenu()
		.start();
	}
}
