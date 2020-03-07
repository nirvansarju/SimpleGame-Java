
public class AlienRight extends Sprite
{
	public AlienRight(int x, int y)
	{
		super(x,y);
		
		initBound();
	}
	
	public void initBound()
	{
		loadImage("/Images/ALIEN_LINE.png");
		getImageDimensions();	
	}
	
	public void move()
	{
		x -= 7;	
		y += 7;
		
		if (x < 0)
		{
			x = 1890;
			y = 0;
		}
        
	}

}
