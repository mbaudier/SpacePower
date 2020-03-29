package eu.mbaudier.spacepower.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/** Main class assembling the software. */
public class SpacePower {

	public static void main(String[] args) {
		System.out.println("Welcome to SpacePower!!");

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SpacePower");
		shell.setLayout(new FillLayout());
		Image originalImage = null;
		originalImage = new Image(display, "images/SpacePower-Spaceship.png");
		final Image image = originalImage;
		final Point origin = new Point(0, 200);
		final Canvas canvas = new Canvas(shell, SWT.NONE);

		canvas.addListener(SWT.Paint, e -> {
			GC gc = e.gc;
			gc.drawImage(image, origin.x, origin.y);
		});

		shell.setSize(800, 600);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		originalImage.dispose();
		display.dispose();

	}

}
