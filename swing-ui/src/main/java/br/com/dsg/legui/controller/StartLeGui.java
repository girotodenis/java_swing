package br.com.dsg.legui.controller;

import static org.lwjgl.glfw.GLFW.GLFW_DONT_CARE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetMonitors;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowMonitor;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_STENCIL_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.joml.Vector2i;
import org.liquidengine.legui.DefaultInitializer;
import org.liquidengine.legui.animation.Animator;
import org.liquidengine.legui.animation.AnimatorProvider;
import org.liquidengine.legui.component.Frame;
import org.liquidengine.legui.event.WindowSizeEvent;
import org.liquidengine.legui.listener.WindowSizeEventListener;
import org.liquidengine.legui.system.context.Context;
import org.liquidengine.legui.system.layout.LayoutManager;
import org.liquidengine.legui.system.renderer.Renderer;
import org.liquidengine.legui.theme.Themes;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowCloseCallbackI;
import org.lwjgl.opengl.GL;

import br.com.dsg.legui.componentes.LeGuiView;

public class StartLeGui {

	private static volatile boolean running = false;
	private static long[] monitors = null;
	private static boolean toggleFullscreen = false;
	private static boolean fullscreen = false;

	private static Context context;

	private Frame frame;

	private static StartLeGui instance = null;

	private int WIDTH = 600;
	private int HEIGHT = 400;
	private LeGuiView leGuiView = null;
	private DefaultInitializer initializer = null;
	private long window = 0;

	private LeGuiController leGuiController = null;

	private StartLeGui(int width, int height, String appNome) {

		System.setProperty("joml.nounsafe", Boolean.TRUE.toString());
		System.setProperty("java.awt.headless", Boolean.TRUE.toString());

		if (!glfwInit()) {
			throw new RuntimeException("Can't initialize GLFW");
		}
		
		WIDTH = width;
		HEIGHT = height;
		
		window = glfwCreateWindow(WIDTH, HEIGHT, appNome, NULL, NULL);
		glfwShowWindow(window);

		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glfwSwapInterval(0);

		PointerBuffer pointerBuffer = glfwGetMonitors();
		int remaining = pointerBuffer.remaining();
		monitors = new long[remaining];
		for (int i = 0; i < remaining; i++) {
			monitors[i] = pointerBuffer.get(i);
		}

//		Themes.setDefaultTheme(new FlatColoredTheme(fromInt(245, 245, 245, 1), // backgroundColor
//				fromInt(176, 190, 197, 1), // borderColor
//				fromInt(176, 190, 197, 1), // sliderColor
//				fromInt(100, 181, 246, 1), // strokeColor
//				fromInt(165, 214, 167, 1), // allowColor
//				fromInt(239, 154, 154, 1), // denyColor
//				ColorConstants.transparent(), // shadowColor
//				ColorConstants.darkGray(), // text color
//				FontRegistry.getDefaultFont(), // font
//				16f // font size
//		));
		
		Themes.setDefaultTheme(Themes.FLAT_PETERRIVER_DARK);
		

		// Firstly we need to create frame component for window.
		frame = new Frame(WIDTH, HEIGHT);// new Frame(WIDTH, HEIGHT);

		leGuiView = new LeGuiView(WIDTH, HEIGHT);
		leGuiView.setFocusable(false);
		leGuiView.getListenerMap().addListener(WindowSizeEvent.class,
				(WindowSizeEventListener) event -> leGuiView.setSize(event.getWidth(), event.getHeight()));
		frame.getContainer().add(leGuiView);

		leGuiController = new LeGuiController(leGuiView);
	}

	public static StartLeGui get(int width, int height, String appNome) {
		return instance =  new StartLeGui( width,  height,  appNome) ;
	}

	public void start() {

		// We need to create legui instance one for window
		// which hold all necessary library components
		// or if you want some customizations you can do it by yourself.
		initializer = new DefaultInitializer(window, frame);

		GLFWKeyCallbackI glfwKeyCallbackI = (w1, key, code, action,
				mods) -> running = !(key == GLFW_KEY_ESCAPE && action != GLFW_RELEASE);
		GLFWWindowCloseCallbackI glfwWindowCloseCallbackI = w -> running = false;

		// if we want to create some callbacks for system events you should create and
		// put them to keeper
		//
		// Wrong:
		// glfwSetKeyCallback(window, glfwKeyCallbackI);
		// glfwSetWindowCloseCallback(window, glfwWindowCloseCallbackI);
		//
		// Right:
		initializer.getCallbackKeeper().getChainKeyCallback().add(glfwKeyCallbackI);
		initializer.getCallbackKeeper().getChainWindowCloseCallback().add(glfwWindowCloseCallbackI);

		// Initialization finished, so we can start render loop.
		running = true;

		// Everything can be done in one thread as well as in separated threads.
		// Here is one-thread example.

		// before render loop we need to initialize renderer
		Renderer renderer = initializer.getRenderer();
		Animator animator = AnimatorProvider.getAnimator();
		renderer.initialize();

		context = initializer.getContext();

		while (running) {

			// Also we can do it in one line
			context.updateGlfwWindow();
			Vector2i windowSize = context.getFramebufferSize();

			glClearColor(1, 1, 1, 1);
			// Set viewport size
			glViewport(0, 0, windowSize.x, windowSize.y);
			// Clear screen
			glClear(GL_COLOR_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);

			LayoutManager.getInstance().layout(frame);

			// render frame
			renderer.render(frame, context);

			// poll events to callbacks
			glfwPollEvents();
			glfwSwapBuffers(window);

			animator.runAnimations();

			// Now we need to handle events. Firstly we need to handle system events.
			// And we need to know to which frame they should be passed.
			initializer.getSystemEventProcessor().processEvents(frame, context);

			// When system events are translated to GUI events we need to handle them.
			// This event processor calls listeners added to ui components
			initializer.getGuiEventProcessor().processEvents();
			if (toggleFullscreen) {
				if (fullscreen) {
					glfwSetWindowMonitor(window, NULL, 100, 100, WIDTH, HEIGHT, GLFW_DONT_CARE);
				} else {
					GLFWVidMode glfwVidMode = glfwGetVideoMode(monitors[0]);
					glfwSetWindowMonitor(window, monitors[0], 0, 0, glfwVidMode.width(), glfwVidMode.height(),
							glfwVidMode.refreshRate());
				}
				fullscreen = !fullscreen;
				toggleFullscreen = false;
			}
		}
		// And when rendering is ended we need to destroy renderer
		renderer.destroy();
		glfwDestroyWindow(window);
		glfwTerminate();
	}

	
	public StartLeGui addItemMenu(String nome, String imagem, ActionMenu action) {
		this.leGuiController.addItemMenu( nome,  imagem,  action);
		return this;
	}
	
	public StartLeGui addItemMenu(String nome, String imagem, GerarController cController) {
		this.leGuiController.addItemMenu( nome,  imagem,  cController);
		return this;
	}
	
	public StartLeGui addItemMenu(String nome, String imagem, GerarController cController, Boolean inicializar) {
		this.leGuiController.addItemMenu( nome,  imagem,  cController, inicializar);
		return this;
	}
	
	public StartLeGui fecharMenu() {
		this.leGuiController.fecharMenu();
		return this;
	}

	public static void main(String[] args) {
		StartLeGui.get(800, 600, "App test")
			//.addItemMenu("Home","/imagens/home_house_10811.png", (controllerPai)->new HomeController(controllerPai), Boolean.TRUE)
			//.addItemMenu("Configuração","/imagens/setting-configure.png",  (controllerPai)->new ConfigController(controllerPai))
			.addItemMenu( "Home","imagens/home_house_10811.png",  () -> System.out.println("Item 1") )
			.addItemMenu( "Configuração","imagens/setting-configure.png",  () -> System.out.println("Item 2") )
			.addItemMenu( "Sair","imagens/icons8-exit-sign-64.png",  () -> System.out.println("Sair") )
			.fecharMenu()
			.start();
	}


}
