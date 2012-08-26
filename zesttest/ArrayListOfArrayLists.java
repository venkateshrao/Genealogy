package zesttest;
import java.util.ArrayList;

public class ArrayListOfArrayLists
{
	public static void main(String[] args)
	{
		ArrayList<ArrayList<Integer>> listOfList = new ArrayList<ArrayList<Integer>>();
        
        for (int row = 0; row < 5; row++)
        {
        	listOfList.add(new ArrayList<Integer>());
        	
        	for (int col = 0; col < 3; col++)
        	{
        		listOfList.get(row).add((int) (Math.random() * 100));
        	}
        }
        
        System.out.println(listOfList);
        
        /*ArrayList<int[]> listOfArray = new ArrayList<int[]>();
        for (int row = 0; row < 5; row++)
        {
        	int[] array = new int[3];
        	
        	for (int col = 0; col < 3; col++)
        	{
        		array[col] = (int) (Math.random() * 100);
        	}
        	
        	listOfArray.add(array);        	
        }

        int i = 0;
        while (i < listOfArray.size())
        {
        	for (int j = 0; j < listOfArray.get(i).length; j++)
        		System.out.print(listOfArray.get(i)[j] + " ");
        	i++;
        }*/
	}
}