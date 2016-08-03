import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class SortingPanel extends JPanel 
{
Integer[] values;
Random random;
int maxValue;
public static final int LINE_WIDTH=1;
int numValues;
int height;
int width;
	
   public SortingPanel(int width,int height) 
 {
	// TODO Auto-generated constructor stub
	   this.width=width;
	   this.height=height;
	  maxValue=height;
	  numValues=width;
	  System.out.println("numvalues="+numValues);
	  values=new Integer[numValues];
	  random=new Random();
	  populateArray();
	  
	  
 }
   
 public void populateArray()
 {
	for(int i=0;i<values.length;i++)
	{
		values[i]=random.nextInt(maxValue);
		
	}
 }
 
 public void swap()
 {
	 int temp;
	 for(int i=0;i<values.length-1;i++)
	 {
		 for (int j = 0; j < values.length - i - 1; j++) {
		        if (values[j] > values[j+1]) /* For descending order use < */
		        {
		          temp       = values[j];
		          values[j]   = values[j+1];
		          values[j+1] = temp;
		          try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        repaint();  
		        }
		      } 
		
	 }
		
 }
 
 
 @Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		System.out.println("paint called");
		g.setColor(Color.BLUE);
		int x1=0,x2=0;
		int y1=0,y2=0;
		for(Integer i : values)
		{
		g.drawLine(x1, y1, x2, y2);
		x1+=LINE_WIDTH;
		x2=x1;
		y1=height;
		y2=i;
		}
		
		
		
		
		}
 
 
 
 

 
 
 
   
}
