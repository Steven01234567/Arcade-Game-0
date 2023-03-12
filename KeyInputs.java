package draft;
/*
 * KeyInputs
 * Steven Chen | 2022-12-31
 */

//Imports
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputs extends KeyAdapter {
	//Field
	private MainFrame mf;
	
	//Constructor
	public KeyInputs(MainFrame mf) {
		this.mf = mf;
	}
	
	//Methods
	/**
	 * KeyAdapter Methods
	 */
	public void keyPressed(KeyEvent e) {
		//Player 1
		if (e.getKeyCode() == KeyEvent.VK_W) {
			mf.getP1().setJump(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			mf.getP1().setLeft(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			mf.getP1().setDuck(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			mf.getP1().setRight(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			mf.getP1().setAttack(true);
			mf.setPriority(1);
		}
		
		//Player 2
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			mf.getP2().setJump(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			mf.getP2().setLeft(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			mf.getP2().setDuck(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			mf.getP2().setRight(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_K) {
			mf.getP2().setAttack(true);
			mf.setPriority(2);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mf.fullReset();
		}
		
	}
	public void keyReleased(KeyEvent e) {
		//Player 1
		if (e.getKeyCode() == KeyEvent.VK_W) {
			mf.getP1().setJump(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			mf.getP1().setLeft(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			mf.getP1().setDuck(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			mf.getP1().setRight(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			mf.getP1().setAttack(false);
			mf.setPriority(0);
		}
		
		//Player 2
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			mf.getP2().setJump(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			mf.getP2().setLeft(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			mf.getP2().setDuck(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			mf.getP2().setRight(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_K) {
			mf.getP2().setAttack(false);
			mf.setPriority(0);
		}
	}
}
