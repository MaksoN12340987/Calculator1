import java.util.Arrays;

class MathPart {
    String valueRaw;
    int value = 0;
    TypesPart type = TypesPart.NONE;
    String[] operations = { "+", "-", "*", "/" };
    String[] romanNumerals = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };

    String[] romanNumeralsMatrix = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII",
            "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI",
            "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
            "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII",
            "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI",
            "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII",
            "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX",
            "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C" };
    MathPart(String value)
    {
        valueRaw = value;

        if (isOperation())
        {
            type = TypesPart.OPERATION;
        }
        else if (isRoman())
        {
            type = TypesPart.ROMAN;
        }
        else if (isArabic())
        {
            type = TypesPart.ARABIC;
        }
    }

    boolean isOperation()
    {
        return Arrays.asList(operations).contains(valueRaw);
    }

    boolean isRoman()
    {
        int position = Arrays.asList(romanNumerals).indexOf(valueRaw);

        if (position == -1)
        {
            return false;
        }

        value = position + 1;

        return true;
    }

    boolean isArabic()
    {
        for (int i = 0; i < valueRaw.length(); i++)
        {
            int digit = valueRaw.charAt(i) - '0';

            if (digit < 0 || digit > 9)
            {
                return false;
            }

            value = value * 10 + digit;
        }

        if (value < 1 || value > 10)
        {
            return false;
        }

        return true;
    }

    String getValue() throws FormulaException
    {
        if (type == TypesPart.ROMAN)
        {
            if (value < 1) {
                throw new FormulaException("Формула неверна");
            }

            return romanNumeralsMatrix[value - 1];
        }

        return String.valueOf(value);
    }
}
