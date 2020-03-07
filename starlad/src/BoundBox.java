import java.awt.Rectangle;

public class BoundBox extends BoundSkele
{
    protected static final int width = 800;
    protected static final int height = 1;
    protected Rectangle rect;
    
	public BoundBox(int x, int y)
	{
		super(x,y);
		
		initBound();
	}
	
	public void initBound()
	{
		loadImage("/Images/BOUNDTESTER.jpg");
		getImageDimensions();	
	}

}
