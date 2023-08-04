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
		/*int n = 1;
		int[] m1 = {0,n,0,n};//11
		int[] m2 = {0,n,n,2*n};//12
		int[] m3 = {n,2*n,0,n};//21
		int[] m4 = {n,2*n,n,2*n};//22
		List<List<Integer>> C = op_matrix(B,B,m2,m4,0);*/
		
		for(int i = 0;i < C.size();i++){
			System.out.println(C.get(i));
		}
		
		
		
		
	}
	
	//Algorithm to calculate matrix multiplication if A x B () NxN and assuming that N is a power of 2 N = 2^n 
	 
	
	private static List<List<Integer>> op_matrix(List<List<Integer>> A,List<List<Integer>> B,int[] a,int[] b,int op){
		
		List<List<Integer>> sum = new ArrayList<>();
		
		int m = b[0];
		
		
		for(int i = a[0];i < a[1];i++){
			sum.add(new ArrayList<>());
			int n = b[2];
			for(int j = a[2];j < a[3];j++){
				if(op == 1){
					sum.get(m-b[0]).add(A.get(i).get(j) + B.get(m).get(n));
				}
				else {
					sum.get(m-b[0]).add(A.get(i).get(j) - B.get(m).get(n));
				}
				n++;
			}
			//System.out.println(m + "," + n);
			m++;
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
		
		if(a[1]-a[0] == 1){
			C.add(new ArrayList<>(Arrays.asList(A.get(a[0]).get(a[2])*B.get(b[0]).get(b[2]))));
			return C;
		}
			
		//ai,bi 
		
		int n = (a[1]-a[0])/2;
		
		int[] m1 = {0,n,0,n};//11
		int[] m2 = {0,n,n,2*n};//12
		int[] m3 = {n,2*n,0,n};//21
		int[] m4 = {n,2*n,n,2*n};//22 	
		
		List<List<Integer>> s1 = op_matrix(B,B,m2,m4,0);
		List<List<Integer>> s2 = op_matrix(A,A,m1,m2,1);
		List<List<Integer>> s3 = op_matrix(A,A,m3,m4,1);
		List<List<Integer>> s4 = op_matrix(B,B,m3,m1,0);
		List<List<Integer>> s5 = op_matrix(A,A,m1,m4,1);
		List<List<Integer>> s6 = op_matrix(B,B,m1,m4,1);
		List<List<Integer>> s7 = op_matrix(B,B,m2,m4,0);
		List<List<Integer>> s8 = op_matrix(B,B,m3,m4,1);
		List<List<Integer>> s9 = op_matrix(B,B,m1,m3,0);
		List<List<Integer>> s10 = op_matrix(B,B,m1,m2,1);
		
		List<List<Integer>> p1 = mult(A,s1,m1,m1);
		List<List<Integer>> p2 = mult(s2,B,m1,m4);
		List<List<Integer>> p3 = mult(s3,B,m1,m1);
		List<List<Integer>> p4 = mult(A,s4,m4,m1);
		List<List<Integer>> p5 = mult(s5,s6,m1,m1);
		List<List<Integer>> p6 = mult(s7,s8,m1,m1);
		List<List<Integer>> p7 = mult(s9,s10,m1,m1);
		
		List<List<Integer>> c1 = op_matrix(p5,p4,m1,m1,1);
		c1 = op_matrix(c1,p6,m1,m1,1);
		c1 = op_matrix(c1,p2,m1,m1,0);
		List<List<Integer>> c2 = op_matrix(p1,p2,m1,m1,1);
		List<List<Integer>> c3 = op_matrix(p3,p4,m1,m1,1);
		List<List<Integer>> c4 = op_matrix(p5,p1,m1,m1,1);
		c4 = op_matrix(c4,p3,m1,m1,0);
		c4 = op_matrix(c4,p7,m1,m1,0);
		
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
