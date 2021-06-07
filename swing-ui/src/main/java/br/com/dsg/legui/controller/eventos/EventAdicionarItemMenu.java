package br.com.dsg.legui.controller.eventos;

import br.com.dsg.legui.EventoController;
import br.com.dsg.legui.controller.ActionMenu;
import br.com.dsg.legui.controller.GerarController;
import br.com.dsg.legui.controller.LeGuiController;

public class EventAdicionarItemMenu extends EventoController<LeGuiController>{
	
	protected String nome;
	protected String imageA; 
	protected String imageB;
	protected boolean imageHorizontalAlignRIGHT; 
	protected GerarController<?> cController;
	protected Boolean inicializar;
	protected Boolean desabilitarSelecaoMenu;
	protected ActionMenu<LeGuiController> action;

	public EventAdicionarItemMenu(String nome, String imageA, String imageB,boolean imageHorizontalAlignRIGHT, GerarController<?> cController, Boolean inicializar) {
		super(LeGuiController.get());
		
		this.nome = nome;
		this.imageA = imageA;
		this.imageB = imageB;
		this.imageHorizontalAlignRIGHT = imageHorizontalAlignRIGHT;
		this.cController = cController;
		this.inicializar = inicializar;
		
	}
	
	public EventAdicionarItemMenu(String nome, String imageA, String imageB, boolean imageHorizontalAlignRIGHT, boolean desabilitarSelecaoMenu, ActionMenu<LeGuiController> action) {
		super(LeGuiController.get());
		this.nome = nome;
		this.imageA = imageA;
		this.imageB = imageB;
		this.imageHorizontalAlignRIGHT = imageHorizontalAlignRIGHT;
		this.desabilitarSelecaoMenu = desabilitarSelecaoMenu;
		this.action = action;
	}	
	
	
}
