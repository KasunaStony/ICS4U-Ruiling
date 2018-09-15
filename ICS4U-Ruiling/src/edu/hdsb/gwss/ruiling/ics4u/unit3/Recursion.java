/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.ruiling.ics4u.unit3;

/**
 *
 * @author maruiling
 */
public class Recursion {
    
    public static boolean isNesting(String str){
        
        if(str.length() == 0)
            return true;
        else if(str.charAt(0) == '(' && str.charAt(str.length()-1) == ')')
            return isNesting(str.substring(1, str.length() - 1));
        else 
            return false;
    }
    
    public static int numberOfhi(String str){
	
	int index = str.indexOf("hi");
	if(index == -1){
		return 0;
	}else if(index + 2 >= str.length()){
		return 1;
	}else{
		return 1 + numberOfhi(str.substring(index + 2));
	}
}
    
    public static int gcd(int m, int n){
        
        if(n > m){
            return gcd(n, m);
        }
        else if(m%n == 0)
            return n;
        else
            return gcd(n, m - n);
    }
    
    public static double frog(int t){
        
        if(t == 1)
            return 1;
        else
            return ((2-frog(t-1))/2.0);
    }
    
    public static int FiveTerms(int n){
        
        if(n == 1)
            return 1;
        if(n == 2)
            return 3;
        else 
            return FiveTerms(n - 1) + FiveTerms(n - 2);
        
    }
    
    public static int RecursiveFunctions(int x, int y){
        
        if(x < y)
            return -RecursiveFunctions(y,x);
        else if(x == y)
            return 0;
        else
            return 1 + RecursiveFunctions(x - 1, y);
    }
    
    public static int Ackermann(int m, int n){
        
        if(m == 0)
            return n + 1;
        else if(m > 0 && n == 0)
            return Ackermann(m - 1, 1);
        else
            return Ackermann(m - 1, Ackermann(m, n - 1));
    }
    
    public static int f(int x, int y){
	
	if(x > 2 && x % 2 == 0){
		return f(y - 3, x + 2) + 3;
	}
	else if(x > 2 && isPrime(x)){
		return f(x - 3, y + 1) - 4;
	}
	else{
		return (int)(Math.pow(x, 2)) + y +1;
	}
    }
    
    public static boolean isPrime(int x){
        
        for(int i=2;i<x;i++) {
        if(x%i==0)
            return false;
    }
    return true;
    
    }
            
    
    public static void main(String args[]){
        
        System.out.println(gcd(54, 108));
        System.out.println(frog(6));
        
        System.out.println(FiveTerms(5));
        System.out.println(RecursiveFunctions(-5,-2));
        System.out.println(Ackermann(1,1));
        
        System.out.println(numberOfhi("xxxhixxhi"));
        
        System.out.println(isNesting("(()))"));
        
        System.out.println(f(7,6));
    }
    
}
