package draft;
/*
 * Character
 * Steven Chen | 2023-02-24
 */

//Imports
import java.awt.geom.*;

public class Character {
	//Field
	private MainFrame mf;
	private int num;
	private int x, y, width = 50, height = 200;
	private int orix, range;
	private final int oriy, oriw = width, orih = height, speed = 50;
	private Ellipse2D head = new Ellipse2D.Double(0, 0, 50, 50);
	int[] idleX = new int[16]; 
			  /*= {0,				// 0. Back foot
				   width * 2 / 5,	// 1. Back knee
			       width / 2,		// 2. Pelvis
			       width / 2,		// 3. Neck
			       width / 2,		// 4. Chest
			       width / 5,		// 5. Back elbow 
			       0,				// 6. Back hand
			       width / 5,		// 7. Back elbow 
			       width / 2,		// 8. Chest
			       width * 3 / 5,	// 9. Front elbow
			       width,			// 10. Front hand
			       width * 3 / 5,	// 11. Front elbow
			       width / 2,		// 12. Chest
			       width / 2,		// 13. Pelvis
			       width * 3 / 4,	// 14. Front knee
			       width};			// 15. Front foot*/
	int[] idleY = new int[16];
			     /*{height,			// 0. Back foot
				   height * 4 / 5,	// 1. Back knee
				   height * 5 / 8,	// 2. Pelvis
				   height / 4,		// 3. Neck
				   height * 2 / 5,	// 4. Chest
				   height / 2,		// 5. Back elbow 
				   height * 5 / 8,	// 6. Back hand
				   height / 2,		// 7. Back elbow 
				   height * 2 / 5,	// 8. Chest
				   height / 2,		// 9. Front elbow
				   height * 9 / 20,	// 10. Front hand
				   height / 2,		// 11. Front elbow
				   height * 2 / 5,	// 12. Chest
				   height * 5 / 8,	// 13. Pelvis
				   height * 3 / 4,	// 14. Front knee
				   height};			// 15. Front foot*/
	
	private boolean left, right, jump, duck, attack;
	
	//Constructor
	public Character(MainFrame mf, int num) {
		this.mf = mf;
		this.num = num;
		
		//width = 50;
		//oriw = width;
		//height = 200;
		//orih = height;
		setIdleX();
		setIdleY();
		switch (num) {
		case 1:
			x = 100;
			break;
		case 2:
			x = mf.getWidth() - width - 100;
			for (int i = 0; i < idleX.length; i++) {
				idleX[i] = width - idleX[i];
			}
			break;
		}
		orix = x;
		y = mf.getHeight() - height - 100;
		oriy = y;
		
		left = false;
		right = false;
		jump = false;
		duck = false;
		
		attack = false;
	}
	
	//Methods
	/**
	 * getX() returns X
	 * 
	 * getX: null -> X
	 */
	public int getNum() {
		return num;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getRange() {
		return range;
	}
	public int getOriH() {
		return orih;
	}
	public boolean getLeft() {
		return left;
	}
	public boolean getRight() {
		return right;
	}
	public boolean getJump() {
		return jump;
	}
	public boolean getDuck() {
		return duck;
	}
	public boolean getAttack() {
		return attack;
	}
	public Ellipse2D getHead() {
		return head;
	}
	
	/**
	 * setX(X x) consumes X x, and
	 * changes X so that it is equal to x
	 * 
	 *  setX: X -> void
	 */
	public void setX(int i) {
		x = i;
	}
	public void setY(int i) {
		y = i;
	}
	public void setWidth(int i) {
		width = i;
	}
	public void setHeight(int i) {
		height = i;
	}
	public void setLeft(Boolean b) {
		left = b;
	}
	public void setRight(Boolean b) {
		right = b;
	}
	public void setJump(Boolean b) {
		jump = b;
	}
	public void setDuck(Boolean b) {
		duck = b;
	}
	public void setAttack(Boolean b) {
		attack = b;
	}
	
	/**
	 * resetState()
	 * resets state to oriy and orih
	 * 
	 * resetState: null -> void
	 */
	public void resetState() {
		y = oriy;
		height = orih;
		
		setIdleY();
	}
	/**
	 * duck()
	 * makes the Character "duck"
	 * 
	 * duck: null -> void
	 */
	public void duck() {
		y = oriy*3/2;
		height = orih/2;
		
		setIdleY();
	}
	/**
	 * jump()
	 * makes the Character "jump"
	 * 
	 * jump: null -> void
	 */
	public void jump() {
		height = orih/2;
		
		setIdleY();
	}
	/**
	 * moveLeft()
	 * makes the Character move to the left
	 * 
	 * moveLeft: null -> void
	 */
	public void moveLeft() {
		x = Math.max(x-speed, 0); 
		orix = x;
	}
	/**
	 * moveRight()
	 * makes the Character move to the right
	 * 
	 * moveRight: null -> void
	 */
	public void moveRight() {
		x = Math.min(x+speed, mf.getWidth()-width);
		orix = x;
	}
	
	/**
	 * recover()
	 * makes the Character recover from an attack
	 * 
	 * recover: null -> void
	 */
	public void recover() {
		range = 0;
		x = orix;
		width = oriw;
		
		setIdleX();
	}
	/**
	 * attack()
	 * makes the Character perform an "attack"
	 * 
	 * attack: null -> void
	 */
	public void attack() {
		range = 50;
		width = oriw+range;
		
		if (num == 1) {
			x = Math.min(orix, mf.getWidth()-width-range);
		}
		else {
			x = Math.max(orix - 50, range);
		}
		
		setIdleX();
	}
	/**
	 * setIdleX() adjusts idleX to fit current conditions
	 * 
	 * setIdles: void -> null
	 */
	public void setIdleX() {
		for (int i = 0; i < idleX.length; i++) {
			switch (i) {
			case 0:	// Back foot
			case 6: // Back hand
				idleX[i] = 0;
				break;
			case 1:	// Back knee
				idleX[i] = width * 2 / 5;
				break;
			case 2:		// Pelvis
			case 13:
			case 3:		// Neck
			case 4:		// Chest
			case 8:
			case 12:
				idleX[i] = width / 2;
				break;
			case 5:		// Back elbow
			case 7:
				idleX[i] = width / 5;
				break;
			case 9: 	// Front elbow
			case 11:
				idleX[i] = width * 3 / 5;
				break;
			case 10:	// Front hand
			case 15:	// Front foot
				idleX[i] = width;
				break;
			case 14:	// Front knee
				idleX[i] = width * 3 / 4;
				break;
			}
			if (num == 2) {
				idleX[i] = width - idleX[i];
			}
		}
	}
	/**
	 * setIdleY() adjusts idleY to fit current conditions
	 * 
	 * setIdles: void -> null
	 */
	public void setIdleY() {
		for (int i = 0; i < idleY.length; i++) {
			switch (i) {
			case 0:		// Front foot
			case 15:	// Back foot
				idleY[i] = height;
				break;
			case 1:		// Back knee
				idleY[i] = height * 4 / 5;
				break;
			case 2:		// Pelvis
			case 13:
			case 6:		// Back hand
				idleY[i] = height * 5 / 8;
				break;
			case 3:		// Neck
				idleY[i] = height / 4;
				break;
			case 4:		// Chest
			case 8:
			case 12:
				idleY[i] = height * 2 / 5;
				break;
			case 5:		// Back elbow
			case 7:
			case 9:		// Front elbow
			case 11:
				idleY[i] = height / 2;
				break;
			case 10:	// Front hand
				idleY[i] = height * 9 / 20;
				break;
			case 14:	// Front knee
				idleY[i] = height * 3 / 4;
				break;
			}
		}
	}
}
