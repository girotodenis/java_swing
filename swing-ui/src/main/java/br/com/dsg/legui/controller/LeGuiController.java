package br.com.dsg.legui.controller;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import br.com.dsg.legui.componentes.ItemMenu;
import br.com.dsg.legui.componentes.LeGuiView;
import br.com.dsg.legui.controller.eventos.EventAtualizarConteudoEvento;
import br.com.dsg.legui.controller.eventos.EventProgressBar;
import br.com.dsg.legui.controller.eventos.EventAbrirFecharMenu;
import br.com.dsg.legui.controller.eventos.ListenerEventAbrirFecharMenu;
import br.com.dsg.legui.controller.eventos.ListenerEventAtualizarConteudo;
import br.com.dsg.legui.controller.eventos.ListenerEventProgressBar;
import br.com.dsg.legui.controller.eventos.ListenerEventVoltarController;
import br.com.dsg.legui.controller.eventos.EventVoltarController;

/**
 * @author Denis Giroto
 *
 */
public class LeGuiController extends AbstractController<LeGuiView> {

	private final static Logger LOG = Logger.getLogger(LeGuiController.class);
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;

	/**
	 * 
	 */
	public LeGuiController(LeGuiView leGuiView) {
		super(leGuiView);

		registerAction(getPanel().getMenu().getBotaoMenu(), (event) -> {
			if (getPanel().getMenu().isAberto()) {
				getPanel().getMenu().encolherItens();
			} else {
				getPanel().getMenu().expandirItens();
			}
		});

		registerControllerEventListener(EventAbrirFecharMenu.class, new ListenerEventAbrirFecharMenu());
		registerControllerEventListener(EventAtualizarConteudoEvento.class, new ListenerEventAtualizarConteudo(this, getPanel()));
		registerControllerEventListener(EventVoltarController.class, new ListenerEventVoltarController(this, getPanel()));
		registerControllerEventListener(EventProgressBar.class, new ListenerEventProgressBar(this, getPanel()));
		


	
//		/*
//		 * Cadastro do evento de atualização do ProgressBar tela principal
//		 * 
//		 */
//		registerEventListener(EventAtualizarProgressBar.class, (event) -> {
//			
//			getPanel().getAppBar().getjProgressBar().setValue(event.getValor());
//			if(event.getAtual()==event.getTotal() || event.getTotal()==0) {
//				
//				getPanel().getAppBar().getjProgressBar().setValue(0);
//			}
//		});
//
//		//Cadastro da ação do botão do menu
//		getPanel().getMenu().getBotaoMenu().addMouseListener(new ActionBotaoMenu(this));

	}

	/**
	 * @author Denis Giroto Classe utilizada para disparar um evento que atualiza a
	 *         tela Principal.
	 */
	public class AtualizarAppEvento {
	}

//	/**
//	 * 
//	 */
//	private class ActionBotaoMenu extends java.awt.event.MouseAdapter {
//		
//		LeGuiController controller;
//		
//		public ActionBotaoMenu(PrincipalController controller) {
//			this.controller = controller;
//		}
//		
//		public void mousePressed(java.awt.event.MouseEvent evt) {
//
//			controller.fireEvent(new EventAbrirFecharMenu(getPanel()));
//
//			SwingUtilities.invokeLater(new Runnable() {
//				public void run() {
//					fireEvent(new AtualizarAppEvento());
//				}
//			});
//		}
//	};

	/**
	 * @param nome
	 * @return
	 */
	public LeGuiController setIconImage(String imagemCaminho) {
//		javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(imagemCaminho));
//		getPanel().setIconImage(icon.getImage());
		return this;
	}

	/**
	 * @param nome
	 * @param imagem
	 * @param cController
	 * @return
	 */
	public LeGuiController addItemMenu(String nome, String imagem, GerarController cController) {
		LOG.info("menu " + nome + " criado");
		addItemMenu(nome, imagem, cController, Boolean.FALSE);
		return this;
	}

	/**
	 * @param nome
	 * @param imagem
	 * @param cController
	 * @param inicializar
	 * @return
	 */
	public LeGuiController addItemMenu(String nome, String imagem, GerarController cController, Boolean inicializar) {
//
		LOG.info("menu " + nome + " criado");

		ItemMenu item = getPanel().getMenu().criarItemMenu(nome, imagem);
		registerAction(item, (event) -> {

			item.seleciona();
			ActionMenu action = new ActionMenu() {
				public void executar() {
					AbstractController<?> controllerMenu = cController.criar(LeGuiController.this);
					controllerMenu.setNomeController(nome);
					fireEvent(new EventAtualizarConteudoEvento(nome, controllerMenu));
				}
			};
			action.executar();

		});

		if (inicializar) {
			 fireEvent(new EventAtualizarConteudoEvento(nome, cController.criar(LeGuiController.this)));
		}
		return this;
	}

	public LeGuiController addItemMenu(String nome, String imagem, ActionMenu action) {
		LOG.info("menu " + nome + " criado");
		ItemMenu item = getPanel().getMenu().criarItemMenu(nome, imagem);
		registerAction(item, (event) -> {
			item.seleciona();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					for (int i = 0; i <= 100; i++) {
						try {
							Thread.sleep(2);
							fireEvent(new EventProgressBar(i));
						} catch (Exception e) {}
					}
				}
			});
			action.executar();
		});
		return this;
	}

	/**
	 * @return
	 */
	public LeGuiController fecharMenu() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(100);
					fireEvent(new EventAbrirFecharMenu(getPanel()));
				} catch (Exception e) {
				}
			}
		});
		return this;
	}

//
//	/**
//	 * 
//	 */
//	public void visualizarApp() {
//		this.getPanel().setVisible(true);
//	}

}
