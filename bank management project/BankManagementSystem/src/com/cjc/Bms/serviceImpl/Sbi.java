package com.cjc.Bms.serviceImpl;
import java.util.Scanner;
import com.cjc.Bms.model.Account;
import com.cjc.Bms.service.Rbi;
public class Sbi implements Rbi {
   
	Scanner sc = new Scanner(System.in);
	Account ac = new Account();
	public void createAccount() {
	   
	    System.out.println("enter your Age");
	    int age = sc.nextInt();
	    if(age>= 18 && age<= 60) {
	    	 System.out.println("create your account");

	 	    System.out.println("enter account number"); 
	 	   
	 	   int accno = sc.nextInt();
	 	  String accStr = String.valueOf(accno);
	 	  int[] arr = new int[5];

	 	  if (accStr.length() >= arr.length) {  
	 	      ac.setAccNo(accno);
	 	      sc.nextLine();

	 	      System.out.println("enter Your Name");
	 	      ac.setName(sc.nextLine());  

	 	      System.out.println("enter Mob number");
	 	      ac.setMobNo(sc.next());

	 	      System.out.println("enter Aadhaar number");
	 	      ac.setAdharNo(sc.nextInt());

	 	      System.out.println("enter Your Gender");
	 	      ac.setGender(sc.next());

	 	      ac.setAge(age);
	 	  } else {
	 	      System.out.println("Please enter 5 digit account number");
	 	  }
	    }else {
		System.out.println("you are not eligible to create account");
	    }}
	

	
	public void displayAllDetails() {
	    System.out.println("---- Account Details ----");
	    System.out.println("Account Number : " + ac.getAccNo());
	    System.out.println("Name           : " + ac.getName());
	    System.out.println("Mobile Number  : " + ac.getMobNo());
	    System.out.println("Aadhaar Number : " + ac.getAdharNo());
	    System.out.println("Gender         : " + ac.getGender());
	    System.out.println("Age            : " + ac.getAge());
	    System.out.println("-------------------------");
	}

	public void depositeMoney() {
	    System.out.println("Enter Account number");
	    int acc = sc.nextInt();

	    if (acc == ac.getAccNo()) {
	        System.out.print("Enter amount to deposit: ");
	        double amt = sc.nextDouble();

	        if (amt <= 0) {
	            System.out.println("Please enter amount greater than 0");
	        } else {
	            ac.setBalance(ac.getBalance() + amt);
	            System.out.println("Deposited successfully! New Balance: " + ac.getBalance() + "\n");
	        }
	    } else {
	        System.out.println("Wrong Account Number");
	    }
	}

	public void withdrawal() {
	    System.out.println("Enter Account number");
	    int acc = sc.nextInt();

	    if (acc == ac.getAccNo()) {
	        System.out.print("Enter amount to withdraw: ");
	        double amt = sc.nextDouble();

	        if (amt <= 0) {
	            System.out.println("Please enter amount greater than 0");
	        } else if (amt <= ac.getBalance()) {
	            ac.setBalance(ac.getBalance() - amt);
	            System.out.println("Withdrawal successful! Remaining Balance: " + ac.getBalance() + "\n");
	        } else {
	            System.out.println("Insufficient Balance!\n");
	        }
	    } else {
	        System.out.println("Wrong Account Number");
	    }
	}

	
	
        public void balanceCheck() {
            System.out.println("Current Balance: " + ac.getBalance() + "\n");
        }
    }

