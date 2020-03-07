
public class Alien extends Sprite
{
	public Alien(int x, int y)
	{
		super(x,y);
		
		initBound();
	}
	
	public void initBound()
	{
		loadImage("/Images/SpriteAlien.png");
		getImageDimensions();	
	}
	
	public void move()
	{
		x+= 7;
				
		y += 5*Math.cos(Math.PI*(x/100));
		
		if (x > 1920)
		{
			x = 0;
			y += 2;
		}
        
	}
}
