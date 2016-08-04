import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AnimationPanel extends JPanel implements Runnable 
{
Integer[] values;
Integer[] helper;
Random random;
int maxValue;
public static final int LINE_WIDTH=1;
public  int animationSpeed=10;
int numValues;
int height;
int width;
Color color;
boolean normalCompare;
String sorting="Bubble";
private JButton populateButton;
private AbstractButton[] disableButtons;

	
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
	  random.setSeed(System.currentTimeMillis());
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
	repaint();
 }
 
 public void swap(int i,int j)
 {
		int temp;
		temp=values[i];
		values[i]=values[j];
		values[j]=temp;
		try {
			Thread.sleep(animationSpeed);
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
             if (compare(values[i],values[k])) 
             {
                 swap(i, k);
             }
         }
     }
 }
 
 
 

 
 
 public  void selectionSort()
 {
     
	   for (int i = 0; i < values.length - 1; i++) {
           // Find the index of the minimum value
           int minPos = i;
           for (int j = i + 1; j < values.length; j++) {
               if (compare(values[j],values[minPos])) {
                   minPos = j;
               }
           }
           swap(minPos,i);
       }

 }

@Override
public void run() 
{
if(sorting.equalsIgnoreCase("Bubble"))
{
	bubbleSort();
}
else if(sorting.equalsIgnoreCase("selection"))
{
	selectionSort();
}
else if(sorting.equalsIgnoreCase("quick"))
{
	quickSort(0,values.length-1);
}
else if(sorting.equalsIgnoreCase("Heap"))
{
	heapSort();
}

for(AbstractButton button : disableButtons)
	button.setEnabled(true);
	
}

public void setNormalOrdering(boolean b) {
	// TODO Auto-generated method stub
	normalCompare=b;
	
}

private boolean compare(Integer a,Integer b)
{
	if(!normalCompare)
	{
	if(a>b)
		return true;
	return false;
	}
	else
	{
		if(a>b)
			return false;
		return true;
		
	}
}

public void setAnimationSpeed(String s)
{
	// TODO Auto-generated method stub
	if(s.equalsIgnoreCase("Fast"))
		animationSpeed=10;
	else if(s.equalsIgnoreCase("Medium"))
		animationSpeed=100;
	else
		animationSpeed=500;
		
	
}

public void setSortAlgorithm(String s)
{
	this.sorting=s;
}

public void sortValues(boolean b) {
	// TODO Auto-generated method stub
	if(!b)
	{
	  Arrays.sort(values);
	}
	else
	{
		Arrays.sort(values,Collections.reverseOrder());
	}
	repaint();
}
 

public void heapSort()
{
    int n = values.length;

    // Build heap (rearrange array)
    for (int i = n / 2 - 1; i >= 0; i--)
        heapify(n, i);

    // One by one extract an element from heap
    for (int i=n-1; i>=0; i--)
    {
        // Move current root to end
    	swap(0,i);

        // call max heapify on the reduced heap
        heapify(i, 0);
    }
}

// To heapify a subtree rooted with node i which is
// an index in arr[]. n is size of heap
void heapify(int n, int i)
{
    int largest = i;  // Initialize largest as root
    int l = 2*i + 1;  // left = 2*i + 1
    int r = 2*i + 2;  // right = 2*i + 2

    // If left child is larger than root
    if (l < n && !compare(values[l],values[largest]))
        largest = l;

    // If right child is larger than largest so far
    if (r < n && !compare(values[r],values[largest]))
        largest = r;

    // If largest is not root
    if (largest != i)
    {
    	swap(i,largest);

        // Recursively heapify the affected sub-tree
        heapify(n, largest);
    }
}


int partition(int low, int high)
{
    int pivot = values[high]; 
    int i = (low-1); // index of smaller element
    for (int j=low; j<=high-1; j++)
    {
        // If current element is smaller than or
        // equal to pivot
        if (values[j] <= pivot)
        {
            i++;

            // swap arr[i] and arr[j]
            int temp = values[i];
            values[i] = values[j];
            values[j] = temp;
        }
    }

    swap(i+1,high);

    return i+1;
}


void quickSort(int low, int high)
{
    if (low < high)
    {
        /* pi is partitioning index, arr[pi] is 
          now at right place */
        int pi = partition(low, high);

        // Recursively sort elements before
        // partition and after partition
        quickSort(low, pi-1);
        quickSort(pi+1, high);
    }
}

public void changeButtonStates(boolean state,AbstractButton... populateArrayButton) {
	
	// TODO Auto-generated method stub
	this.disableButtons=populateArrayButton;
	for(AbstractButton ab : disableButtons)
		ab.setEnabled(state);
}

 
 

 
 
 
   
}
