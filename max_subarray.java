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

		List<Integer> arr1 = new ArrayList<>(Arrays.asList(1,-16,-23,18,20,-7,12,-25,15));
		
		List<Integer> arr2 = new ArrayList<>(Arrays.asList(-10,-15,-1,-2,-30));
		
		System.out.println(max_subarray(arr1,0,arr1.size()-1));
		
		System.out.println(max_subarray(arr2,0,arr2.size()-1));
		
		//System.out.println(get_max(arr,1,1,3,3));
		
	}
	
	private static List<Integer> get_max(List<Integer> arr,int strt_a,int end_a,int strt_b,int end_b){
		
		List<Integer> interval  = new ArrayList<>(Arrays.asList(-1,-1,0));
		
		int sum_a = 0;
		
		//calculating individually
		
		for(int i = strt_a;i <= end_a;i++){
			sum_a += arr.get(i);
		}
		
		int sum_b = 0;
		
		for(int i = strt_b;i <= end_b;i++){
			sum_b += arr.get(i);
		}
		
		
		//finding a max that cross the midpoint
		
		int max_idx = -1;
		int curr = 0;
		int acc_curr = 0;
		for(int i = strt_a+1;i <= end_b;i++){
			
			int x = arr.get(i);
			curr += x;
			if(x > 0){
				if(curr > 0) {
					max_idx = i;
					acc_curr += curr;
				}
				curr = 0;
			}
			
		}
		
		if(max_idx != -1 && sum_a+acc_curr > sum_b){
			interval.set(0, strt_a);
			interval.set(1,max_idx);
			interval.set(2,sum_a+max_idx);
		}
		else if(sum_b > 0 && sum_b > sum_a){
			interval.set(0,strt_b);
			interval.set(1,end_b);
			interval.set(2,sum_b);
		}
		else if(sum_a > 0){
			interval.set(0,strt_a);
			interval.set(1,end_a);
			interval.set(2,sum_a);
		}
		
		return interval;
	}
	
	private static List<Integer> max_subarray(List<Integer> arr,int strt,int end){
		
		if(strt >= end){
			return new ArrayList<>(Arrays.asList(strt,end,arr.get(strt)));
		}
		
		int mid = (strt+end)/2;
		
		List<Integer> max_a = max_subarray(arr,strt,mid);
		
		List<Integer> max_b = max_subarray(arr,mid+1,end);
		
		if(max_a.get(0) == -1 && max_b.get(0) == -1){
			if(max_a.get(2) > max_b.get(2)){
				return max_a;
			}
			return max_b;
		}
		
		if(max_a.get(0) == -1){
			return max_b;
		}
		if(max_b.get(0) == -1){
			return max_a;
		}
		
		//System.out.println(max_b);
		return get_max(arr,max_a.get(0),max_a.get(1),max_b.get(0),max_b.get(1));
		
		
		
	
		
	}
	
}
