package br.com.dsg.legui.componentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joml.Vector2f;
import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.component.Panel;
import org.liquidengine.legui.event.WindowSizeEvent;
import org.liquidengine.legui.listener.WindowSizeEventListener;
import org.liquidengine.legui.listener.processor.EventProcessorProvider;

import br.com.dsg.legui.componentes.eventos.MenuChangeSizeEvent;
import br.com.dsg.swing.util.Constantes;

public class MenuLeGui extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3275627456468898718L;

	private Map<String, ItemMenu> mapa = new HashMap<String, ItemMenu>();

	private Component[] components;
	private BotaoMenu botaoMenu;
	private boolean aberto = Boolean.TRUE;

	public MenuLeGui(Component... panels) {

		this.components = panels;
			
		this.setPosition(0, 0);
		this.setSize(Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_APP);

		getListenerMap().addListener(WindowSizeEvent.class, (WindowSizeEventListener) event -> {

			Vector2f newSize = new Vector2f(getSize().x, event.getHeight());

			notifica(newSize);

			this.setSize(newSize);
		});

		botaoMenu = new BotaoMenu(true);
		botaoMenu.setPosition(0, 0);
		botaoMenu.setSize(Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_ITEM_MENU);

		add(botaoMenu);
	}

	public ItemMenu criarItemMenu(String nome, String imagem) {
		ItemMenu item = new ItemMenu(nome, imagem);
		mapa.put(item.getNome(), item);
		item.setOrdem(mapa.size());
		item.setPosition(0, item.getPosicao());
		item.setSize(Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_ITEM_MENU);
		add(item);
		return item;
	}

	public ItemMenu getItem(String nome) {
		return mapa.get(nome);
	}

	public void resetOutros(ItemMenu item) {
		List<ItemMenu> itens = mapa.values().stream().filter(i -> !i.getNome().equals(item.getNome()))
				.collect(Collectors.toList());
		itens.forEach(i -> i.rest());
	}

	public void encolherItens() {
		aberto = false;
		botaoMenu.fechar();
		List<ItemMenu> itens = mapa.values().stream().collect(Collectors.toList());
		itens.forEach(i -> {
			i.encolher();
		});

		Vector2f newSize = new Vector2f(Constantes.LARGURA_MENU_FECHADO, this.getSize().y);
		notifica(newSize);
		setSize(newSize);

	}

	public void expandirItens() {
		aberto = true;
		botaoMenu.abrir();
		List<ItemMenu> itens = mapa.values().stream().collect(Collectors.toList());
		itens.forEach(i -> {
			i.expandir();
		});

		Vector2f newSize = new Vector2f(Constantes.LARGURA_MENU_ABERTO, this.getSize().y);
		notifica(newSize);
		setSize(newSize);
	}

	public Button getBotaoMenu() {
		return botaoMenu.getBtMenu();
	}

	public boolean isAberto() {
		return aberto;
	}
	
	public void notifica(Vector2f newSize) {
		if (this.components != null)
			for (Component panel : this.components) {
				EventProcessorProvider.getInstance()
					.pushEvent(
							new MenuChangeSizeEvent<Component>(panel, 
									null,
									MenuLeGui.this.getFrame(), 
									MenuLeGui.this.getSize(), 
									newSize, this.aberto)
							);
			}
	}

}
