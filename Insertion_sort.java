package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class main_misc {

	
	public static int MAX = 1_000_000;
	
	
	public static void main(String[] args) {
	
		
	
	List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,5,6,3));
	
	insertion_sort(arr);
	
	System.out.println(arr);
		
		
	}
	
	private static void insertion_sort(List<Integer> arr){
		
		for(int i = 1;i < arr.size();i++){
			
			int key = arr.get(i);
			
			int j = i-1;
			while(j > 0 && arr.get(j) > key){
				arr.set(j+1,arr.get(j));
				arr.set(j,key);
				j--;
			}
			
			
		}
		
		
	}
	

	
}
