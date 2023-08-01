package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class main_misc {

	
	public static int MAX = 1_000_000;
	
	
	public static void main(String[] args) {
	
		List<Integer> arr = new ArrayList<>(Arrays.asList(2,7,1,5,3,4,10));
		System.out.println(find_sum(arr,14));
	
	}
	private static int b_search(List<Integer> arr,int strt,int end,int x,int idx){
		
		int middle = (strt+end)/2;
		
		if(arr.get(middle) == x && middle != idx){
			return middle;
		}
		
		if(strt >= end){
			return -1;
		}
		
		if(x > arr.get(middle)){
			return b_search(arr,middle+1,end,x,idx);
		}
		else{
			return b_search(arr,strt,middle-1,x,idx);
		}
		 
	}
	
	private static int b_search(List<Integer> arr,int x,int idx){
		return b_search(arr,0,arr.size()-1,x,idx);
	}
	
	private static boolean find_sum(List<Integer> arr,int sum){
		
		Collections.sort(arr);
		
		for(int i = 0;i < arr.size();i++){
			
			int a = arr.get(i);
			
			if(b_search(arr,sum-a,i) != -1){
				return true;
			}
			
		}
		return false;
	}
	
	
}
