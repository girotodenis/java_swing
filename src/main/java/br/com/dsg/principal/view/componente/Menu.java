package br.com.dsg.principal.view.componente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.SwingUtilities;

import br.com.dsg.swing.tela.layout.AbsoluteConstraints;
import br.com.dsg.swing.tela.layout.AbsoluteLayout;
import br.com.dsg.swing.util.Constantes;
import br.com.dsg.swing.util.PropertiesUtil;

public class Menu extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3275627456468898718L;

	private Map<String, ItemMenu> mapa = new HashMap<String, ItemMenu>();

	private NomeSistema nomeSistema;
	
//	private boolean menuAberto = true;
//	
//	private class NomeSistemaAdapter extends java.awt.event.MouseAdapter {
//		public void mousePressed(java.awt.event.MouseEvent evt) {
//        	if(menuAberto = !menuAberto) {
//        		expandeItens();
//        	}else {
//        		encolheItens();
//        	}
//        	SwingUtilities.invokeLater(new Runnable() {
//				public void run() {
//					atualizar();
//				}
//			});
//        }
//    }
	
	public Menu() {
		setBackground(Constantes.COR_FUNDO_MENU);
		setLayout(new AbsoluteLayout());
		
		nomeSistema = new NomeSistema(PropertiesUtil.get("sistema"), 14);
		add(nomeSistema, new AbsoluteConstraints(0, 0, Constantes.LARGURA_MENU_ABERTO, -1));
	}

	public void addItem(ItemMenu item) {
		mapa.put(item.getName(), item);
		item.setOrdem(mapa.size());

		add(item, new AbsoluteConstraints(0, item.getPosicao(), Constantes.LARGURA_MENU_ABERTO, -1));
	}

	public ItemMenu getItem(String nome) {
		return mapa.get(nome);
	}

	public void resetOutros(ItemMenu item) {
		List<ItemMenu> itens = mapa.values().stream().filter(i -> !i.getName().equals(item.getName()))
				.collect(Collectors.toList());
		itens.forEach(i -> i.rest());
	}

	public void encolheItens() {
		
		remove(nomeSistema);
		nomeSistema = new NomeSistema(PropertiesUtil.get("sigla"), 12);
		add(nomeSistema, new AbsoluteConstraints(0, 0, Constantes.LARGURA_MENU_FECHADO, Constantes.ALTURA_ITEM_MENU));
		
		List<ItemMenu> itens = mapa.values().stream().collect(Collectors.toList());
		itens.forEach(i -> {

			remove(i);
			add(i, new AbsoluteConstraints(0, i.getPosicao(), Constantes.LARGURA_MENU_FECHADO, -1));
		});
	}

	public void expandeItens() {
		
		remove(nomeSistema);
		nomeSistema = new NomeSistema(PropertiesUtil.get("sistema"), 14);
		add(nomeSistema, new AbsoluteConstraints(0, 0, Constantes.LARGURA_MENU_ABERTO, Constantes.ALTURA_ITEM_MENU));
		
		List<ItemMenu> itens = mapa.values().stream().collect(Collectors.toList());
		itens.forEach(i -> {

			remove(i);
			add(i, new AbsoluteConstraints(0, i.getPosicao(), Constantes.LARGURA_MENU_ABERTO, -1));
		});
	}
	
	public void atualizar() {
		this.invalidate();
		this.validate();
		this.repaint();
	}

	public NomeSistema getNomeSistema() {
		return nomeSistema;
	}
	
	

}
