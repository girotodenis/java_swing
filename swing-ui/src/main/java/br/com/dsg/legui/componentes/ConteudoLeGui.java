package br.com.dsg.legui.componentes;

import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.component.Panel;

import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEvent;
import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEventListener;

public class ConteudoLeGui extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3275627456468898718L;

	private float x, y, w, h = 0;

	public ConteudoLeGui(float x, float y, float w, float h) {
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.setPosition(this.x, this.y);
		this.setSize(this.w, this.h);
		
		getListenerMap().addListener(MenuChangeSizeEvent.class, (MenuChangeSizeEventListener) event -> {
			
			float larguraApp = this.getFrame().getTooltipLayer().getSize().x();
			float alturaApp = this.getFrame().getTooltipLayer().getSize().y();
			
			this.x = event.getNewSize().x();
			
			this.setPosition(this.x, this.y);
			
			this.w = larguraApp-this.x;
			this.h = alturaApp-this.y;
			
			this.setSize(this.w, this.h);
			
			for(Component c: getChildComponents() ) {
				c.setPosition(0,0);
				c.setSize(this.w, this.h);
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
