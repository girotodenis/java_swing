package br.com.dsg.legui.componentes;

import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.component.Panel;

import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEvent;
import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEventListener;
import br.com.dsg.swing.util.Constantes;

public class ConteudoLeGui extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3275627456468898718L;


	public ConteudoLeGui() {
		
		this.setPosition(Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_PROGRESS_BAR);
		this.setSize(Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_APP_CONTEUDO);

		getListenerMap().addListener(MenuChangeSizeEvent.class, (MenuChangeSizeEventListener) event -> {
			
			this.setPosition(event.getNewSize().x(), Constantes.ALTURA_PROGRESS_BAR);
			if(event.isAberto()) {
				this.setSize(this.getFrame().getTooltipLayer().getSize().x()-Constantes.LARGURA_MENU_ABERTO, this.getFrame().getTooltipLayer().getSize().y()-Constantes.ALTURA_PROGRESS_BAR);
			}else {
				this.setSize(this.getFrame().getTooltipLayer().getSize().x()-Constantes.LARGURA_MENU_FECHADO, this.getFrame().getTooltipLayer().getSize().y()-Constantes.ALTURA_PROGRESS_BAR);
			}
			
			for(Component c: getChildComponents() ) {
				c.setPosition(0,0);
				c.setSize(this.getSize());
			}
			
		});
		
	}

	public void addPanel(Panel panel) {
		
		removeAll(getChildComponents());
		
		panel.setPosition(0,0);
		panel.setSize(this.getSize());
		
		add(panel);
	}

}
