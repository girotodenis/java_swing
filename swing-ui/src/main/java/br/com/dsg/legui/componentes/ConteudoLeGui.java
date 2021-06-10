package br.com.dsg.legui.componentes;

import java.util.List;

import org.apache.log4j.Logger;
import org.joml.Vector2f;
import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.component.Panel;
import org.liquidengine.legui.component.event.component.ChangeSizeEvent;
import org.liquidengine.legui.listener.processor.EventProcessorProvider;

import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEvent;
import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEventListener;

public class ConteudoLeGui extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3275627456468898718L;
	private final static Logger LOG = Logger.getLogger(ConteudoLeGui.class);

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
				Vector2f oldSize = c.getSize();
				c.setSize(this.w, this.h);
				Vector2f newSize = c.getSize();
				EventProcessorProvider.getInstance().pushEvent(new ChangeSizeEvent<Component>(c, null,
						this.getFrame(),oldSize, newSize));
				
				if(c.getChildComponents()!=null && !c.getChildComponents().isEmpty()) {
					update(c.getChildComponents(),oldSize, newSize);
				}
			}
		});
		
	}

	public void update(List<Component> lista, Vector2f oldSize, Vector2f newSize) {
		LOG.info(String.format("%s x=%s, y=%s, w=%s, h=%s.", "ConteudoLeGui", x, y, w, h));
		LOG.info(String.format("%s x=%s, y=%s, w=%s, h=%s.", "ConteudoLeGui", getPosition().x(), getPosition().y(), getSize().x(), getSize().y()));
		for(Component c: lista ) {
			LOG.info(String.format("%s x=%s, y=%s, w=%s, h=%s.", c.getClass().getSimpleName(), c.getPosition().x(), c.getPosition().y(), c.getSize().x(), c.getSize().y()));
			EventProcessorProvider.getInstance().pushEvent(new ChangeSizeEvent<Component>(c, null,
					this.getFrame(),oldSize, newSize));
			
			if(c.getChildComponents()!=null && !c.getChildComponents().isEmpty()) {
				update(c.getChildComponents(),oldSize, newSize);
			}
		}
	}

	public void addPanel(Panel panel) {
		removeAll(getChildComponents());
		panel.setSize(this.getSize());
		panel.setPosition(0,0);
		add(panel);
		update(getChildComponents(), panel.getSize(), panel.getSize());
	}

}
