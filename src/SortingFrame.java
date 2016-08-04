import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class SortingFrame extends JFrame
{
	
	SortPanel leftPanel;
	SortPanel rightPanel;

	
	public static void main(String[] args)
	{
		SortingFrame sframe=new SortingFrame();
		
		sframe.initUI();
		sframe.setVisible(true);
	}
	
	void initUI()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// get 2/3 of the height, and 2/3 of the width
		int height = screenSize.height * 2 / 3;
		int width = screenSize.width * 2 / 3;
		
		setLayout(new GridLayout(1,2));
		
		leftPanel=new SortPanel(Color.BLUE);
		rightPanel=new SortPanel(Color.GREEN);
		
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		setSize(600,600);
		
		add(leftPanel);
		add(rightPanel);

		setTitle("Sorting Animation");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        
        
	}
	

}
