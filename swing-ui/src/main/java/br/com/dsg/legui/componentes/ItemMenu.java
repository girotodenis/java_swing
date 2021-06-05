package br.com.dsg.legui.componentes;

import org.joml.Vector2f;
import org.joml.Vector4f;
import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.component.Panel;
import org.liquidengine.legui.component.optional.align.HorizontalAlign;
import org.liquidengine.legui.icon.Icon;
import org.liquidengine.legui.icon.ImageIcon;
import org.liquidengine.legui.image.loader.ImageLoader;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEvent;
import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEventListener;
import br.com.dsg.util.Constantes;

public class ItemMenu extends Button {

	private static final String ESPACO = ".              ";


	/**
	 * 
	 */
	private static final long serialVersionUID = 1923843665779481525L;

	private String nome = "";
	private String imagePathAberta = "";
	private String imagePathFechada = "";
	private boolean imageHorizontalAlignRIGHT = false;
	
	private Class<? extends Panel> panel;
	
	private float w, h = 0;
	private int ordem = 0;
	private int posicao = 0;
	private Vector4f color;

	public ItemMenu(float w, float h, String nome, String imagePathAberta) {
		this(w, h, nome, imagePathAberta, imagePathAberta, false);
	}
	
	public ItemMenu(float w, float h, String nome, String imagePathAberta, boolean imageHorizontalAlignRIGHT) {
		this(w,h,nome, imagePathAberta, imagePathAberta, false);
	}
	
	public ItemMenu(float w, float h, String nome, String imagePathAberta, String imagePathFechada, boolean imageHorizontalAlignRIGHT) {
		
		this.color = getStyle().getBackground().getColor();
		
		this.nome=ESPACO + nome;
		this.imagePathAberta = imagePathAberta;
		this.imagePathFechada = imagePathFechada;
		this.imageHorizontalAlignRIGHT = imageHorizontalAlignRIGHT;
		this.w=w;
		this.h=h;
		
		getStyle().setHorizontalAlign(HorizontalAlign.LEFT);
		getTextState().setText(this.nome);
		
		setSize(this.w, this.h);

		Icon bgIm = new ImageIcon(ImageLoader.loadImage(this.imagePathAberta));
		bgIm.setSize(new Vector2f(35, 30));
		bgIm.setPosition(new Vector2f(8, 5));
		getStyle().getBackground().setIcon(bgIm);
		
		expandir();
		
		getListenerMap().addListener(MenuChangeSizeEvent.class, (MenuChangeSizeEventListener) event -> {
			this.w = event.getNewSize().x();
			setSize(w, Constantes.ALTURA_ITEM_MENU);
			if(event.isAberto()) {
				expandir();
			}else {
				encolher();
			}
		});
		
	}

	private void encolher() {
		if(imageHorizontalAlignRIGHT) {
			Icon bgIm = new ImageIcon(ImageLoader.loadImage(this.imagePathFechada));
			bgIm.setSize(new Vector2f(35, 30));
			bgIm.setPosition(new Vector2f(8, 5));
			getStyle().getBackground().setIcon(bgIm);
		}
		getTextState().setText("");
	}
	
	private void expandir() {
		if(imageHorizontalAlignRIGHT) {
			Icon bgIm = new ImageIcon(ImageLoader.loadImage(this.imagePathAberta));
			bgIm.setSize(new Vector2f(35, 30));
			bgIm.setPosition(new Vector2f(getSize().x()-37, 5));
			getStyle().getBackground().setIcon(bgIm);
		}
		getTextState().setText(nome);
	}
	
	protected void seleciona() {
		this.setEnabled(false);
		this.setPressed(true);
		this.setFocused(true);
		
		this.getStyle().getBackground().setColor(this.getPressedStyle().getBackground().getColor());
	}

	public void rest() {
		this.setEnabled(true);
		this.setPressed(false);
		this.setFocused(false);
		this.getStyle().getBackground().setColor(this.color);
	}

	public int getPosicao() {
		return posicao;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
		this.posicao = ((int) this.h) * this.ordem;
	}

	protected String getNome() {
		return nome;
	}

	public void setPanel(Class<? extends Panel> panel) {
		this.panel = panel;
	}

	protected Class<? extends Panel> getPanel() {
		return panel;
	}

}
