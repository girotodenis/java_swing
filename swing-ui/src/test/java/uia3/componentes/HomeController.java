package uia3.componentes;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.componentes.LeGuiView;
import br.com.dsg.legui.controller.LeGuiEventos;

/**
 * @author Denis Giroto
 *
 */
public class HomeController extends AbstractController<HomeView> {

	private final static Logger LOG = Logger.getLogger(HomeController.class);

	class MeuEvento {

		public MeuEvento(String valor, JPanel target) {
			((JLabel) target.getComponent(0)).setText(valor);
		}

	}


	public HomeController(AbstractController<?> controlerPai) {
		super(controlerPai, new HomeView());

		registerAction(getPanel().botao1, (event) -> LeGuiEventos.irPara( new ConfigController(this) ));

		registerTarefa(getPanel().botao2, (event) -> {
			try {
				for (int i = 0; i <= 100; i++) {
					Thread.sleep(50);
					LeGuiEventos.atualizarProgressBar( i );
				}
				Thread.sleep(500);
			} catch (Exception e) {
			}
		});

		registerAction(getPanel().botao3, (event) -> {
			// Dialog dialog = new Dialog("Erro", 300, 100);
			// dialog.setDraggable(false);
			// dialog.setResizable(false);
			// Label erro = new Label("botão 3", 10, 10, 300, 20);
			// dialog.getContainer().add(erro);
			// dialog.show(getPanel().getFrame());
			Display d = new Display();
			Shell s = new Shell(d);
			s.setSize(400, 400);
			FileDialog fd = new FileDialog(s, SWT.MULTI);
			fd.setText("Open");
			fd.setFilterPath("C:/");
			String[] filterExt = { "*.txt", "*.doc", ".rtf", "*.*" };
			fd.setFilterExtensions(filterExt);
			String selected = fd.open();
			System.out.println(selected);
			String[] selectedFileNames = fd.getFileNames();
			System.out.println(fd.getFilterPath());
			for(String fileName : selectedFileNames) {
				System.out.println("  " + fileName);
			}
			d.close();
		});
		
		
		registerAction(getPanel().botao4, (event) -> {
			((LeGuiView) getControllerPai().getPanel()).getMenu().esconder();
		});
		
		registerAction(getPanel().botao5, (event) -> {
			((LeGuiView) getControllerPai().getPanel()).getMenu().exibir();
		});


	}

}
