package com.cjc.Bms.client;
import java.util.Scanner;
import com.cjc.Bms.service.Rbi;
import com.cjc.Bms.serviceImpl.Sbi;
public class Test {
	public static void main(String[] args) {
		
	    Scanner sc = new Scanner(System.in);
	    Rbi bank = new Sbi();
	    
	    while(true) {
	    System.out.println("enter your choice");
	    System.out.println(" 1.for create Account \n 2.displayAllDetails \n 3.for depositeMoney \n 4.withdraw money \n 5.for balanceCheck");
	    try {
	    int c = sc.nextInt();

	    switch(c) {
	        case 1:
	            bank.createAccount();
	            break;
	            
	        case 2:
	            bank.displayAllDetails();
	            break;
	            
	        case 3:
	             bank.depositeMoney();
	            break;
	            
	        case 4:
	             bank.withdrawal();
	            break;
	            
	        case 5:
	        	bank.balanceCheck();
	        	break;
	         
	        default:
	            System.out.println("Invalid choice");
	            break;
	    }
	    }catch(Exception e)
	    {
	    	System.out.println("please enter interger");
	    	sc.nextLine();
	    	
	    }}}}
	
		
