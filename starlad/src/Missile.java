
public class Missile extends Sprite
{
	private final int MISSILE_SPEED = 18;
	
	public Missile(int x, int y)
	{
		super(x,y);
		
		initMissile();
	}
	
	private void initMissile()
	{
		loadImage("/Images/starmissile.png");
		getImageDimensions();
	}
	
	public void move()
	{
		y -= MISSILE_SPEED;
		
		if (x > 1920)
		{
			vis = false;
		}
		
	}
	
}
