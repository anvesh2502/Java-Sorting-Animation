import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class SortingFrame extends JFrame
{
	
	SortingPanel panel;
	
	public static void main(String[] args)
	{
		SortingFrame sframe=new SortingFrame();
		
		sframe.initUI();
		sframe.setVisible(true);
		sframe.panel.swap();
	}
	
	void initUI()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// get 2/3 of the height, and 2/3 of the width
		int height = screenSize.height * 2 / 3;
		int width = screenSize.width * 2 / 3;
		
		

		panel=new SortingPanel(400,400);
		setSize(400,400);
		add(panel);
		setTitle("Points");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        
        
	}
	

}
