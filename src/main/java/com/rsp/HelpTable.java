package com.rsp;

import java.util.Arrays;

public class HelpTable {

    private Rule rules;

    public HelpTable(Rule rules){
        this.rules = rules;
    }

    public void printTable() {

        int[] colWidth = getColumnWidth(this.rules.getArgs(), this.rules.getWinLooseDrawMatrix());

        printHorizontalDelimiter(this.rules.getArgs(), colWidth);
        printHeaders(this.rules.getArgs(), colWidth);
        for (int i = 0; i < this.rules.getWinLooseDrawMatrix().length; i++) {
            printHorizontalDelimiter(this.rules.getArgs(), colWidth);
            printRow(this.rules.getArgs()[i], this.rules.getWinLooseDrawMatrix()[i], colWidth);
        }
        printHorizontalDelimiter(this.rules.getArgs(), colWidth);
    }

    private int[] getColumnWidth(String[] headers, int[][] data) {
        // Calculate column widths
        int defaultWidth = 7;
        int[] columnWidths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = headers[i].length();
            for (int[] row : data) {
                if (defaultWidth > columnWidths[i]) {
                    columnWidths[i] = defaultWidth;
                }
            }
        }
        return columnWidths;
    }

    private static void printHorizontalDelimiter(String[] horizontalHeaders,int[] colWidth) {
        int verticalHeaderWidth = Arrays.stream(colWidth).max().getAsInt();

        System.out.print("+");

        for (int vw = 0; vw < verticalHeaderWidth + 2; vw++) {
            System.out.print("-");
        }

        for (int hw = 0; hw < horizontalHeaders.length; hw++) {
            System.out.print("+");
            for (int i = 0; i < colWidth[hw] + 2; i++) {
                System.out.print("-");
            }
            if (hw == horizontalHeaders.length - 1) {
                System.out.println("+");
            }
        }
    }

    private static void printRow(String header, int[] row, int[] colWidth) {

        String format = "";
        int headerWidth = Arrays.stream(colWidth).max().getAsInt();
        format = "%s%-" + (headerWidth + 1) + "s";
        System.out.printf(format, "| ", header);

        for (int i = 0; i < row.length; i++) {
            format = "%s%-" + (colWidth[i] + 1)+ "s";
            if (row[i] == -1) {
                System.out.printf(format,"| ", "Loose");
            }
            if (row[i] == 1) {
                System.out.printf(format,"| ", "Win");
            }
            if (row[i] == 0) {
                System.out.printf(format,"| ", "Draw");
            }
            if (i == row.length - 1) {
                System.out.print("|\n");
            }
        }
    }

    private static void printHeaders(String[] headers, int[] colWidth) {

        String format = "";
        String pcVsUser = "PC/User";
        int headerWidth = pcVsUser.length();
        if (Arrays.stream(colWidth).max().getAsInt() > headerWidth) {
            headerWidth = Arrays.stream(colWidth).max().getAsInt();
        }
        format = "%s%-" + (headerWidth + 1) + "s";
        System.out.printf(format, "| ", "PC/User");

        for (int h = 0; h < headers.length; h++) {
            format = "%s%-" + (colWidth[h] + 1)+ "s";
            System.out.printf(format,"| ", headers[h]);
            if (h == headers.length - 1) {
                System.out.print("|\n");
            }
        }
    }
}
