package br.com.dsg.legui.componentes;

import org.liquidengine.legui.component.Panel;

import br.com.dsg.util.Constantes;

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
        add(this.appBarr = new AppBar(Constantes.LARGURA_MENU_ABERTO, 0, Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_PROGRESS_BAR));
        add(this.conteudoLeGui = new ConteudoLeGui(Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_PROGRESS_BAR, Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_APP_CONTEUDO));
        add(this.menu = new MenuLeGui(0,0,
        							Constantes.LARGURA_MENU_ABERTO, 
        							Constantes.ALTURA_APP,
        							Constantes.LARGURA_MENU_FECHADO,
        							Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_ITEM_MENU, 
        							this.appBarr, this.conteudoLeGui));
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
