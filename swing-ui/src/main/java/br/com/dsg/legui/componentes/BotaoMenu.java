package br.com.dsg.legui.componentes;

import org.joml.Vector2f;
import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.component.Panel;
import org.liquidengine.legui.icon.Icon;
import org.liquidengine.legui.icon.ImageIcon;
import org.liquidengine.legui.image.loader.ImageLoader;

import br.com.dsg.util.Constantes;

public class BotaoMenu extends Panel {


	private static final int X_MENU_FECHADO = Constantes.LARGURA_MENU_FECHADO - Constantes.LARGURA_MENU_FECHADO;

	private static final int X_MENU_ABERTO = Constantes.LARGURA_MENU_ABERTO - Constantes.LARGURA_MENU_FECHADO;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1923843665779481525L;
	
	private Button btMenu;
	
	
	public BotaoMenu(boolean aberto) {
		
		Icon bgIm = new ImageIcon(ImageLoader.loadImage("imagens/icons8-cardapio-30.png"));
		bgIm.setSize(new Vector2f(Constantes.LARGURA_MENU_FECHADO, Constantes.ALTURA_ITEM_MENU));
		
		btMenu = new Button("", 0, 0, Constantes.LARGURA_MENU_FECHADO, Constantes.ALTURA_ITEM_MENU);
		btMenu.getStyle().getBackground().setIcon(bgIm);
		
		if(aberto) {
			abrir();
		}else {
			fechar();
		}
		
		add(btMenu);

	}
	
	protected void abrir() {
		btMenu.setPosition(X_MENU_ABERTO, 0);;
	}
	
	protected void fechar() {
		btMenu.setPosition(X_MENU_FECHADO, 0);;
	}
	
	
	protected Button getBtMenu() {
		return btMenu;
	}
	
}
