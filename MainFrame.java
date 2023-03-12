package draft;
/*
 * MainFrame
 * Steven Chen | 2023-02-24
 */

//Imports
import javax.swing.JFrame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class MainFrame extends JFrame {
	//Field
	private static final long serialVersionUID = 1L;
	
	private final int width = 1000, height = 500;
	
	private Character p1, p2;
	private int priority = 0, winner = 0;
	
	private KeyInputs ki;
	
	private Thread t1;
	
	//Constructor
	/**
	 * A MainFrame is a JFrame
	 */
	public MainFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setVisible(true);
		
		p1 = new Character(this, 1);
		p2 = new Character(this, 2);
		
		ki = new KeyInputs(this);
		addKeyListener(ki);
		
		t1 = new Thread(new RunnableThread(this));
	    t1.start();
	}
	
	//Methods
	/**
	 * paint(Graphics g) consumes a Graphics g, and
	 * paints all components onto the MainFrame
	 * 
	 * paint: Graphics -> void
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		//Sky
		g2.setColor(Color.CYAN);
		g2.fillRect(0, 0, width, height);
		//Ground
		g2.setColor(Color.GRAY);
		g2.fillRect(0, height-100, width, 100);
		
		//Characters
		if (priority == 2) {
			paintCharacter(g2, p1);
			paintCharacter(g2, p2);
		} else {
			paintCharacter(g2, p2);
			paintCharacter(g2, p1);
		}
	}
	/**
	 * paintCharacter(Graphics2D g2, Character c)
	 * consumes a Graphics2D g2 and Character c, and
	 * paints the characters onto the MainFrame
	 * 
	 * paintCharacter: Graphics2D Character -> void
	 */
	public void paintCharacter(Graphics2D g2, Character c) {
		switch (c.getNum()) {
		case 1:
			g2.setColor(Color.ORANGE);
			//g2.fillRect(c.getX()+c.getWidth(), c.getY() + c.getHeight() / 4, c.getRange(), c.getHeight() / 4);
			g2.setColor(Color.RED);
			break;
		case 2:
			g2.setColor(Color.GREEN);
			//g2.fillRect(c.getX()-c.getRange(), c.getY() + c.getHeight() / 4, c.getRange(), c.getHeight() / 4);
			g2.setColor(Color.BLUE);
			break;
		default:
			break;
		}
		paintCharacterHelp(g2, c, g2.getColor());
	}
	/**
	 * paintCharacterHelp(Graphics g2, Character c, Color c1)
	 * helper for paintCharacter
	 */
	public void paintCharacterHelp(Graphics g2, Character c, Color c1) {
		((Graphics2D) g2).setStroke(new BasicStroke(5));
		g2.setColor(c1);
		// Tag
		int[] triX = {c.getX() + c.getWidth() / 2,
					  c.getX() + c.getWidth() / 2 + 15,
					  c.getX() + c.getWidth() / 2 - 15};
		int[] triY = {c.getY() - 10,
				  	  c.getY() - 20,
				  	  c.getY() - 20};
		g2.fillPolygon(triX, triY, triX.length);
		//g2.fillRect(c.getX(), c.getY(), c.getWidth(), c.getHeight());
		// Sword
		if (c.getNum() == 1) {
			g2.drawLine(c.idleX[10] + c.getX(),
						c.idleY[10] + c.getY(),
						c.idleX[10] + c.getX() + 50,
						c.idleY[10] + c.getY() - 10);
		} else if (c.getNum() == 2) {
			g2.drawLine(c.idleX[10] + c.getX(),
						c.idleY[10] + c.getY(),
						c.idleX[10] + c.getX() - 50,
						c.idleY[10] + c.getY() - 10);
		}
		g2.setColor(Color.BLACK);
		// Head
		g2.fillOval(c.getX() + c.getWidth() / 2 - (int)c.getHead().getWidth() / 2,
					c.getY() + (int)c.getHead().getY(),
					(int)c.getHead().getWidth(),
					(int)c.getHead().getHeight());
		// Body
		g2.drawPolyline(incIntArr(c.idleX, c.getX()), 
						incIntArr(c.idleY, c.getY()),
						c.idleX.length);
	}
	
	/**
	 * incIntArr(intArr, inc) increments each element in intArr by inc
	 * 
	 * incIntArr: int[] int -> int[]
	 */
	public int[] incIntArr(int[] intArr, int inc) {
		int[] toReturn = new int[intArr.length];
		for (int i = 0; i < intArr.length; i++) {
			toReturn[i] = intArr[i] + inc;
		}
		return toReturn;
 	}
	
	/**
	 * getX() returns X
	 * 
	 * getX: null -> X
	 */
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Character getP1() {
		return p1;
	}
	public Character getP2() {
		return p2;
	}
	public int getPriority() {
		return priority;
	}
	public int getWinner() {
		return winner;
	}
	
	/**
	 * setX(X x) consumes X x, and
	 * changes X so that it is equal to x
	 * 
	 *  setX: X -> void
	 */
	public void setPriority(int i) {
		priority = i;
	}
	public void setWinner(int i) {
		winner = i;
	}
	
	/**
	 * fullReset()
	 * fully resets to a new game
	 * 
	 * fullReset: null -> void
	 */
	public void fullReset() {
		p1 = new Character(this, 1);
		p2 = new Character(this, 2);
		priority = 0;
		repaint();
		
		if (winner != 0) {
			t1 = new Thread(new RunnableThread(this));
			t1.start();
		}
		winner = 0;
	}
	
	/**
	 * main(String[] args) consumes a String[] args, and
	 * creates a new MainFrame
	 * 
	 * main: String[] -> void
	 */
	public static void main(String[] args) {
		new MainFrame();
		
		//Thread t1 = new Thread(new RunnableThread(mf));
	    //t1.start();
	}
}
