package org.example;
public class Logic {
    public static char result = ' ';
    public static double parse;
    public static double parse2;
    public static Number calculateFromStringLogic(String input) throws Exception {
        // убираем лишние пробелы и разбиваем числа \s+ и делим числа и проверяем операторы в строке!
        String[] splits = input.replaceAll("([+\\-*/])", " $1 ").trim().split("\\s+");
        if(splits.length < 2 || splits.length % 2 == 0) { // проверяем границу
         throw new ArithmeticException("Error operator");
        }
        parse = Double.parseDouble(splits[0]); // считываем первое число
        for (int i = 1; i < splits.length; i+=2) {// проходимся по всем числам
            result = splits[i].charAt(0); // присваиваем операторы +,-,*,/
            parse2 = Double.parseDouble(splits[i + 1]); // проходимся по всей строке и добавляем 1 число к длине и проходимся дальше
        switch (result) {
            case '+':
                parse += parse2;
                break;
            case '-':
                parse -= parse2;
                break;
            case '*':
                parse *= parse2;
                break;
            case '/':
                if (parse2 == 0) {
                    throw new ArithmeticException("Error -> " + parse);
                }
                parse /= parse2;
                break;
            default:
                throw new IllegalArgumentException("Error operator");
            }

        }
        double result = parse;
        if(result == (int) result) {
            return (int) result;
        } else {
            return result;
        }
    }
}