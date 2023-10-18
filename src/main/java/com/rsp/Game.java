package com.rsp;

import com.rsp.crypto.HMAC;
import com.rsp.crypto.Key;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
//    private com.rsp.Rule rule;

    private String[] args;

    public Game(String[] args) {
        this.args = args;
    }

    public void play() throws NoSuchAlgorithmException, InvalidKeyException {
        Rule rule = new Rule(args);
        rule.createRules();

        // generating secret key
        String key = Key.generateKey();
        ComputerTurn computerTurn = new ComputerTurn(args);
        // computer makes random choice
        computerTurn.makeChoice();
        String computerSelected = computerTurn.getSelectedChoice();
        // generating HMAC
        System.out.println("HMAC: " + HMAC.generateHmac(args[Integer.parseInt(computerSelected)], key));

        Scanner sc = new Scanner(System.in);
        boolean selectingMove = true;
        ArrayList<String> availableMoves = getAvailableMoves();
        String userSelected = "";

        do {
            printMenu();
            System.out.print("Enter your move: ");
            String input = sc.nextLine();

            if (input.equals("?")) {
                HelpTable helpTableGenerator = new HelpTable(rule);
                helpTableGenerator.printTable();
                continue;
            }

            if (input.equals("0")) {
                System.exit(1);
            }

            try {
                int selectedOption = Integer.parseInt(input) - 1;
                if (selectedOption < availableMoves.size() && selectedOption >= 0) {
                    for (String availableMove : availableMoves) {
                        if (input.equals(availableMove)) {
                            userSelected = input;
                            selectingMove = false;
                            System.out.println();
                            break;
                        }
                    }
                } else {
                    System.out.println("Please, enter valid move.");
                }
            } catch (NumberFormatException nfex) {
                System.out.println("Please, enter valid move.");
            }

        } while (selectingMove);

        System.out.println(rule.defineAWinner(computerSelected, userSelected));
        System.out.println("Your move: " + this.args[Integer.parseInt(userSelected) - 1]);
        System.out.println(("Computer move: " + this.args[Integer.parseInt(computerSelected)]));
        System.out.println("HMAC Key: " + key);

    }

    private void printMenu() {
        System.out.println("Available moves");

        int i = 0;
        for (String option : args) {
            System.out.printf("%s%s%s", (i + 1), " - ", option);
            System.out.println();
            i++;
        }
        System.out.println("0 - exit");
        System.out.println("? - help");
        System.out.println();
    }

    private ArrayList<String> getAvailableMoves() {
        ArrayList<String> availableMoves = new ArrayList<>(args.length);
        for (int i = 0; i < args.length; i++) {
            availableMoves.add(String.valueOf(i + 1));
        }
        return availableMoves;
    }
}
