package com.rest.CLI;

import com.rest.models.Table;
import com.rest.service.TableServices;

import java.util.Scanner;

public class TableCli {
    private final TableServices Service;
    Scanner scan = new Scanner(System.in);

    public TableCli(TableServices Service) {
        this.Service = Service;
    }

    public void start() {
        int choice;
        do {
            System.out.println("Table Management");

            System.out.println("1. All Tables");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    Service.getAll();
                    break;

                default:
                    System.out.println("Balance Ur Wish");
                    break;
            }
        } while (choice != 0);
    }
}
