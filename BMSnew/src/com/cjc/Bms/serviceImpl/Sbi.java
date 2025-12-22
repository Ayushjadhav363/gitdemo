package com.cjc.Bms.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.cjc.Bms.service.Rbi;
import com.cjc.Bms.utility.DBUtil;

public class Sbi implements Rbi {

    Scanner sc = new Scanner(System.in);

    @Override
    public void createAccount() {
        try (Connection con = DBUtil.getConnection()) {
            System.out.println("Enter age:");
            int age = sc.nextInt();
            sc.nextLine();

            if (age < 18 || age > 60) {
                System.out.println("You are not eligible to create an account (age must be 18-60).");
                return;
            }

            System.out.println("Enter 5-digit account number:");
            int accno = sc.nextInt();
            sc.nextLine();

          
            String checkSql = "SELECT COUNT(*) FROM account_details WHERE accNo = ?";
            try (PreparedStatement psCheck = con.prepareStatement(checkSql)) {
                psCheck.setInt(1, accno);
                try (ResultSet rs = psCheck.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        System.out.println("Account already exists! Choose a different account number.");
                        return;
                    }
                }
            }

          
            System.out.println("Enter Your Name:");
            String name = sc.nextLine();

            System.out.println("Enter Mobile number:");
            String mobNo = sc.next();

            System.out.println("Enter Aadhaar number:");
            long adharNo = sc.nextLong();
            sc.nextLine();

            System.out.println("Enter Gender:");
            String gender = sc.next();

            
            System.out.println("Enter initial deposit amount:");
            double balance = sc.nextDouble();
            sc.nextLine();

            
            String insertSql = "INSERT INTO account_details (accNo, name, mobNo, adharNo, gender, age, balance) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                psInsert.setInt(1, accno);
                psInsert.setString(2, name);
                psInsert.setString(3, mobNo);
                psInsert.setLong(4, adharNo);
                psInsert.setString(5, gender);
                psInsert.setInt(6, age);
                psInsert.setDouble(7, balance);

                int rows = psInsert.executeUpdate();
                if (rows > 0) {
                    System.out.println("Account Created Successfully!");
                } else {
                    System.out.println("Failed to create account. Try again.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error during createAccount: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void displayAllDetails() {
        String sql = "SELECT accNo, name, mobNo, adharNo, gender, age, balance FROM account_details ORDER BY accNo";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("---- All Account Details ----");
            boolean any = false;
            while (rs.next()) {
                any = true;
                System.out.println("Account Number : " + rs.getInt("accNo"));
                System.out.println("Name           : " + rs.getString("name"));
                System.out.println("Mobile Number  : " + rs.getString("mobNo"));
                System.out.println("Aadhaar Number : " + rs.getLong("adharNo"));
                System.out.println("Gender         : " + rs.getString("gender"));
                System.out.println("Age            : " + rs.getInt("age"));
                System.out.println("Balance        : " + rs.getDouble("balance"));
                System.out.println("-------------------------------");
            }
            if (!any) {
                System.out.println("No accounts found.");
            }

        } catch (SQLException e) {
            System.out.println("Database error during displayAllDetails: " + e.getMessage());
        }
    }

    @Override
    public void depositeMoney() {
        try (Connection con = DBUtil.getConnection()) {
            System.out.println("Enter Account number:");
            int acc = sc.nextInt();
            sc.nextLine();

            // fetch current balance
            String sel = "SELECT balance FROM account_details WHERE accNo = ?";
            try (PreparedStatement psSel = con.prepareStatement(sel)) {
                psSel.setInt(1, acc);
                try (ResultSet rs = psSel.executeQuery()) {
                    if (rs.next()) {
                        double current = rs.getDouble("balance");
                        System.out.println("Enter amount to deposit:");
                        double amt = sc.nextDouble();
                        sc.nextLine();

                        if (amt <= 0) {
                            System.out.println("Please enter amount greater than 0");
                            return;
                        }

                        String upd = "UPDATE account_details SET balance = ? WHERE accNo = ?";
                        try (PreparedStatement psUpd = con.prepareStatement(upd)) {
                            psUpd.setDouble(1, current + amt);
                            psUpd.setInt(2, acc);
                            int r = psUpd.executeUpdate();
                            if (r > 0) {
                                System.out.println("Deposited successfully! New Balance: " + (current + amt));
                            } else {
                                System.out.println("Deposit failed.");
                            }
                        }

                    } else {
                        System.out.println("Wrong Account Number");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error during deposit: " + e.getMessage());
        }
    }

    @Override
    public void withdrawal() {
        try (Connection con = DBUtil.getConnection()) {
            System.out.println("Enter Account number:");
            int acc = sc.nextInt();
            sc.nextLine();

            String sel = "SELECT balance FROM account_details WHERE accNo = ?";
            try (PreparedStatement psSel = con.prepareStatement(sel)) {
                psSel.setInt(1, acc);
                try (ResultSet rs = psSel.executeQuery()) {
                    if (rs.next()) {
                        double current = rs.getDouble("balance");
                        System.out.println("Enter amount to withdraw:");
                        double amt = sc.nextDouble();
                        sc.nextLine();

                        if (amt <= 0) {
                            System.out.println("Please enter amount greater than 0");
                            return;
                        }
                        if (amt <= current) {
                            String upd = "UPDATE account_details SET balance = ? WHERE accNo = ?";
                            try (PreparedStatement psUpd = con.prepareStatement(upd)) {
                                psUpd.setDouble(1, current - amt);
                                psUpd.setInt(2, acc);
                                int r = psUpd.executeUpdate();
                                if (r > 0) {
                                    System.out.println("Withdrawal successful! Remaining Balance: " + (current - amt));
                                } else {
                                    System.out.println("Withdrawal failed.");
                                }
                            }
                        } else {
                            System.out.println("Insufficient Balance!");
                        }
                    } else {
                        System.out.println("Wrong Account Number");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error during withdrawal: " + e.getMessage());
        }
    }

    @Override
    public void balanceCheck() {
        try (Connection con = DBUtil.getConnection()) {
            System.out.println("Enter Account number:");
            int acc = sc.nextInt();
            sc.nextLine();

            String sel = "SELECT balance FROM account_details WHERE accNo = ?";
            try (PreparedStatement psSel = con.prepareStatement(sel)) {
                psSel.setInt(1, acc);
                try (ResultSet rs = psSel.executeQuery()) {
                    if (rs.next()) {
                        double current = rs.getDouble("balance");
                        System.out.println("Current Balance: " + current);
                    } else {
                        System.out.println("Account not found.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error during balanceCheck: " + e.getMessage());
        }
    }
}
