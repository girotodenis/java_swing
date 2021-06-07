package uia3;

import br.com.dsg.legui.controller.StartLeGui;


public class TesteStart {
	
	public static void main(String[] args) {
		new TesteStart().deve_iniciar_app();
	}

//	@Test
	public void deve_iniciar_app() {
		StartLeGui.get(800, 600, "App test")
		.autenticacao( (controllerPai) -> new LoginController(controllerPai) )
		.abrirFecharMenuPagrao()
		.addItemMenu("Configuração",
						"imagens/setting-configure.png",  
						(controllerPai)->new ConfigController(controllerPai)
		)
		.addItemMenu("Home",
						"imagens/home_house_10811.png", 
						(controllerPai)->new HomeController(controllerPai)
						, true
		)
		.addItemActionMenu( 
				"Sair",
				"imagens/icons8-exit-sign-64.png",  
				(c) -> c.setAppFinalizado(true) 
		)
		.menuFechado()
		.start();
	}
}
