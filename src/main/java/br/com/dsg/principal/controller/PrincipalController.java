package br.com.dsg.principal.controller;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import br.com.dsg.principal.view.PrincipalView;
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
		}
		
		public void print() {
			System.out.println("MeuEvento");
		}
	}
	
	public PrincipalController() {
		
		frame.addWindowListener(this);
		frame.setVisible(true);
		
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
		
		
		
		
		registerAction(frame.getBotao(), new AbstractAction() {
			
			private boolean isMenuAberto = true;
			
			@Override
			protected void preAction() {
				LOG.info("preAction");
			}
			
			@Override
			protected void action() {
				LOG.info("action");
				//fireEvent(new MeuEvento("Teste", frame.getTarget()));
				if(isMenuAberto = !isMenuAberto) {
					frame.remove(frame.getAppBar());
					frame.getContentPane().add(frame.getAppBar(), 
							new AbsoluteConstraints(Constantes.LARGURA_MENU_ABERTO, 0, Constantes.LARGURA_APP_BAR, Constantes.ALTURA_APP_BAR));
			        
				}else {
					frame.remove(frame.getAppBar());
					frame.getContentPane().add(frame.getAppBar(), 
							new AbsoluteConstraints(Constantes.LARGURA_MENU_FECHADO, 0, Constantes.LARGURA_APP_BAR_FULL, Constantes.ALTURA_APP_BAR));
					
				}
				
				fireEvent(new AtualizarAppEvento());
				fireEvent(new MeuEvento("Teste", frame.getTarget()));
			}
			
			@Override
			protected void posAction() {
				LOG.info("preAction");
			}
			
		});
		
		registerEventListener(AtualizarAppEvento.class, (event) -> frame.atualizar());
		registerEventListener(MeuEvento.class, (event) -> event.print());
		
	}
	

}
