package com.rsp;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        if (args.length < 3) {
            System.out.println("You need to pass at least 3 arguments");
            System.exit(1);
        }
        if (args.length % 2 == 0) {
            System.out.println("Please pass odd number of arguments (e.g: rock scissors paper carrot tomato)");
            System.exit(1);
        }

        Game game = new Game(args);
        game.play();
    }

}
