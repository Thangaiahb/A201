package com.rest.CLI;

import com.rest.models.Payment;
import com.rest.payment.*;
import com.rest.service.PaymentService;

import java.util.Scanner;

public class PaymentCli {

    private final PaymentService service;
    private final Scanner scanner = new Scanner(System.in);

    public PaymentCli() {
        service = new PaymentService();
    }

    public void start() {
        int choice = -1;

        do {
            System.out.println("\n=== RESTAURANT PAYMENT SYSTEM ===");
            System.out.println("1. Make Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. Find Payment by ID");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        makePayment();
                        break;

                    case 2:
                        viewAllPayments();
                        break;

                    case 3:
                        findPayment();
                        break;

                    case 0:
                        System.out.println("Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = -1;

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                choice = -1;
            }

        } while (choice != 0);

        scanner.close();
    }

    private void makePayment() {
        System.out.print("Enter amount: Rs. ");

        float amount = scanner.nextFloat();

        System.out.println("\nSelect Payment Method");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. UPI");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();

        PaymentMethod method;

        switch (choice) {
            case 1:
                method = new CashPayment();
                break;

            case 2:
                method = new CardPayment();
                break;

            case 3:
                method = new UPIPayment();
                break;

            default:
                System.out.println("Invalid payment method.");
                return;
        }

        Payment payment = service.processPayment(method, amount);

        System.out.println("Payment record saved.");
        System.out.println(payment);
    }

    private void viewAllPayments() {
        if (service.getAllPayments().isEmpty()) {
            System.out.println("No payment records found.");
            return;
        }

        for (Payment payment : service.getAllPayments()) {
            System.out.println(payment);
        }
    }

    private void findPayment() {
        System.out.print("Enter payment ID: ");

        int id = Integer.parseInt(scanner.nextLine());

        Payment payment = service.getPaymentById(id);

        System.out.println(payment);
    }
}