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
	Thread populateButtonThread;
    JPanel controllerPanel;    
    JButton populateArrayButton;
    JButton sortButton;
    JComboBox<String> sortSelectionComboBox;
    JToggleButton initialOrderingToggling;
    JToggleButton sortOrderingToggle;
    JComboBox<String> sortSpeedSelectionComboBox;
    JToggleButton pauseSortingButton;
    JButton stopSortingButton;
    
    String[] sortings;
    String[] animationSpeeds;
    Color color;
    
    
	
	

	SortPanel(Color color)
	{
		sortings=new String[4];
		sortings[0]="Heap";
		sortings[1]="Quick";
		sortings[2]="Selection";
		sortings[3]="Bubble";
		
		animationSpeeds=new String[3];
		animationSpeeds[0]="Fast";
		animationSpeeds[1]="Medium";
		animationSpeeds[2]="Slow";
		this.color=color;
		
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
		pauseSortingButton=new JToggleButton("Pause");
		stopSortingButton=new JButton("Stop");
		
		
		
		// Initial states of the components
		populateArrayButton.setEnabled(false);
		pauseSortingButton.setEnabled(false);
		stopSortingButton.setEnabled(false);
		
		
		
		
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
		populateArrayButton.addActionListener(this);
		sortOrderingToggle.addActionListener(this);
		stopSortingButton.addActionListener(this);
		pauseSortingButton.addActionListener(this);
		initialOrderingToggling.addActionListener(this);
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
			String speed=(String) sortSpeedSelectionComboBox.getSelectedItem();
			animationPanel.setAnimationSpeed(speed);
			String algo=(String) sortSelectionComboBox.getSelectedItem();
			animationPanel.setSortAlgorithm(algo);
			animationPanel.changeButtonStates(false,populateArrayButton,sortButton,sortOrderingToggle,initialOrderingToggling);
			animationPanel.changeButtonStates(true,pauseSortingButton,stopSortingButton);
			animationThread=new Thread(animationPanel);  
			animationThread.start();
			
			
			
			
			
			
			  
		
		  }
		  if(b==populateArrayButton)
		  {
		      animationPanel.populatevalues();
		      animationPanel.changeButtonStates(true,populateArrayButton,sortButton,initialOrderingToggling,sortOrderingToggle);
		      animationPanel.changeButtonStates(false,pauseSortingButton,stopSortingButton);
		  }
		  
		  if(b==stopSortingButton)
		  {
			  if(animationThread!=null && animationThread.isAlive())
				  animationThread.stop();
			  populateArrayButton.setEnabled(true);
			  animationPanel.changeButtonStates(false,sortButton,initialOrderingToggling,sortOrderingToggle,pauseSortingButton);
			  animationPanel.changeButtonStates(true, populateArrayButton);
		  }
		  
		  
	  }
	  if(component instanceof JToggleButton)
	  {
		  JToggleButton tb=(JToggleButton) component;
		  
		  if(tb==pauseSortingButton)
		  {
			  if(animationThread!=null)
			  {
				  if(pauseSortingButton.getText().equals("Pause"))
				  {
					  if(animationThread.isAlive())
					  {
						  animationThread.stop();
					  pauseSortingButton.setText("Resume");
					  String speed=(String) sortSelectionComboBox.getSelectedItem();
					  animationPanel.setAnimationSpeed(speed);
					  animationPanel.changeButtonStates(false, populateArrayButton,initialOrderingToggling,sortButton,sortOrderingToggle);
					  }
				  }
				  else
				  {
					  if(!animationThread.isAlive())
					  {
						  animationThread=new Thread(animationPanel);
						  animationThread.start();
						  pauseSortingButton.setText("Pause");
						  
					  }
				  }
			  }
		  }
		  
		  if(tb==initialOrderingToggling)
		  {
			  if(initialOrderingToggling.getText().equals("Initial Increasing"))
			  {
				  animationPanel.sortValues(true);
				  initialOrderingToggling.setText("Initial Decreasing");
			  }
			  else
			  {
				  animationPanel.sortValues(false);
				  initialOrderingToggling.setText("Initial Increasing");
			  }
		  }
		  
		  
		  
		  if(tb==sortOrderingToggle)
		  {
			  
			  if(tb.getText().equals("Increasing"))
			  {
				  animationPanel.setNormalOrdering(true);
				  tb.setText("Decreasing");
			  }
			  else
			  {
				  animationPanel.setNormalOrdering(false);
				  tb.setText("Increasing");
			  }
		  }
		  

	  }
	}
	
}
