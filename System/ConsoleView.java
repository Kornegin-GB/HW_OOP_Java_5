package HW_OOP_Java_5.System;

import HW_OOP_Java_5.Main;

import java.util.Collections;

public class ConsoleView {
    private static final String TOP10 = formatDiv("a") + String.join("", Collections.nCopies(Main.getFieldWidth() - 1, formatDiv("-b"))) + formatDiv("-c");
    private static final String MID10 = formatDiv("d") + String.join("", Collections.nCopies(Main.getFieldWidth() - 1, formatDiv("-e"))) + formatDiv("-f");
    private static final String BOTTOM10 = formatDiv("g") + String.join("", Collections.nCopies(Main.getFieldWidth() - 1, formatDiv("-h"))) + formatDiv("-i");
    private static int step = 0;

    public static void view() {
        if (step == 0) {
            System.out.printf(AnsiColors.ANSI_RED + "%-60s", "Начало игры." + AnsiColors.ANSI_RESET);
        } else {
            System.out.printf(AnsiColors.ANSI_RED + "%-60s", "Ход №" + step + "." + AnsiColors.ANSI_RESET);
        }
        System.out.printf(AnsiColors.ANSI_BLUE + "%-85s", "Команда синих." + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_GREEN + "Команда зеленых." + AnsiColors.ANSI_RESET);
        step++;

        System.out.println(ConsoleView.TOP10);
        for (int i = 1; i <= Main.getGangSize() - 1; i++) {
            for (int j = 1; j <= Main.getFieldWidth(); j++) {
                System.out.print(getChar(new Vector2D(j, i)));
            }
            System.out.print("|");
            System.out.println(getPrintSide(i));
            System.out.println(ConsoleView.MID10);
        }

        for (int j = 1; j <= Main.getFieldWidth(); j++) {
            System.out.print(getChar(new Vector2D(j, Main.getGangSize())));
        }
        System.out.print("|");
        System.out.println(getPrintSide(Main.getGangSize()));
        System.out.println(ConsoleView.BOTTOM10);
        System.out.println("Нажмите Enter.");
    }

    private static String getPrintSide(int i) {
        String str = "";
        str += String.format("\t\t\t" + "%-90s", AnsiColors.ANSI_BLUE + Main.getBlueSide().get(i - 1).getInfo() + AnsiColors.ANSI_RESET);
        str += String.format(AnsiColors.ANSI_GREEN + Main.getGreenSide().get(i - 1).getInfo() + AnsiColors.ANSI_RESET);
        return str;
    }

    private static String formatDiv(String str) {
        return str.replace('a', '\u250c')
                .replace('b', '\u252c')
                .replace('c', '\u2510')
                .replace('d', '\u251c')
                .replace('e', '\u253c')
                .replace('f', '\u2524')
                .replace('g', '\u2514')
                .replace('h', '\u2534')
                .replace('i', '\u2518')
                .replace('-', '\u2500');
    }

    private static String getChar(Vector2D position) {
        String str = "| ";
        for (int i = 0; i < Main.getGangSize(); i++) {
            if (Main.getBlueSide().get(i).getPosition().isEquals(position)) {
                if (!Main.getBlueSide().get(i).getStatus().equals("Мертв")) {
                    str = "|" + AnsiColors.ANSI_BLUE + Main.getBlueSide().get(i).getName().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET;
                } else {
                    str = "|" + AnsiColors.ANSI_RED + Main.getBlueSide().get(i).getName().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET;
                }
            }
            if (Main.getGreenSide().get(i).getPosition().isEquals(position)) {
                if (!Main.getGreenSide().get(i).getStatus().equals("Мертв")) {
                    str = "|" + AnsiColors.ANSI_GREEN + Main.getGreenSide().get(i).getName().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET;
                } else {
                    str = "|" + AnsiColors.ANSI_RED + Main.getGreenSide().get(i).getName().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET;
                }

            }
        }
        return str;
    }
}

