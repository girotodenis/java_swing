package br.com.dsg.legui.componentes;

import org.liquidengine.legui.component.ProgressBar;

import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEvent;
import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEventListener;
import br.com.dsg.swing.util.Constantes;

public class AppBar extends ProgressBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5615207212602152683L;

	private float valor = 0F;

	public AppBar() {

		this.setPosition(Constantes.LARGURA_MENU_ABERTO, 0);
		this.setSize(Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_PROGRESS_BAR);

		getListenerMap().addListener(MenuChangeSizeEvent.class, (MenuChangeSizeEventListener) event -> {
			
			this.setPosition(event.getNewSize().x(), 0);
			if(event.isAberto()) {
				this.setSize(this.getFrame().getTooltipLayer().getSize().x()-Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_PROGRESS_BAR);
			}else {
				this.setSize(this.getFrame().getTooltipLayer().getSize().x()-Constantes.LARGURA_MENU_FECHADO, Constantes.ALTURA_PROGRESS_BAR);
			}
			setValue(this.valor);
		});

	}

	
}
