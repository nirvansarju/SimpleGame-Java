import java.awt.EventQueue;

import javax.swing.JFrame;


public class Application extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	Child child;
	
    public Application() 
    {
    	initUI();
    }
    
    public void initUI()
    {	
        add(new Board());
        
        setSize(1920, 1080);
        setResizable(false);
        
        setTitle("StarLad Saves the World");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) 
    {	
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application ex = new Application();
                ex.setVisible(true);
            }
        });
    }
}
