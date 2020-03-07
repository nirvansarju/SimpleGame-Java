import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;
	
	private Timer timer;
	private Child child;
	private BoundBox box1;
	private BoundBox box2;
	
	private ArrayList<Alien> aliens;
	private ArrayList<AlienLine> alienLines;
	private ArrayList<AlienRight> rights;
	private ArrayList<AlienGuard> alienGuards;
	private ArrayList<AlienMother> alienMothers;
	
	private ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/Images/starjumpbackground.png"));
	private Image image = ii.getImage();
	
	
	private final int ICHILD_X = 960;
	private final int ICHILD_Y = 800;
	private final int B_WIDTH = 1920;
	private final int B_HEIGHT = 1080;
	private final int DELAY = 15;
	
	private int hitCount = 0;
	private int hitCountGuard = 0;
	private int AliensLeft = 42; 
	
	private final int[][]pos = {{0,0},{150,150},{300,300},{450,450}};
	private final int[][]Linepos = {{50,50},{150,150},{300,300},{450,450}};
	private final int[][]rightPos = {{1880,50},{1780,150},{1680,300},{1580,450}};
	private final int[][]guardPos = {{50,100},{100,100},{50,100},{150,100},{200,100},{250,100},{300,100},{350,100},{400,100},{450,100},{500,100},
									 {550,100},{600,100},{650,100},{700,100},{750,100},{800,100}};
	private final int[][]motherPos ={{0,20},{150,20},{300,20},{450,20},{600,20},{750,20},{900,20},{1050,20},{1200,20},{1350,20},{1500,20},
									 {1650,20},{1800,20}};
	
	boolean ingame = true;
	boolean win = false;
    
	public Board()
	{
		initBoard();
	}
	
	public void initBoard()
	{
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		child = new Child(ICHILD_X, ICHILD_Y);
		
		initAliens();
		initAlienLines();
		initAlienRights();
		initAlienGuards();
		initAlienMothers();
		
		box1 = new BoundBox(B_WIDTH,0);
		box2 = new BoundBox(-400,0);

		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void initAliens() //CREATE A LIST OF ALIEN OBJECTS, ALIENS TAKE INITITAL POSITION FROM POS ARRAY
	{
		aliens = new ArrayList<>();
		
		for (int[] p : pos)
		{
			aliens.add(new Alien(p[0], p[1]));
		}
	}
	
	public void initAlienLines() //CREATE A LIST OF ALIEN OBJECTS, ALIENS TAKE INITITAL POSITION FROM POS ARRAY
	{
		alienLines = new ArrayList<>();
		
		for (int[] p : Linepos)
		{
			alienLines.add(new AlienLine(p[0], p[1]));
		}
	}
	
	public void initAlienRights() //CREATE A LIST OF ALIEN OBJECTS, ALIENS TAKE INITITAL POSITION FROM POS ARRAY
	{
		rights = new ArrayList<>();
		
		for (int[] p : rightPos)
		{
			rights.add(new AlienRight(p[0], p[1]));
		}
	}
	
	public void initAlienGuards()
	{
		alienGuards = new ArrayList<>();
		
		for (int[] p : guardPos)
		{
			alienGuards.add(new AlienGuard(p[0], p[1]));
		}
	}
	
	public void initAlienMothers() //CREATE A LIST OF ALIEN OBJECTS, ALIENS TAKE INITITAL POSITION FROM POS ARRAY
	{
		alienMothers = new ArrayList<>();
		
		for (int[] p : motherPos)
		{
			alienMothers.add(new AlienMother(p[0], p[1]));
		}
	}
	
	
    public Dimension getPreferredSize()
    {
        return new Dimension(B_WIDTH, B_HEIGHT);
    }

	public void paintComponent(Graphics g) //DRAW SPRITES AND GAME OVER SCREEN DEPENDING ON ingame
	{
		super.paintComponent(g);

		if(ingame)
		{
			drawObjects(g);
		}
		else 
		{
			drawGameOver(g);
		}
		
		if (AliensLeft == 0)
		{
			child.setVisible(false);
			drawYouWin(g);
		}

		Toolkit.getDefaultToolkit().sync();
	}
	
	private void drawObjects(Graphics g)//DRAWS GAME SPRITES ONTO WINDOW
	{
			g.drawImage(image,0,0,this);
			g.drawImage(child.getImage(), child.getX(), child.getY(), this);
			g.drawImage(box1.getImage(),box1.getX(),box1.getY(),this);
			g.drawImage(box2.getImage(),box2.getX(),box2.getY(),this);
			
			@SuppressWarnings("unchecked")
			ArrayList<Missile> ms = child.getMissiles();
			
			for (Missile m : ms)
			{//draw all missiles
				if (m.isVisible())
				{
					g.drawImage(m.getImage(), m.getX(), m.getY(), this);
				}
			}
			
	        for (Alien a : aliens) 
	        {//draw all aliens, unless they have already been destroyed (isVisible)
	            if (a.isVisible()) 
	            {
	                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
	            }
	        }
	        
	        for (AlienLine a : alienLines) 
	        {//draw all aliens, unless they have already been destroyed (isVisible)
	            if (a.isVisible()) 
	            {
	                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
	            }
	        }
	        
	        for (AlienRight a : rights) 
	        {//draw all aliens, unless they have already been destroyed (isVisible)
	            if (a.isVisible()) 
	            {
	                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
	            }
	        }
	        
	        for (AlienGuard a : alienGuards) 
	        {//draw all aliens, unless they have already been destroyed (isVisible)
	            if (a.isVisible()) 
	            {
	                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
	            }
	        }
	        
	        for (AlienMother a : alienMothers) 
	        {//draw all aliens, unless they have already been destroyed (isVisible)
	            if (a.isVisible()) 
	            {
	                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
	            }
	        }

	        g.setColor(Color.WHITE); //draw the number of aliens left
	        g.drawString("Aliens left: " + (AliensLeft) , 5, 15);
	        
	}

	private void drawGameOver(Graphics g)//draw game over in the middle of the window
	{
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);
		
		g.setColor(Color.WHITE);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - fm.stringWidth(msg))/2, B_HEIGHT/2);
		
	}
	
	private void drawYouWin(Graphics g)//draw game over in the middle of the window
	{
		String msg = "YOU WIN";
		Font small = new Font("Helvetica", Font.BOLD, 50);
		FontMetrics fm = getFontMetrics(small);
		
		g.setColor(Color.WHITE);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - fm.stringWidth(msg))/2, B_HEIGHT/2);

		
	}
	
	private void updateChild()
	{
		Rectangle r3 = child.getBounds();

		if(child.isVisible())
		{
			child.move();
		}
		
		if (child.getY()+21.2+(child.getHeight()) > 1080)
		{
			ingame = false;
		}
		Rectangle r1 = box1.getBounds();
		
		if (r3.intersects(r1))
		{
			child.sticking();
		}
		
		Rectangle r4 = box2.getBounds();
		
		if (r3.intersects(r4))
		{
			child.sticking();
		}
		
	}
	
	private void updateMissiles()
	{
		@SuppressWarnings("unchecked")
		ArrayList<Missile> ms  = child.getMissiles();
		
		for (int i = 0; i <ms.size(); i++)
		{
			Missile m = ms.get(i);
			
			if (m.isVisible())
			{
				m.move();
			}
			else
			{
				ms.remove(i);
			}
		}
	}
	
	public void updateAliens()
	{
		if(aliens.isEmpty() && alienLines.isEmpty() && rights.isEmpty() && alienMothers.isEmpty() && alienGuards.isEmpty())
		{
			win = true;
			return;
		}
		
		for (int i = 0; i < aliens.size(); i++)
		{
			Alien a = aliens.get(i);
			
			if(a.isVisible())
			{
				a.move();
			}
			else
			{
				aliens.remove(i);
			}
		}
	}
	
	public void updateAlienLines()
	{
		if(aliens.isEmpty() && alienLines.isEmpty() && rights.isEmpty() && alienMothers.isEmpty() && alienGuards.isEmpty())
		{
			win = true;
			return;
		}
		
		for (int i = 0; i < alienLines.size(); i++)
		{
			AlienLine a = alienLines.get(i);
			
			if(a.isVisible())
			{
				a.move();
			}
			else
			{
				alienLines.remove(i);
			}
		}
	}
	
	public void updateAlienRights()
	{
		if(aliens.isEmpty() && alienLines.isEmpty() && rights.isEmpty() && alienMothers.isEmpty() && alienGuards.isEmpty())
		{
			win = true;
			return;
		}
		
		for (int i = 0; i < rights.size(); i++)
		{
			AlienRight a = rights.get(i);
			
			if(a.isVisible())
			{
				a.move();
			}
			else
			{
				rights.remove(i);
			}
		}
	}
	
	public void updateAlienGuards()
	{
		if(aliens.isEmpty() && alienLines.isEmpty() && rights.isEmpty() && alienMothers.isEmpty() && alienGuards.isEmpty())
		{
			win = true;
			return;
		}
		
		for (int i = 0; i < alienGuards.size(); i++)
		{
			AlienGuard a = alienGuards.get(i);
			
			if(a.isVisible())
			{
				a.move();
			}
			else
			{
				alienGuards.remove(i);
			}
		}
	}
	
	public void updateAlienMothers()
	{
		if(aliens.isEmpty() && alienLines.isEmpty() && rights.isEmpty() && alienMothers.isEmpty())
		{
			win = true;
			return;
		}
		
		for (int i = 0; i < alienMothers.size(); i++)
		{
			AlienMother a = alienMothers.get(i);
			
			if(a.isVisible())
			{
				a.move();
			}
			else
			{
				alienMothers.remove(i);
			}
		}
	}
	
	public void checkCollisions()//check for possible collisions. using hitboxes
	{
		Rectangle r3 = child.getBounds();
		
		for (Alien alien: aliens)
		{
			Rectangle r2 = alien.getBounds();
			
			if (r3.intersects(r2))
			{
				child.setVisible(false);
				alien.setVisible(false);
				ingame = false;
			}
		}
		
		for (AlienLine alienLine: alienLines)
		{
			Rectangle r4 = alienLine.getBounds();
			
			if (r3.intersects(r4))
			{
				child.setVisible(false);
				alienLine.setVisible(false);
				ingame = false;
			}
		}
		
		for (AlienRight alienRight: rights)
		{
			Rectangle r2 = alienRight.getBounds();
			
			if (r3.intersects(r2))
			{
				child.setVisible(false);
				alienRight.setVisible(false);
				ingame = false;
			}
		}
		
		for (AlienGuard alienGuard: alienGuards)
		{
			Rectangle r2 = alienGuard.getBounds();
			
			if (r3.intersects(r2))
			{
				child.setVisible(false);
				alienGuard.setVisible(false);
				ingame = false;
			}
		}
		
		for (AlienMother alienMother: alienMothers)
		{
			Rectangle r2 = alienMother.getBounds();
			
			if (r3.intersects(r2))
			{
				child.setVisible(false);
				alienMother.setVisible(false);
				ingame = false;
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		@SuppressWarnings("unchecked")
		ArrayList<Missile> ms = child.getMissiles();
		
		for (Missile m: ms) //CHECK COLLISION BETWEEN MISSILES AND ALIENS
		{
			Rectangle r1 = m.getBounds();
			
			for(Alien alien: aliens)
			{
				Rectangle r2 = alien.getBounds();
				
				if (r1.intersects(r2))
				{
					m.setVisible(false);
					alien.setVisible(false);
					AliensLeft -= 1;
				}
			}
		}
		
		for (Missile m: ms) //CHECK COLLISION BETWEEN MISSILES AND ALIENS
		{
			Rectangle r1 = m.getBounds();
			
			for(AlienLine alien: alienLines)
			{
				Rectangle r2 = alien.getBounds();
				
				if (r1.intersects(r2))
				{
					m.setVisible(false);
					alien.setVisible(false);
					AliensLeft -= 1;
				}
			}
		}
		
		for (Missile m: ms) //CHECK COLLISION BETWEEN MISSILES AND ALIENS
		{
			Rectangle r1 = m.getBounds();
			
			for(AlienRight alien: rights)
			{
				Rectangle r2 = alien.getBounds();
				
				if (r1.intersects(r2))
				{
					m.setVisible(false);
					alien.setVisible(false);
					AliensLeft -= 1;
				}
			}
		}
		
		for (Missile m: ms) //CHECK COLLISION BETWEEN MISSILES AND ALIENS
		{
			Rectangle r1 = m.getBounds();
			
			for(AlienGuard alien: alienGuards)
			{
				Rectangle r2 = alien.getBounds();
				
				if (r1.intersects(r2))
				{
					hitCountGuard++;
					
					if (hitCountGuard == 4)
					{
						m.setVisible(false);
						alien.setVisible(false);	
						AliensLeft -= 1;
						hitCountGuard = 0;
					}
					else
					{
						m.setVisible(false);
						alien.setVisible(true);	
					}
				}
			}
		}
		
		for (Missile m: ms) //CHECK COLLISION BETWEEN MISSILES AND ALIENS
		{
			Rectangle r1 = m.getBounds();
			
			for(AlienMother alienMother: alienMothers)
			{
				Rectangle r2 = alienMother.getBounds();
				
				if (r1.intersects(r2))
				{
					hitCount++;
					
					if (hitCount == 2)
					{
						m.setVisible(false);
						alienMother.setVisible(false);	
						AliensLeft -= 1;
						hitCount = 0;
					}
					else
					{
						m.setVisible(false);
						alienMother.setVisible(true);	
					}
				}
			}
		}
		
	}
	
	private class TAdapter extends KeyAdapter
	{	
		public void keyPressed(KeyEvent e)
		{
			child.keyPressed(e);	
		}
	}
	
	
	public void actionPerformed(ActionEvent e)//stage one game cycle as a set of actions
	{
		updateChild();
		updateMissiles();
		updateAliens();
		updateAlienLines();
		updateAlienRights();
		updateAlienGuards();
		updateAlienMothers();
		
		checkCollisions();
		
		repaint();
	}
	
}
