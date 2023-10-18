package com.rsp;

public class ComputerTurn {
    private String selectedChoice;
    private String[] options;

    public ComputerTurn(String[] options) {
        this.options = options;
    }

    public void makeChoice() {
        int randomValue = (int) (Math.random() * this.options.length);
        this.selectedChoice = String.valueOf(randomValue);
    }

    public String getSelectedChoice() {
        return this.selectedChoice;
    }
}
