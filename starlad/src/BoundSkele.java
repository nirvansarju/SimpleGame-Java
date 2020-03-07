import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class BoundSkele 
{
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean vis;
	protected Image image;
	
	public BoundSkele(int x, int y)
	{
		this.x = x;
		this.y = y;
		vis = true;
	}
	
	protected void getImageDimensions()
	{
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	protected void loadImage(String imageName)
	{
		ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource(imageName));
		image = ii.getImage();
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public boolean isVisible()
	{
		return vis;
	}
	
	public void setVisible(Boolean visible)
	{
		vis = visible;
	}
	
	public Rectangle getBounds()//HITBOX
	{
		return new Rectangle(x,y,width,height); 
	}
}
