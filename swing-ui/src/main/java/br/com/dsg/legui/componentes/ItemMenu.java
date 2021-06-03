package br.com.dsg.legui.componentes;

import org.joml.Vector2f;
import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.component.optional.align.HorizontalAlign;
import org.liquidengine.legui.icon.Icon;
import org.liquidengine.legui.icon.ImageIcon;
import org.liquidengine.legui.image.loader.ImageLoader;

import br.com.dsg.util.Constantes;

public class ItemMenu extends Button {

	private static final String ESPACO = ".              ";


	/**
	 * 
	 */
	private static final long serialVersionUID = 1923843665779481525L;

	private String nome = "";
	private String imagepath = "";
	
	private int ordem = 0;
	private int posicao = 0;

	public ItemMenu(String nome, String imagepath) {
		
		
		
		this.nome=ESPACO + nome;
		this.imagepath = imagepath;
		
		getStyle().setHorizontalAlign(HorizontalAlign.LEFT);
		getTextState().setText(this.nome);
		
		setSize(Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_ITEM_MENU);

		Icon bgIm = new ImageIcon(ImageLoader.loadImage(this.imagepath));
		bgIm.setSize(new Vector2f(35, 30));
		bgIm.setPosition(new Vector2f(8, 5));
		getStyle().getBackground().setIcon(bgIm);
		
	}

	public void encolher() {
		setSize(Constantes.LARGURA_MENU_FECHADO, Constantes.ALTURA_ITEM_MENU);
		getTextState().setText("");
	}
	
	public void expandir() {
		setSize(Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_ITEM_MENU);
		getTextState().setText(nome);
	}
	
	public void seleciona() {
		this.setEnabled(false);
		this.setPressed(true);
		this.setFocused(true);
	}

	public void rest() {
		this.setEnabled(true);
		this.setPressed(false);
		this.setFocused(false);
	}

	public int getPosicao() {
		return posicao;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
		this.posicao = Constantes.ALTURA_ITEM_MENU * this.ordem;
	}

	protected String getNome() {
		return nome;
	}

	
	
}
