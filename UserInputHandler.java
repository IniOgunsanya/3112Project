package com.example;

import java.util.Scanner;

public class UserInputHandler {
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your search query, book title and author name: ");
        String input = scanner.nextLine();
        scanner.close();
        return input.trim();
    }
}
