package eu.mbaudier.spacepower.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/** Main class assembling the software. */
public class SpacePower implements KeyListener {
	private Canvas canvas;
	private Image spaceShipImage;

	private int spaceShipY = 0;
	private int spaceShipYmove = 50;

	public SpacePower(Shell shell) {
		Display display = shell.getDisplay();
		canvas = new Canvas(shell, SWT.NONE);
		spaceShipImage = new Image(display, "images/SpacePower-Spaceship.png");

		canvas.addListener(SWT.Paint, e -> {
			GC gc = e.gc;
			paintScreen(gc);
		});

		canvas.addKeyListener(this);
	}

	public void paintScreen(GC gc) {
		int canvasHeight = canvas.getSize().y;

		// Space ship
		// in case canvas has been resized we reset space ship Y
		int imageHeight = spaceShipImage.getBounds().height;
		if (spaceShipY >= (canvasHeight - imageHeight))
			spaceShipY = canvasHeight - imageHeight;
		gc.drawImage(spaceShipImage, 0, spaceShipY);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println(e);
		if (e.keyCode == 0x1000001) {// up
			if (spaceShipY <= 0)
				return;
			spaceShipY = spaceShipY - spaceShipYmove;
		} else if (e.keyCode == 0x1000002) {// down
			int canvasHeight = canvas.getSize().y;
			int imageHeight = spaceShipImage.getBounds().height;
			if (spaceShipY >= (canvasHeight - imageHeight - spaceShipYmove))
				return;
			spaceShipY = spaceShipY + spaceShipYmove;
		}
		System.out.println("spaceShipY=" + spaceShipY);

		// repaint canvas
		canvas.redraw();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public void dispose() {
		spaceShipImage.dispose();
	}

	public static void main(String[] args) {
		System.out.println("Welcome to SpacePower!!");

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SpacePower");
		shell.setLayout(new FillLayout());
		SpacePower spacePower = new SpacePower(shell);

		shell.setSize(800, 600);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		spacePower.dispose();
		display.dispose();

	}

}
