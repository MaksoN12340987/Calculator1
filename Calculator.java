import java.util.Scanner;
import java.util.NoSuchElementException;

public class Calculator {
    static void FatalError(String message)
    {
        System.out.println("Ошибка: " + message);
    }

    public static void main(String[] args) {
        System.out.println("Введите формулу:");

        Scanner scanner = new Scanner(System.in);

        String formula = scanner.nextLine();

        scanner = new Scanner(formula);

        MathPart[] parts = new MathPart[3];

        try {
            parts[0] = new MathPart(scanner.next());
            parts[1] = new MathPart(scanner.next());
            parts[2] = new MathPart(scanner.next());
        }
        catch(NoSuchElementException e) {
            FatalError("Формула неверна");

            return;
        }

        /*
        Если первое / второе / третье значение ошибочно
        если первое значение имеет знак операции
        если второе значение не имеет знак операции
        если первое и третье значения не равны по типу
        сгенерировать ошибку и завершить работу
         */
        try {
            if (parts[0].type == TypesPart.NONE || parts[0].type == TypesPart.OPERATION ||
                    parts[1].type != TypesPart.OPERATION || parts[2].type == TypesPart.NONE ||
                    parts[0].type != parts[2].type) {
                throw new FormulaException("Формула неверна");
            }
        }
        catch (FormulaException e)
        {
            FatalError(e.getMessage());

            return;
        }

        switch (parts[1].valueRaw)
        {
            case "+": parts[0].value += parts[2].value;
                break;
            case "-": parts[0].value -= parts[2].value;
                break;
            case "*": parts[0].value *= parts[2].value;
                break;
            case "/": parts[0].value /= parts[2].value;
                break;
        }

        try {
            System.out.println("Результат: " + parts[0].getValue());
        }
        catch (FormulaException e)
        {
            FatalError(e.getMessage());
        }
    }
}