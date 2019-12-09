package br.com.dsg.principal.controller;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import br.com.dsg.principal.view.PrincipalView;
import br.com.dsg.principal.view.componente.ItemMenu;
import br.com.dsg.swing.controller.AbstractController;
import br.com.dsg.swing.controller.action.AbstractAction;

/**
 * @author Denis Giroto
 *
 */
public class PrincipalController extends AbstractController<PrincipalView>{
	
	
	private int xx,xy;
	
	private final static Logger LOG = Logger.getLogger(PrincipalController.class);
	
	class AtualizarAppEvento {}
	
	public PrincipalController() {
		super(new PrincipalView());
		
		getPanel().addWindowListener(this);
		
		registerEventListener(AtualizarAppEvento.class, (event) -> getPanel().atualizar());
		
		registerEventListener(EventItemMenu.class, (event)->{
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						ItemMenu item = PrincipalController.this.getPanel().getMenu().getItem(event.getNome());
						item.seleciona();
						getPanel().getMenu().resetOutros(item);
						
						if(event.getController()!=null) {
							getPanel().getPrincipalJpane().getConteudo().removeAll();
							getPanel().getPrincipalJpane().getConteudo().setLayout(new GridBagLayout());
							GridBagConstraints c = new GridBagConstraints();
							c.gridx = GridBagConstraints.RELATIVE;
							c.gridy = GridBagConstraints.RELATIVE;
							c.fill = GridBagConstraints.BOTH;
							c.weightx = 1.0;
							c.weighty = 1.0;
							c.gridwidth =  GridBagConstraints.REMAINDER;
							c.gridheight=  GridBagConstraints.REMAINDER;
							getPanel().getPrincipalJpane().getConteudo().add((Component) event.getController().getPanel(), c);
							fireEvent(new AtualizarAppEvento());
						}
					}
				});
			}
		);
		
		

		
		getPanel().getMenu().getBotaoMenu().addMouseListener(new java.awt.event.MouseAdapter() {
			private boolean menuAberto = true;
			public void mousePressed(java.awt.event.MouseEvent evt) {
				
				fireEvent(new EventAbrirFecharMenu(menuAberto = !menuAberto, getPanel(), this));
	        	
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						fireEvent(new AtualizarAppEvento());
					}
				});
            }
        });
	}
	
	public PrincipalController habilitaMovimentavaoAppBar() {
		getPanel().setUndecorated(true);//retirar barra
		getPanel().getPrincipalJpane().addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
            	int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();
                getPanel().setLocation(x-xx,y-xy);
            }
        });
		
		getPanel().getPrincipalJpane().addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
            	xx = evt.getX();
            	xy = evt.getY();
            }
        });
		
		return this;
	}
	
	public PrincipalController addItemMenu(String nome, CriaController cController) {
		
		LOG.info("menu "+nome+" criado");
		
		addItemMenu(nome, null, cController);
		
		return this;
	}
	
	public PrincipalController addItemMenu(String nome, String imagem, CriaController cController) {
		
		LOG.info("menu "+nome+" criado");
		
		getPanel().getMenu().addItem(new ItemMenu(nome, imagem, new AbstractAction() {
			protected void action() {
				AbstractController<?> controllerMenu = cController.criar(PrincipalController.this);
				fireEvent(new EventItemMenu(nome, controllerMenu));
			}
		}));
		return this;
	}
	
	public void visualizarApp() {
		this.getPanel().setVisible(true);
	}
	

}
