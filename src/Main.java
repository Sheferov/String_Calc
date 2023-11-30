import java.util.Scanner;
import java.util.regex.Pattern;

class Main_Calc {
    public static void main(String[] args) throws UnsupportedOperationException {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        char operation;
        String[] parts;
        if (str1.contains(" + ")) {
            parts = str1.split(" \\+ ");
            operation = '+';
        } else if (str1.contains(" - ")) {
            parts = str1.split(" - ");
            operation = '-';
        } else if (str1.contains(" * ")) {
            parts = str1.split(" \\* ");
            operation = '*';
        } else if (str1.contains(" / ")) {
            parts = str1.split(" / ");
            operation = '/';
        } else {

            throw new UnsupportedOperationException("Недопустимый знак операции");
        }
        if ((isNumeric(parts[0]) && Integer.valueOf(parts[0])>10 || (isNumeric(parts[1]) && Integer.valueOf(parts[1])>10))){

            throw new UnsupportedOperationException("Введено число больше 10 ");
        }
        if (isNumeric(parts[0])) {

            throw new UnsupportedOperationException("Первым аргументом выражения должна быть строка");
        }
        if (parts[0].length() > 12 || parts[1].length() > 12){

            throw new UnsupportedOperationException("Cтроки длинной не более 10 символов");
        }
        if ((operation == '*' || operation == '/')){

        if(parts[1].contains("\"")) {

            throw new UnsupportedOperationException("Строку невозможно поделить или умножить на строку");
        }
        }
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].replace("\"", "");
        }
        if (operation == '+') {
            printInQuotes(parts[0] + parts[1]);
        } else if (operation == '*') {
            int factor = Integer.parseInt(parts[1]);
            String result = "";
            for (int i = 0; i < factor; i++) {
                result = result + parts[0];
            }
            printInQuotes(result);
        } else if (operation == '-') {
            int minus = parts[0].indexOf(parts[1]);
            if (minus == -1) {
                printInQuotes(parts[0]);
            } else {
                String result = parts[0].substring(0, minus);
                result += parts[0].substring(minus + parts[1].length());
                printInQuotes(result);
            }
        } else {
            int share = parts[0].length() / Integer.parseInt(parts[1]);
            String result = parts[0].substring(0, share);
            printInQuotes(result);
        }
    }

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        public static boolean isNumeric(String strNum) {
            if (strNum == null) {
                return false;
            }
            return pattern.matcher(strNum).matches();
        }

    private static void printInQuotes(String text) {
        if (40 < text.length()){
            System.out.printf("\"%s...\"%n", text.substring(0, 40));
        }else {
            System.out.printf("\"%s\"%n", text);
        }
    }
}
