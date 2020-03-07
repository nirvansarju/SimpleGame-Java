
public class AlienMother extends Sprite
{
	public AlienMother(int x, int y)
	{
		super(x,y);
		
		initBound();
	}
	
	public void initBound()
	{
		loadImage("/Images/AlienMother.png");
		getImageDimensions();	
	}
	
	public void move()
	{
		x += 4;
		x -= 4;
        
	}
}
