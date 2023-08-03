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
		
		List<Integer> arr2 = new ArrayList<>(Arrays.asList(-1,1,7,-10,3,15,-4,-1,3,5));
		
		System.out.println(max_subarray_linear(arr1));
		
		System.out.println(max_subarray_linear(arr2));
		
	}
	
	private static List<Integer> max_subarray_linear(List<Integer> arr){
		
		List<Integer> idxs = new ArrayList<>(Arrays.asList(0,0));
		//List<Integer> idxs_acc = new ArrayList<>(Arrays.asList(-1,-1));
		
		int strt = -1;
		
		
		int max = arr.get(0);
		int acc_max = max;
		
		boolean can_sum = false;
		
		if(max < 0){
			acc_max = 0;
		}
		else {
			strt = 0;
			can_sum = true;
		}
		
		
		int len = arr.size();
		
		for(int i = 1;i < len;i++){
			
			int x = arr.get(i);
			if(x >= 0){
				if(can_sum) {
					acc_max += x;
				}
				else {
					//check if it is worth to aggregate both maxes
					if(acc_max <= 0){
						strt = i;
						acc_max = x;
					}
					acc_max += x;
					can_sum = true;
				}
				
			}
			else if(x < 0){
				if(acc_max > max) {
					max = acc_max;
					idxs.set(0,strt);
					idxs.set(1,i-1);
				}
				can_sum = false;
				
				acc_max += x;
				
			}
			if(i+1 == len){
				if(acc_max > max){
					idxs.set(0,strt);
					idxs.set(1,i);
				}
			}
			
		}
		return idxs;
	}
	
}
