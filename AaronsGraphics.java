import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class AaronsGraphics {
	
	public static void main( String[] args ) {
		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = size.width;
		int screenHeight = size.height;
		
		JFrame graphicWindow;
		graphicWindow = new JFrame("My Drawing Program");
		graphicWindow.setTitle("Drawing Window");
		graphicWindow.setSize(screenWidth, screenHeight);
		graphicWindow.setLocation(1, 1);
		graphicWindow.setVisible(true);
		graphicWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		
		//Create and define a graphics container object
		Container graphicContentPane;
		graphicContentPane = graphicWindow.getContentPane();
		
		//Initialize a graphics object
		Graphics graphicDrawer;
		graphicDrawer = graphicContentPane.getGraphics();
		graphicDrawer.setColor(Color.DARK_GRAY);
		
		Point mousePos = new Point(0,0);
		Point prevMousePos = new Point(0,0);
		for(int i = 0; i < screenWidth-250; i++ ) {
			mousePos = graphicContentPane.getMousePosition();
			if( mousePos == null ) {
				mousePos = prevMousePos;
			}
			graphicDrawer.drawRoundRect( 20+i, 20, 20, 20, 20, 20);
			try { Thread.sleep(10) ; }catch(Exception e) {}
			
			graphicDrawer.drawRoundRect( mousePos.x-10, mousePos.y-10, 20, 20, 20, 20);
			graphicDrawer.drawLine(mousePos.x, mousePos.y, prevMousePos.x, prevMousePos.y);
			drawPath(mousePos,prevMousePos,graphicDrawer);
			
			prevMousePos = mousePos;
		}
	}

	private static void drawPath(Point cur, Point prev, Graphics graphicDrawer) {
		if( cur.equals(prev) ) {
			return;
		}
		int xDif = cur.x - prev.x;
		int yDif = cur.y - prev.y;
		
		int xDir = 1;
		int yDir = 1;
		if( xDif < 0 ) {
			xDir = -1;
		}
		if( yDif < 0 ) {
			yDir = -1;
		}

		if( Math.abs(xDif) == Math.abs(yDif) ) {
			graphicDrawer.drawRoundRect( cur.x-10+xDir, cur.y-10+yDir, 20, 20, 20, 20);
			//recur
			drawPath( new Point(prev.x+xDir, prev.y+yDir), cur, graphicDrawer);
			return;
		}

		if( Math.abs(xDif) > Math.abs(yDif) ) {
			graphicDrawer.drawRoundRect( cur.x-10+xDir, cur.y-10, 20, 20, 20, 20);
			//recur
			drawPath( new Point(prev.x+xDir, prev.y), cur, graphicDrawer);
			return;
		}
		
		if( Math.abs(xDif) < Math.abs(yDif) ) {
			graphicDrawer.drawRoundRect( cur.x-10, cur.y-10+yDir, 20, 20, 20, 20);
			//recur
			drawPath( new Point(prev.x, prev.y+yDir), cur, graphicDrawer);
			return;
		}
		
		return;
	}
}
