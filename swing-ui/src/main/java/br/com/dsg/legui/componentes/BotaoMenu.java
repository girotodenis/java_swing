package br.com.dsg.legui.componentes;

import org.joml.Vector2f;
import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.component.Panel;
import org.liquidengine.legui.icon.Icon;
import org.liquidengine.legui.icon.ImageIcon;
import org.liquidengine.legui.image.loader.ImageLoader;

import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEvent;
import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEventListener;
import br.com.dsg.util.Constantes;

public class BotaoMenu extends Panel {


	private static final int X_MENU_FECHADO = Constantes.LARGURA_MENU_FECHADO - Constantes.LARGURA_MENU_FECHADO;

	private static final int X_MENU_ABERTO = Constantes.LARGURA_MENU_ABERTO - Constantes.LARGURA_MENU_FECHADO;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1923843665779481525L;
	
	private Button btMenu;
	private float x, y, w, h = 0;
	
	
	public BotaoMenu(float x, float y, float w, float h) {
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		btMenu = new Button("", 0, 0, this.w, this.h);
		
		getListenerMap().addListener(MenuChangeSizeEvent.class, (MenuChangeSizeEventListener) event -> {
			
			float larguraMenu = event.getNewSize().x();
			
			if(event.isAberto()) {
				abrir();
			}else {
				fechar();
			}
			
			this.x = larguraMenu - this.w;
			btMenu.setPosition(this.x, this.y);
			
		});
		
		
		
		add(btMenu);

	}
	
	protected void abrir() {
		Icon bgIm = new ImageIcon(ImageLoader.loadImage("imagens/icons8-cardapio-30.png"));
		bgIm.setSize(new Vector2f(this.w, this.h));
		btMenu.getStyle().getBackground().setIcon(bgIm);
	}
	
	protected void fechar() {
		Icon bgIm = new ImageIcon(ImageLoader.loadImage("imagens/icons8-cardapio-fechado-30.png"));
		bgIm.setSize(new Vector2f(this.w, this.h));
		btMenu.getStyle().getBackground().setIcon(bgIm);
	}
	
	
	protected Button getBtMenu() {
		return btMenu;
	}
	
}
