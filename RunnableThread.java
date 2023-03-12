package draft;
/*
 * RunnableThread
 * Steven Chen | 2023-02-24
 */

//Imports
import java.awt.geom.Rectangle2D;

public class RunnableThread implements Runnable {
	//Field
	private final int interval = 100;
	
	private MainFrame mf;
	
	private Rectangle2D p1hb, p2hb, p1dhb, p2dhb;
	
	//Constructor
	public RunnableThread(MainFrame mf) {
		this.mf = mf;
	}
	
	//Methods
	/**
	 * run() runs constantly while
	 * processing key inputs
	 * 
	 * run: null -> void
	 */
	public void run() {
		do {
			try {
		    	   Thread.sleep(interval);
				} catch (InterruptedException e) {
		    	   e.printStackTrace();
				}
			
			//Player 1
			if (mf.getP1().getJump()) {
				mf.getP1().jump();
				mf.getP1().setJump(false);
			}
			else if (mf.getP1().getDuck()) {
				mf.getP1().duck();
				mf.getP1().setLeft(false);
				mf.getP1().setRight(false);
			}
			else {
				mf.getP1().resetState();
			}
			
			if (mf.getP1().getLeft()) {
				mf.getP1().moveLeft();
			}
			else if (mf.getP1().getRight()) {
				mf.getP1().moveRight();
			}
			
			if (mf.getP1().getAttack()) {
				mf.getP1().attack();
			}
			else {
				mf.getP1().recover();
			}
			
			//Player 2
			if (mf.getP2().getJump()) {
				mf.getP2().jump();
				mf.getP2().setJump(false);
			}
			else if (mf.getP2().getDuck()) {
				mf.getP2().duck();
				mf.getP2().setLeft(false);
				mf.getP2().setRight(false);
			}
			else {
				mf.getP2().resetState();
			}
			
			if (mf.getP2().getLeft()) {
				mf.getP2().moveLeft();
			}
			else if (mf.getP2().getRight()) {
				mf.getP2().moveRight();
			}
			
			if (mf.getP2().getAttack()) {
				mf.getP2().attack();
			}
			else {
				mf.getP2().recover();
			}
			
			p1hb = new Rectangle2D.Double(mf.getP1().getX(), 
										  mf.getP1().getY(), 
										  mf.getP1().getWidth(), 
										  mf.getP1().getHeight());
			p2hb = new Rectangle2D.Double(mf.getP2().getX(), 
										  mf.getP2().getY(), 
										  mf.getP2().getWidth(), 
										  mf.getP2().getHeight());
			p1dhb = new Rectangle2D.Double(mf.getP1().getX() + mf.getP1().getWidth(), 
									       mf.getP1().getY() + mf.getP1().getHeight() / 4, 
										   mf.getP1().getRange(), 
										   mf.getP1().getHeight() / 4);
			p2dhb = new Rectangle2D.Double(mf.getP2().getX() - mf.getP2().getRange(), 
									       mf.getP2().getY() + mf.getP2().getHeight() / 4, 
										   mf.getP2().getRange(), 
										   mf.getP2().getHeight() / 4);
			switch (mf.getPriority()) {
			case 1:
				if (p1dhb.intersects(p2hb)) {
					System.out.println("P1 Wins");
					mf.setWinner(1);
				}
				break;
			case 2:
				if (p2dhb.intersects(p1hb)) {
					System.out.println("P2 Wins");
					mf.setWinner(2);
				}
				break;
			default:
				break;
			}
			
			mf.repaint();
		} while (mf.getWinner() == 0);
	}
}
