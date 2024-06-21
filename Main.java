import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String result = calc(input);
        System.out.println(result);

    }

    public static String calc(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию");
        }

        String left = parts[0];
        String operator = parts[1];
        String right = parts[2];

        boolean isRoman = isRoman(left) && isRoman(right);
        boolean isArabic = isArabic(left) && isArabic(right);

        if (!(isRoman || isArabic)) {
            throw new RuntimeException("Используются одновременно разные системы счисления");
        }

        int num1, num2;
        if (isRoman) {
            num1 = romanToArabic(left);
            num2 = romanToArabic(right);
        } else {
            num1 = Integer.parseInt(left);
            num2 = Integer.parseInt(right);
        }

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new RuntimeException("Числа должны быть в диапазоне от 1 до 10 включительно");
        }

        int result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new RuntimeException("Введен некорректный оператор");
        }

        if (isRoman) {
            if (result < 1) {
                throw new RuntimeException("В римской системе нет отрицательных чисел");
            }
            return arabicToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    private static boolean isRoman(String input) {
        return input.equals("I") || input.equals("II") || input.equals("III") || input.equals("IV") ||
               input.equals("V") || input.equals("VI") || input.equals("VII") || input.equals("VIII") ||
               input.equals("IX") || input.equals("X");
    }

    private static boolean isArabic(String input) {
        try {
            int number = Integer.parseInt(input);
            return number >= 1 && number <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int romanToArabic(String input) {
        switch (input) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                throw new RuntimeException("Введены некорректные римские цифры");
        }
    }

    private static String arabicToRoman(int number) {
        StringBuilder sb = new StringBuilder();
        while (number >= 100) {
            sb.append("C");
            number -= 100;
        }
        while (number >= 90) {
            sb.append("XC");
            number -= 90;
        }
        while (number >= 50) {
            sb.append("L");
            number -= 50;
        }
        while (number >= 40) {
            sb.append("XL");
            number -= 40;
        }
        while (number >= 10) {
            sb.append("X");
            number -= 10;
        }
        while (number >= 9) {
            sb.append("IX");
            number -= 9;
        }
        while (number >= 5) {
            sb.append("V");
            number -= 5;
        }
        while (number >= 4) {
            sb.append("IV");
            number -= 4;
        }
        while (number >= 1) {
            sb.append("I");
            number -= 1;
        }
        return sb.toString();
    }
}
