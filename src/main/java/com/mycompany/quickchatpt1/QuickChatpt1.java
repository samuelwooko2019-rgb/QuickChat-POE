/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.quickchatpt1;

import java.util.Scanner;

/**
 * Login.java
 *
 * Handles registration and login validation for the QuickChat application
 * (PROG - Part 1).
 *
 * Reference for cell phone regular expression approach:
 * Baeldung (2024) 'Validate Phone Numbers With Java Regex'. Available at:
 * https://www.baeldung.com/java-regex-validate-phone-numbers
 * (Accessed: 16 September 2026). Section 2.4 ("Number With International
 * Prefix") adapted here to require the leading '+' and digit-only
 * country code, simplified to fit the assignment's ten-character rule
 * rather than matching a specific national number format.
 *
 * @autho Sam Wooko
 */
public class QuickChatpt1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== QuickChat: Registration ===");
        boolean registeredSuccessfully = false;

        while (!registeredSuccessfully) {
            System.out.print("Enter first name: ");
            login.setFirstName(scanner.nextLine());

            System.out.print("Enter last name: ");
            login.setLastName(scanner.nextLine());

            System.out.print("Enter username (must contain '_' and be <= 5 characters): ");
            login.setUsername(scanner.nextLine());

            System.out.print("Enter password (min 8 chars, 1 capital, 1 number, 1 special char): ");
            login.setPassword(scanner.nextLine());

            System.out.print("Enter cell number (e.g. +27838968976): ");
            login.setCellNumber(scanner.nextLine());

            String registrationMessage = login.registerUser();
            System.out.println(registrationMessage);

            if (login.isRegistered()) {
                System.out.println(login.checkCellPhoneNumberMessage());
                if (login.checkCellPhoneNumber()) {
                    registeredSuccessfully = true;
                } else {
                    System.out.println("Please reenter your details to try again.");
                }
            } else {
                System.out.println("Please re-enter your details to try again.");
            }
        }

        System.out.println("= QuickChat: Login ===");
        boolean loggedIn = false;
        int attempts = 0;

        while (!loggedIn && attempts < 3) {
            System.out.print("Enter username: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            boolean success = login.loginUser(enteredUsername, enteredPassword);
            System.out.println(login.returnLoginStatus(success));

            if (success) {
                loggedIn = true;
            } else {
                attempts++;
            }
        }

        if (!loggedIn) {
            System.out.println("Too many failed login attempts. Exiting.");
        }

        scanner.close();
    }
}
