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

		List<Integer> a0 = new ArrayList<>(Arrays.asList(1,2));
		List<Integer> a1 = new ArrayList<>(Arrays.asList(2,3));
		List<Integer> b0 = new ArrayList<>(Arrays.asList(1,2));
		List<Integer> b1 = new ArrayList<>(Arrays.asList(2,3));
		
		List<List<Integer>> A = new ArrayList<>(Arrays.asList(a0,a1));
		List<List<Integer>> B = new ArrayList<>(Arrays.asList(b0,b1));
		
		List<List<Integer>> C = mult(A,B);
		
		for(int i = 0;i < C.size();i++){
			System.out.println(C.get(i));
		}
		
		
	}
	
	//Algorithm to calculate matrix multiplication if A x B () NxN and assuming that N is a power of 2 N = 2^n 
	 
	
	private static void concat_matrix(List<List<Integer>> C,List<List<Integer>> m,int num){
		
		
	}
	
	private static List<List<Integer>> sum_matrix(List<List<Integer>> A,List<List<Integer>> B){
		
		List<List<Integer>> sum = new ArrayList<>();
		
		int n = A.size();
		
		for(int i = 0;i < n;i++){
			sum.add(new ArrayList<>());
			
			for(int j = 0;j < n;j++){
				sum.get(i).add(A.get(i).get(j) + B.get(i).get(j));
			}
			
		}
		return sum;
	}
	
	private static List<List<Integer>> get_part(List<List<Integer>> m,int num){
		
		int strt_r = -1;
		int end_r = -1;
		
		int strt_c = -1;
		int end_c = -1;
		
		int n = m.size()/2;
		
		if(num == 1 || num == 2){
			strt_r = 0;
			end_r = n-1;
			
			if(num == 1){
				strt_c = 0;
				end_c = n-1;
			}
			else {
				strt_c = n;
				end_c = (2*n)-1;
			}
		}
		else{
			strt_r = n;
			end_r = (2*n)-1;
			
			if(num == 3){
				strt_c = 0;
				end_c = n-1;
			}
			else {
				strt_c = n;
				end_c = (2*n)-1;
			}
		}
		
		List<List<Integer>> tmp = new ArrayList<>();
		
		int k = 0;
		for(int i = strt_r;i <= end_r;i++){
			tmp.add(new ArrayList<>());
			for(int j = strt_c;j <= end_c;j++){
				tmp.get(k).add(m.get(i).get(j));
			}
			k++;
		}
		return tmp;
	}
	
	private static void make_matrix(List<List<Integer>> mi,List<List<Integer>> mj,List<List<Integer>> C,int half){
		
		int n = mi.size();
		
		for(int i = 0;i < n;i++){
			mi.get(i).addAll(mj.get(i));
			C.add(mi.get(i));
		}
		
	}
	
	private static List<List<Integer>> mult(List<List<Integer>> A,List<List<Integer>> B){
		
		//a : row_strt; b : row_end; c : col_strt; d : col_end
		
		List<List<Integer>> C = new ArrayList<>();
		
		if(A.size() == 1){
			C.add(new ArrayList<>(Arrays.asList(A.get(0).get(0)*B.get(0).get(0))));
			return C;
		}
			
		//ai,bi 
		
		List<List<Integer>> a1 = get_part(A,1);
		List<List<Integer>> a2 = get_part(A,2);
		List<List<Integer>> a3 = get_part(A,3);
		List<List<Integer>> a4 = get_part(A,4);
		List<List<Integer>> b1 = get_part(B,1);
		List<List<Integer>> b2 = get_part(B,2);
		List<List<Integer>> b3 = get_part(B,3);
		List<List<Integer>> b4 = get_part(B,4);
		
		//concat ;mk = ai + bj
		List<List<Integer>> m1 = sum_matrix(mult(a1,b1),mult(a2,b3));
		List<List<Integer>> m2 = sum_matrix(mult(a1,b2),mult(a2,b4));
		List<List<Integer>> m3 = sum_matrix(mult(a3,b1),mult(a4,b3));
		List<List<Integer>> m4 = sum_matrix(mult(a3,b2),mult(a4,b4));
		
		make_matrix(m1,m2,C,1);
		make_matrix(m3,m4,C,2);
		
		return C;
	}
	
	
}
