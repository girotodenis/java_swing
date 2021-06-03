package uia3;

import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.component.Panel;

public class HomeView extends Panel {

   /**
    * 
    */
   private static final long serialVersionUID = -5615207212602152683L;

   public Button botao1;
   public Button botao2;
   public Button botao3;


   public HomeView() {
	   
	   add(this.botao1 = new Button("Botão 1", 50, 0,  50, 30));
	   add(this.botao2 = new Button("Botão 2", 50, 40, 50, 30));
	   add(this.botao3 = new Button("Botão 3", 50, 80, 50, 30));
   }


}
