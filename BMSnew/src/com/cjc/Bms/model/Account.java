package com.cjc.Bms.model;

public class Account { 
	 private int accNo; 
	 private String name; 
	 private String mobNo; 
	 private int adharNo; 
	 private String gender; 
	 private int age; 
	 private double balance;
	 public int getAccNo() {
		 return accNo;
	 }
	 public int setAccNo(int accNo) {
		 this.accNo = accNo;
		 return accNo;
	 }
	 public String getName() {
		 return name;
	 }
	 public void setName(String name) {
		 this.name = name;
	 }
	 public String getMobNo() {
		 return mobNo;
	 }
	 public void setMobNo(String mobNo) {
		 this.mobNo = mobNo;
	 }
	 public int getAdharNo() {
		 return adharNo;
	 }
	 public void setAdharNo(int adharNo) {
		 this.adharNo = adharNo;
	 }
	 public String getGender() {
		 return gender;
	 }
	 public void setGender(String gender) {
		 this.gender = gender;
	 }
	 public int getAge() {
		 return age;
	 }
	 public void setAge(int age) {
		 this.age = age;
	 }
	 public double getBalance() {
		 return balance;
	 }
	 public void setBalance(double balance) {
		 this.balance = balance;
	 }
}