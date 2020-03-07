import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Child extends Sprite
{
    private float dy = 0; // The vertical delta...
    private float dx;
    private float gDelta = 0.7f; // Gravity, how much the vDelta will be reduced by over time...
    private ArrayList<Missile> missiles;
    
	public Child(int x, int y)
	{
		super(x,y);
		
		initChild();
	}
	
	public void initChild()
	{
		missiles = new ArrayList<>();
		loadImage("/Images/starchildIDLE.png");
		getImageDimensions();
	}
	public void move()
	{
        // Add the vDelta to the yPos
        y += dy;
        x += dx;
        // Add the gravity to the vDelta, this will slow down
        // the upward movement and speed up the downward movement.
        dy += gDelta;
	}
	
	public void sticking()
	{
		y  += 3;
		dy = 0;
		dx = 0;
	}
	
	public void keyPressed(KeyEvent e)
	{
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D) 
        {
            dy = -8;
            dx = 3;
        }
        
        if (key == KeyEvent.VK_SPACE) 
        {
            fire();
        }
        
        if (key == KeyEvent.VK_A) 
        {
            dy = -8;
            dx = -3;
        }
		
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList getMissiles()
	{
		return missiles;
	}
	
    public void fire() 
    {
        missiles.add(new Missile(x + width, y + height / 2));
    }
	
	public void keyReleased(KeyEvent e)
	{
        int key = e.getKeyCode();
		
 
        if (key == KeyEvent.VK_D) 
        {

            dy = 0;
            dx = 0;
          
        }

        if (key == KeyEvent.VK_A) 
        {
            dy = 0;
            dx = 0;
        }


	}
}
