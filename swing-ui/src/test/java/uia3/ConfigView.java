package uia3;

import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.component.Panel;

/**
 *
 * @author dgiroto
 */
public class ConfigView extends Panel {

	public Button btCancelar;

	public ConfigView() {

		add(this.btCancelar = new Button("Cancelar", 50, 50, 50, 30));

	}

}