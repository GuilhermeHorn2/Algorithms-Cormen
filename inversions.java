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

	List<Integer> arr = new ArrayList<>(Arrays.asList(2,3,8,6,1));
	
	System.out.println(count_inverse(arr));
	
	
	}
	
	private static void merge(List<Integer> arr,int strt,int mid,int end,int[] count_ptr){
		
		int itr_a = strt;
		int itr_b = mid+1;
		
		List<Integer> aux = new ArrayList<>();
		
		while(itr_a <= mid && itr_b <= end){
			
			int a = arr.get(itr_a);
			int b = arr.get(itr_b);
			
			if(a > b){
				aux.add(b);
				itr_b++;
				count_ptr[0] += mid-itr_a +1;
			}
			else {
				aux.add(a);
				itr_a++;
			}
		}
		
		for(int i = itr_a;i <= mid;i++){
			aux.add(arr.get(i));
		}
		for(int i =  itr_b;i <= end;i++){
			aux.add(arr.get(i));
		}
		
		int c = 0;
		for(int i = strt;i <= end;i++){
			arr.set(i,aux.get(c));
			c++;
		}
	}
	
	private static void merge_sort(List<Integer> arr,int strt,int end,int[] count_ptr){
		
		if(strt >= end){
			return;
		}
		
		
		int mid = (strt+end)/2;
		
		merge_sort(arr,strt,mid,count_ptr);
		merge_sort(arr,mid+1,end,count_ptr);
		merge(arr,strt,mid,end,count_ptr);
	}
	
	private static int count_inverse(List<Integer> arr){
		
		int[] count = {0};
		
		merge_sort(arr,0,arr.size()-1,count);
		
		
		return count[0];
	}
	
}
