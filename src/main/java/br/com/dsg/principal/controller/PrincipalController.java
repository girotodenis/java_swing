package br.com.dsg.principal.controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import br.com.dsg.principal.view.PrincipalView;
import br.com.dsg.principal.view.componente.ItemMenu;
import br.com.dsg.swing.controller.AbstractController;
import br.com.dsg.swing.controller.action.AbstractAction;
import br.com.dsg.swing.tela.layout.AbsoluteConstraints;
import br.com.dsg.swing.util.Constantes;

/**
 * @author Denis Giroto
 *
 */
public class PrincipalController extends AbstractController{
	
	
	private PrincipalView frame = new PrincipalView();
	private int xx,xy;
	
	private final static Logger LOG = Logger.getLogger(PrincipalController.class);
	
	class AtualizarAppEvento {}
	
	class MeuEvento {

		public MeuEvento( String valor, JPanel target) {
			JLabel jLabel = new JLabel(valor);
			jLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
			jLabel.setForeground(new java.awt.Color(255, 255, 255));
			target.add(jLabel);
//			target.updateUI();
		}
		
		public void print() {
			LOG.info("MeuEvento");
		}
	}
	
	public PrincipalController() {
		
		frame.addWindowListener(this);
		frame.setVisible(true);
		
		registerEventListener(AtualizarAppEvento.class, (event) -> frame.atualizar());
		registerEventListener(MeuEvento.class, (event) -> event.print());
		
		registerEventListener(EventItemMenu.class, (event)->{
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						ItemMenu item = PrincipalController.this.frame.getMenu().getItem(event.getNome());
						item.seleciona();
						frame.getMenu().resetOutros(item);
					}
				});
			}
		);
		
		frame.getMenu().addItem(new ItemMenu("Home", new AbstractAction() {
			protected void action() {
				LOG.info("menu home");
				fireEvent(new EventItemMenu("Home"));
			}
		}));
		
		frame.getMenu().addItem(new ItemMenu("Configuracao", new AbstractAction() {
			protected void action() {
				LOG.info("menu Configuracao");
				fireEvent(new EventItemMenu("Configuracao"));
			}
		}));
		
		frame.getAppBar().addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
            	int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();
                frame.setLocation(x-xx,y-xy);
            }
        });
		
		frame.getAppBar().addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
            	xx = evt.getX();
            	xy = evt.getY();
            }
        });
		
		frame.getMenu().getNomeSistema().addMouseListener(new java.awt.event.MouseAdapter() {
			private boolean menuAberto = true;
			public void mousePressed(java.awt.event.MouseEvent evt) {
				if(menuAberto = !menuAberto) {
					frame.getMenu().expandeItens();
					frame.remove(frame.getAppBar());
					frame.getContentPane().add(frame.getAppBar(), 
							new AbsoluteConstraints(Constantes.LARGURA_MENU_ABERTO, 0, Constantes.LARGURA_APP_BAR, Constantes.ALTURA_APP_BAR));
					
					frame.remove(frame.getMenu());
					frame.getContentPane().add(frame.getMenu(), 
							new AbsoluteConstraints(0, 0, Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_APP));
	        	}else {
	        		frame.getMenu().encolheItens();
	        		frame.remove(frame.getAppBar());
					frame.getContentPane().add(frame.getAppBar(), 
							new AbsoluteConstraints(Constantes.LARGURA_MENU_FECHADO, 0, Constantes.LARGURA_APP_BAR_FULL, Constantes.ALTURA_APP_BAR));
					
					frame.remove(frame.getMenu());
					frame.getContentPane().add(frame.getMenu(), 
							new AbsoluteConstraints(0, 0, Constantes.LARGURA_MENU_FECHADO, Constantes.ALTURA_APP));
	        	}
				frame.getMenu().getNomeSistema().addMouseListener(this);
	        	
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						fireEvent(new AtualizarAppEvento());
					}
				});
            }
        });
		
		
		
		
		registerAction(frame.getBotao(), ()-> {
			LOG.info("action");
			fireEvent(new MeuEvento("Teste", frame.getTarget()));
			fireEvent(new AtualizarAppEvento());
		});
		
		
		
	}
	

}
