
public class AlienGuard extends Sprite
{
	public AlienGuard(int x, int y)
	{
		super(x,y);
		
		initBound();
	}
	
	public void initBound()
	{
		loadImage("/Images/AlienGuard.png");
		getImageDimensions();	
	}
	
	public void move()
	{
		x+= 7;
		
		if (x > 1920)
		{
			x = 0;
		}
        
	}
}
