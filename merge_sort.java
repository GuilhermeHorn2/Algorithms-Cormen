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
	
	List<Integer> arr = new ArrayList<>(Arrays.asList(1,3,5,2,7));
	
	merge_sort(arr,0,arr.size()-1);
	
	System.out.println(arr);
		
	}
	private static void merge(List<Integer> arr,int p,int q,int r){
		
		int itr_a = p;
		int itr_b = q+1;
		
		List<Integer> merged = new ArrayList<>();
		
		while(itr_a <= q && itr_b <= r){
			
			int a = arr.get(itr_a);
			int b = arr.get(itr_b);
			
			if(a > b){
				merged.add(b);
				itr_b++;
			}
			else {
				merged.add(a);
				itr_a++;
			}
			
		}
		
		//fill the rest
		
		for(int i = itr_a;i <= q;i++){
			merged.add(arr.get(i));
		}
		for(int i = itr_b;i <= r;i++){
			merged.add(arr.get(i));
		}
		
		//change the elements in arr
		
		int c = 0;
		for(int i = p;i <= r;i++){
			arr.set(i, merged.get(c));
			c++;
		}
		
		
	}
	
	private static void merge_sort(List<Integer> arr,int strt,int end){
		
		if(strt >= end){
			return;
		}
			
		int part = (strt+end)/2;
		
		merge_sort(arr,strt,part);
		merge_sort(arr,part+1,end);
		merge(arr,strt,part,end);
		
	}
	
}
