import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class AnimationPanel extends JPanel implements Runnable 
{
Integer[] values;
Integer[] helper;
Random random;
int maxValue;
public static final int LINE_WIDTH=1;
public static final int ANIMATION_DURATION=10;
int numValues;
int height;
int width;
Color color;

	
   public AnimationPanel(int width,int height,Color color) 
 {
	// TODO Auto-generated constructor stub
	   this.width=width;
	   this.height=height;
	  maxValue=height;
	  numValues=width;
	  System.out.println("numvalues="+numValues);
	  values=new Integer[numValues];
	  random=new Random();
	  this.color=color;
	  setSize(width, height);
	  populatevalues();
	  
	  
 }
   
 public void populatevalues()
 {
	for(int i=0;i<values.length;i++)
	{
		values[i]=random.nextInt(maxValue);
		
	}
 }
 
 public void swap(int i,int j)
 {
		int temp;
		temp=values[i];
		values[i]=values[j];
		values[j]=temp;
		try {
			Thread.sleep(ANIMATION_DURATION);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
 }
 
 
 @Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		System.out.println("paint called");
		g.setColor(color);
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
 
 public  void bubbleSort()
 {
     int n = values.length;
     int k;
     for (int m = n; m >= 0; m--) 
     {
         for (int i = 0; i < n - 1; i++) 
         {
             k = i + 1;
             if (values[i] > values[k]) 
             {
                 swap(i, k);
             }
         }
     }
 }
 
 
 
 public void quickSort() {
     
     if (values == null || values.length == 0) {
         return;
     }
     quick_sort(0,values.length-1);
 }

 private void quick_sort(int lowerIndex, int higherIndex) {
      
     int i = lowerIndex;
     int j = higherIndex;
     int pivot = values[lowerIndex+(higherIndex-lowerIndex)/2];
     while (i <= j) {
         while (values[i] < pivot) {
             i++;
         }
         while (values[j] > pivot) {
             j--;
         }
         if (i <= j) {
             swap(i, j);
             //move index to next position on both sides
             i++;
             j--;
         }
     }
     // call quickSort() method recursively
     if (lowerIndex < j)
         quick_sort(lowerIndex, j);
     if (i < higherIndex)
         quick_sort(i, higherIndex);
 }
 
 
 public  void selectionSort()
 {
     
	   for (int i = 0; i < values.length - 1; i++) {
           // Find the index of the minimum value
           int minPos = i;
           for (int j = i + 1; j < values.length; j++) {
               if (values[j] < values[minPos]) {
                   minPos = j;
               }
           }
           swap(minPos,i);
       }

 }

@Override
public void run() 
{
quickSort();	
	
}
 
 

 
 

 
 
 
   
}
