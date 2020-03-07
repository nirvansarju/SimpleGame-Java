
public class AlienLine extends Sprite
{
	public AlienLine(int x, int y)
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
		x += 7;
				
		y = x;
		
		if (x > 1920)
		{
			x = 0;
			y = 0;
		}
        
	}

}
