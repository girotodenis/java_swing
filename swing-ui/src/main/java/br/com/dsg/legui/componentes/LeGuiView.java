package br.com.dsg.legui.componentes;

import org.liquidengine.legui.component.Panel;

public class LeGuiView extends Panel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7467979951269544962L;
	
	private MenuLeGui menu;
	private AppBar appBarr;
	private ConteudoLeGui conteudoLeGui;
	

	public LeGuiView(int width, int height) {
        super(0, 0, width, height);
        add(this.appBarr = new AppBar());
        add(this.conteudoLeGui = new ConteudoLeGui());
        add(this.menu = new MenuLeGui(this.appBarr, this.conteudoLeGui));
        //add(this.menu = new MenuLeGui(this.appBarr));
	}
	
	public MenuLeGui getMenu() {
		return menu;
	}
	
	public void addPanel(Panel panel) {
		conteudoLeGui.addPanel(panel);
	}
	
	public void setValueProgressBar(float value) {
		appBarr.setValue(value);
	}

}
