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
	 
	
	
	private static List<List<Integer>> sum_matrix(List<List<Integer>> A,List<List<Integer>> B){
		
		List<List<Integer>> sum = new ArrayList<>();
		
		System.out.println(A);
		System.out.println(B);
		System.out.println("----------------");
		
		int n = A.size();
		
		for(int i = 0;i < n;i++){
			sum.add(new ArrayList<>());
			
			for(int j = 0;j < n;j++){
				sum.get(i).add(A.get(i).get(j) + B.get(i).get(j));
			}
			
		}
		return sum;
	}

	private static void make_matrix(List<List<Integer>> mi,List<List<Integer>> mj,List<List<Integer>> C){
		
		int n = mi.size();
		
		for(int i = 0;i < n;i++){
			mi.get(i).addAll(mj.get(i));
			C.add(mi.get(i));
		}
		
	}
	
	private static List<List<Integer>> mult(List<List<Integer>> A,List<List<Integer>> B,int[] a,int[] b){
		
		//a : row_strt; b : row_end; c : col_strt; d : col_end
		
		List<List<Integer>> C = new ArrayList<>();
		
		int N  = (a[1]-a[0]);
		
		if(N == 1){
			C.add(new ArrayList<>(Arrays.asList(A.get(a[0]).get(a[2])*B.get(b[0]).get(b[2]))));
			return C;
		}
			
		
		//ai,bi 
		
		int n = N/2;
		
		//{strt_r,end_r,strt_c,end_c}
		int[] m1 = {0,n,0,n};
		int[] m2 = {0,n,n,2*n};
		int[] m3 = {n,2*n,0,n};
		int[] m4 = {n,2*n,n,2*n};
		
		
		List<List<Integer>> c1 = sum_matrix(mult(A,B,m1,m1),mult(A,B,m2,m3));
		List<List<Integer>> c2 = sum_matrix(mult(A,B,m1,m2),mult(A,B,m2,m4));
		List<List<Integer>> c3 = sum_matrix(mult(A,B,m3,m1),mult(A,B,m4,m3));
		List<List<Integer>> c4 = sum_matrix(mult(A,B,m3,m2),mult(A,B,m4,m4));
		
		make_matrix(c1,c2,C);
		make_matrix(c3,c4,C);
	
		return C;
	}
	
	private static List<List<Integer>> mult(List<List<Integer>> A,List<List<Integer>> B){
		
		int N = A.size();
		int[] a = {0,N};
		int[] b = {0,N};
		return mult(A,B,a,b);
	}
	
	
}

