package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

class Main {

    static int result;
    static int num1;
    static int num2;


    public static void main(String[] args) throws Exception {


        System.out.println("Enter two numbers and the operation sign in the format [2 + 2] or [X - III]:");
        try {

            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine().toUpperCase();  // то что мы хотим прочитать
            String[] numbers = s.split(" ");// разделяем то что пришло
            if(numbers.length != 3)
                throw new Exception("Seriosly?");


            //char op = s.charAt(2);
            char op = numbers[1].charAt(0);
            try {
                num1 = Integer.parseInt(numbers[0]);
                num2 = Integer.parseInt(numbers[2]);
                result = operations(num1, op, num2);
                System.out.println("Output for arabic: \n" + result);

            } catch (NumberFormatException e) {
                num1 = CalculatorHelper.romanToArabic((numbers[0]));
                num2 = CalculatorHelper.romanToArabic((numbers[2]));
                result = operations(num1, op, num2);
                if (result < 1) throw new Exception("Miss!");
                System.out.println("Output for Roman: \n" + CalculatorHelper.arabicToRoman(result));

            }

        } catch(Exception e) {
            System.err.println(e.getMessage());
        }

        }

        private static int operations ( int num1, char op, int num2) throws Exception {
            check(num1, num2);
            return switch (op) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> result = num1 / num2;
                default -> throw new Exception("Invalid operator!");
            };
        }

        private static void check ( int num1, int num2) throws Exception {
            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10)
                throw new Exception("Only 1 - 10 numbers.");
        }


    }

    class CalculatorHelper {

        static int romanToArabic(String roman) throws Exception { //римляне маскируются в арабов -.-
            return switch (roman) {
                case "I" -> 1;
                case "II" -> 2;
                case "III" -> 3;
                case "IV" -> 4;
                case "V" -> 5;
                case "VI" -> 6;
                case "VII" -> 7;
                case "VIII" -> 8;
                case "IX" -> 9;
                case "X" -> 10;
                default -> throw new Exception("Try again.");

            };
        }

        static String arabicToRoman(int arabic) { //арабские в римские так< как отвеt может быть больше 10
            String roman = switch (arabic / 10) {
                case 1 -> "X";
                case 2 -> "XX";
                case 3 -> "XXX";
                case 4 -> "XL";
                case 5 -> "L";
                case 6 -> "LX";
                case 7 -> "LXX";
                case 8 -> "LXXX";
                case 9 -> "XC";
                case 10 -> "C";
                default -> "";

            };
            return roman;

        }
    }