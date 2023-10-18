package com.rsp;

public class Rule {
    private String[] args;

    private int[][] ruleMatrix;

    public Rule(String[] args) {
        this.args = args;
    }

    public void createRules() {
        int n = args.length;
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            int winCount = n / 2;
            int loseCount = n / 2;

            for (int j = 1; j <= n / 2; j++) {
                int prevIdx = (i - j + n) % n;
                int nextIdx = (i + j) % n;

                matrix[i][prevIdx] = -1; // Loose
                winCount--;

                matrix[i][nextIdx] = 1;// Win
                loseCount--;
            }

            // If there is an odd number of elements, one element will neither win nor lose (draw)
            if (n % 2 != 0 && (winCount == 0 || loseCount == 0)) {
                matrix[i][i] = 0;// Draw
            }
        }
        this.ruleMatrix = matrix;
    }
    public int[][] getWinLooseDrawMatrix() {
        int n = args.length;
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            int winCount = n / 2;
            int loseCount = n / 2;

            for (int j = 1; j <= n / 2; j++) {
                int prevIdx = (i - j + n) % n;
                int nextIdx = (i + j) % n;

                matrix[i][prevIdx] = -1; // Loose
                winCount--;

                matrix[i][nextIdx] = 1;// Win
                loseCount--;
            }

            // If there is an odd number of elements, one element will neither win nor lose (draw)
            if (n % 2 != 0 && (winCount == 0 || loseCount == 0)) {
                matrix[i][i] = 0;// Draw
            }
        }
        return matrix;
    }

    public String[] getArgs() {
        return this.args;
    }

    public int[][] getRuleMatrix() {
        return this.ruleMatrix;
    }

    public String defineAWinner(String ComputerChoice, String userChoice) {
        int intComputerChoice = Integer.parseInt(ComputerChoice);
        int intUserChoice = Integer.parseInt(userChoice) - 1;
        if (this.ruleMatrix[intComputerChoice][intUserChoice] == 1) {
            return "You win!";
        }
        if (this.ruleMatrix[intComputerChoice][intUserChoice] == -1) {
            return "You loose :(";
        }
        return "Draw";
    }

}
