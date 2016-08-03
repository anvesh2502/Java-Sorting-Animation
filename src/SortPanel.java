import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class SortPanel extends JPanel implements ActionListener
{
	
	AnimationPanel animationPanel;
	Thread animationThread;
    JPanel controllerPanel;    
    JButton populateArrayButton;
    JButton sortButton;
    JComboBox<String> sortSelectionComboBox;
    JToggleButton initialOrderingToggling;
    JToggleButton sortOrderingToggle;
    JComboBox<String> sortSpeedSelectionComboBox;
    JButton pauseSortingButton;
    JButton stopSortingButton;
    
    String[] sortings;
    String[] animationSpeeds;
    
	
	

	SortPanel(Color color)
	{
		sortings=new String[4];
		sortings[0]="Bubble";
		sortings[1]="Quick";
		sortings[2]="Selection";
		sortings[3]="Shell";
		
		animationSpeeds=new String[3];
		animationSpeeds[0]="Fast";
		animationSpeeds[1]="Medium";
		animationSpeeds[2]="Slow";
		
		initUI(color);
		initListeners();
	}
	
	void initUI(Color color)
	{
		setLayout(new GridLayout(2, 1));
		controllerPanel=new JPanel();
		controllerPanel.setLayout(new GridLayout(4,2));
		
		
		// Initialization of animationPanel
		animationPanel=new AnimationPanel(300,300,color);
		
		// Initialization of controller elements
		populateArrayButton=new JButton("Populate");
		sortButton=new JButton("Sort");
		sortSelectionComboBox=new JComboBox<String>(sortings);
		initialOrderingToggling=new JToggleButton("Initial Increasing");
		sortOrderingToggle=new JToggleButton("Increasing");
		sortSpeedSelectionComboBox=new JComboBox<String>(animationSpeeds);
		pauseSortingButton=new JButton("Pause");
		stopSortingButton=new JButton("Stop");
		
		animationThread=new Thread(animationPanel);
		
		
		// Initial states of the components
		populateArrayButton.setEnabled(false);
		
		
		
		
		// Adding components to Controller Panel
		controllerPanel.add(populateArrayButton);
		controllerPanel.add(sortButton);
		controllerPanel.add(sortSelectionComboBox);
		controllerPanel.add(initialOrderingToggling);
		controllerPanel.add(sortOrderingToggle);
		controllerPanel.add(sortSpeedSelectionComboBox);
		controllerPanel.add(pauseSortingButton);
		controllerPanel.add(stopSortingButton);
		
		
		add(animationPanel);
		add(controllerPanel);

		
		
		
	}
	
	void initListeners()
	{
		sortButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object component=e.getSource();
	  if(component instanceof JButton)
	  {
		  JButton b=(JButton) component;
		  if(b==sortButton)
		  {
			  animationThread.start();
		  }
	  }
	}
	
}
