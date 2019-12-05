package br.com.dsg.swing.controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import br.com.dsg.swing.controller.action.AbstractAction;
import br.com.dsg.swing.view.PrincipalView;

/**
 * @author Denis Giroto
 *
 */
public class PrincipalController extends AbstractController{
	
	
	private PrincipalView frame = new PrincipalView();
	
	private final static Logger LOG = Logger.getLogger(PrincipalController.class);
	
	class MeuEvento extends AbstractEvent<JPanel> {

		public MeuEvento(final String valor, final JPanel target) {
			super(target);
	        
	        SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JLabel jLabel = new JLabel(valor);
					jLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
					jLabel.setForeground(new java.awt.Color(255, 255, 255));
					target.add(jLabel);
					
				}
			});
		}
		
	}
	
	public PrincipalController() {
		
		frame.addWindowListener(this);
		frame.setVisible(true);
		
		
		registerAction(frame.getBotao(), new AbstractAction() {
			
			@Override
			protected void preAction() {
				LOG.info("preAction");
			}
			
			@Override
			protected void action() {
				LOG.info("action");
				fireEvent(new MeuEvento("Teste", frame.getTarget()));
			}
			
			@Override
			protected void posAction() {
				LOG.info("preAction");
			}
			
		});
		
		
		
		registerEventListener(MeuEvento.class, new AbstractEventListener<MeuEvento>() {
			public void handleEvent(final MeuEvento event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						LOG.info("atualiza target");
						
						try {
							Thread.sleep(500L);
							event.getTarget().invalidate();
							event.getTarget().validate();
							event.getTarget().repaint();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		});
		
	}
	
	

}
