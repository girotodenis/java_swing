package br.com.dsg.legui.componentes;

import org.liquidengine.legui.component.ProgressBar;

import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEvent;
import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEventListener;

public class AppBar extends ProgressBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5615207212602152683L;

	private float x, y, w, h = 0;

	public AppBar(float x, float y, float w, float h) {
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		this.setPosition(this.x, this.y);
		this.setSize(this.w, this.h);

		getListenerMap().addListener(MenuChangeSizeEvent.class, (MenuChangeSizeEventListener) event -> {
			
			float larguraMenu = event.getNewSize().x();
			float larguraApp = this.getFrame().getTooltipLayer().getSize().x();
			float widthAppBar = larguraApp-larguraMenu;
			
			this.x = larguraMenu;
			this.setPosition(this.x, this.y);
			
			this.w = widthAppBar;
			this.setSize(this.w, this.h);
		});

	}

	
}
