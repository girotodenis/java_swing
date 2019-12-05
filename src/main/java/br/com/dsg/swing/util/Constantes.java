package br.com.dsg.swing.util;

public class Constantes {
	
	public static int ALTURA_APP = PropertiesUtil.getInt("altura.app");
	public static int LARGURA_APP= PropertiesUtil.getInt("largura.app");
	public static final java.awt.Color COR_FUNDO_APP = new java.awt.Color(255, 255, 255);
	
	
	public static  int LARGURA_MENU_FECHADO = 40;
	public static  int LARGURA_MENU_ABERTO = 120;
	
	//Conf APP_BAR
	public static  int ALTURA_APP_BAR = PropertiesUtil.getInt("altura.app.bar") ;
	public static  int LARGURA_APP_BAR = LARGURA_APP - LARGURA_MENU_ABERTO;
	public static  int LARGURA_APP_BAR_FULL = LARGURA_APP - LARGURA_MENU_FECHADO;
	public static final java.awt.Color COR_FUNDO_APP_BAR = new java.awt.Color(71, 120, 197);
	//Conf APP_BAR
	
	
	
	

}
