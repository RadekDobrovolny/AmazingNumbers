package numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public enum Properties {
        BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SUNNY, SQUARE, JUMPING, HAPPY, SAD, EVEN, ODD
    }

    public static List<Properties> findCommonProperties(List<Properties> list1, List<Properties> list2) {
        List<Properties> commonValues = new ArrayList<Properties>();
        for (Properties value : list1) {
            if (list2.contains(value)) {
                commonValues.add(value);
            }
        }
        return commonValues;
    }

    public static boolean checkInput (String[] args) {
        if (args.length >= 1) {
            String numberStr = args[0];

            if (!isNatural(numberStr)) {
                System.out.println("The first parameter should be a natural number or zero.");
                return false;
            }
        } else {
            return false;
        }

        if (args.length >= 2) {
            String consecStr = args[1];
            if (!isNatural(consecStr)) {
                System.out.println("The second parameter should be a natural number.");
                return false;
            }
        }

        if (args.length >= 3) {

            List<Properties> selectedProperties = new ArrayList<Properties>();
            List<Properties> disabledProperties = new ArrayList<Properties>();
            List<String> wrongProperties = new ArrayList<String>();

            for (int i = 2; i < args.length; i++) {
                try {
                    String propertyCandidate = args[i];
                    if (propertyCandidate.startsWith("-")) {
                        propertyCandidate = propertyCandidate.substring(1);
                        Properties p = Properties.valueOf(propertyCandidate.toUpperCase());
                        disabledProperties.add(p);
                    } else {
                        Properties p = Properties.valueOf(propertyCandidate.toUpperCase());
                        selectedProperties.add(p);
                    }
                } catch (IllegalArgumentException e) {
                    wrongProperties.add(args[i]);
                }
            }

            StringBuilder output = new StringBuilder();
            if (wrongProperties.isEmpty()) {

                if (selectedProperties.contains(Properties.ODD) && selectedProperties.contains(Properties.EVEN)) {
                    System.out.println("The request contains mutually exclusive properties: [" + Properties.ODD + ", " + Properties.EVEN + "]\n" +
                            "There are no numbers with these properties.");
                    return false;
                }

                if (disabledProperties.contains(Properties.ODD) && disabledProperties.contains(Properties.EVEN)) {
                    System.out.println("The request contains mutually exclusive properties: [" + Properties.ODD + ", " + Properties.EVEN + "]\n" +
                            "There are no numbers with these properties.");
                    return false;
                }

                if (selectedProperties.contains(Properties.SUNNY) && selectedProperties.contains(Properties.SQUARE)) {
                    System.out.println("The request contains mutually exclusive properties: [" + Properties.SUNNY + ", " + Properties.SQUARE + "]\n" +
                            "There are no numbers with these properties.");
                    return false;
                }

                if (disabledProperties.contains(Properties.SUNNY) && disabledProperties.contains(Properties.SQUARE)) {
                    System.out.println("The request contains mutually exclusive properties: [" + Properties.SUNNY + ", " + Properties.SQUARE + "]\n" +
                            "There are no numbers with these properties.");
                    return false;
                }

                if (selectedProperties.contains(Properties.SPY) && selectedProperties.contains(Properties.DUCK)) {
                    System.out.println("The request contains mutually exclusive properties: [" + Properties.SPY + ", " + Properties.DUCK + "]\n" +
                            "There are no numbers with these properties.");
                    return false;
                }

                if (disabledProperties.contains(Properties.SPY) && disabledProperties.contains(Properties.DUCK)) {
                    System.out.println("The request contains mutually exclusive properties: [" + Properties.SPY + ", " + Properties.DUCK + "]\n" +
                            "There are no numbers with these properties.");
                    return false;
                }

                List<Properties> commonProperties = findCommonProperties(selectedProperties, disabledProperties);
                if (! commonProperties.isEmpty()) {
                    System.out.println("The request contains mutually exclusive properties: [" + commonProperties.get(0) + ", -" + commonProperties.get(0) + "]");
                    System.out.println("There are no numbers with these properties.");
                    return false;
                }


                return true;
            } else if (wrongProperties.size() == 1) {
                output.append("The property [");
                output.append(wrongProperties.get(0));
                output.append("] is wrong.");
            } else {
                output.append("The properties [");
                for (String s : wrongProperties) {
                    output.append(s);
                    output.append(" ");
                }
                output.append("] are wrong.");
            }

            output.append("\n");
            output.append("Available properties: [");
            Properties[] properties = Properties.values();
            for (Properties p: properties) {
                output.append(p);
                output.append(" ");
            }
            output.append("]");
            System.out.println(output.toString());
            return false;
        }

        return true;
    }

    public static List<Properties> checkNumber (String numberStr) {
        List<Properties> properties = new ArrayList<Properties>();

        long number = Long.parseLong(numberStr);

        if (isBuzzNumber(number)) {
            properties.add(Properties.BUZZ);
        }
        if (isDuckNumber(number)) {
            properties.add(Properties.DUCK);
        }
        if (isPalindromic(number)) {
            properties.add(Properties.PALINDROMIC);
        }
        if (isGapful(number)) {
            properties.add(Properties.GAPFUL);
        }
        if (isSpy(number)) {
            properties.add(Properties.SPY);
        }
        if (isSunny(number)) {
            properties.add(Properties.SUNNY);
        }
        if (isSquare(number)) {
            properties.add(Properties.SQUARE);
        }
        if (isJumping(number)) {
            properties.add(Properties.JUMPING);
        }
        if (isHappy(number)) {
            properties.add(Properties.HAPPY);
        } else {
            properties.add(Properties.SAD);
        }
        if (isEven(number)) {
            properties.add(Properties.EVEN);
        } else {
            properties.add(Properties.ODD);
        }

        return properties;
    }

    public static String numberLine (String number) {
        StringBuilder output = new StringBuilder(number + " is ");

        List<Properties> properties = checkNumber(number);

        for (int i = 0; i < properties.size(); i++) {
            output.append(properties.get(i));
            if (i < properties.size() - 1) {
                output.append(", ");
            }
        }
        return output.toString();
    }

    public static boolean isNatural(String number) {
        if (number.isEmpty()) {
            return false; // Empty string is not a natural number
        }

        if (number.charAt(0) == '-') {
            return false; // Negative sign at the beginning
        }

        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false; // Non-digit character found
            }
        }

        return true; // All characters are digits and no negative sign
    }

    public static boolean isEven(long number) {
        return number % 2 == 0;
    }


    public static boolean isDivisibleBy7(long number) {
        return number % 7 == 0;
    }

    public static boolean endsWith7(long number) {
        return number % 10 == 7;
    }

    public static boolean isBuzzNumber(long number) {
        return isDivisibleBy7(number) || endsWith7(number);
    }

    public static boolean isDuckNumber(long number) {
        String numStr = String.valueOf(number);
        return numStr.contains("0");
    }

    public static boolean isPalindromic(long number) {
        String numberStr = String.valueOf(number);
        String reversedText = new StringBuilder(numberStr).reverse().toString();
        return numberStr.equals(reversedText);
    }

    public static boolean isGapful(long number) {
        if (number < 100) {
            return false;  // Gapful numbers must have at least 3 digits
        }

        int firstDigit = Integer.parseInt(Long.toString(number).substring(0, 1));
        long lastDigit = number % 10;
        int divisor = Integer.parseInt(Integer.toString(firstDigit) + Long.toString(lastDigit));

        return number % divisor == 0;
    }

    public static boolean isSpy(long number) {
        String numberStr = String.valueOf(number);
        int sum = 0;
        int product = 1;

        for (int i = 0; i < numberStr.length(); i++) {
            int digit = Character.getNumericValue(numberStr.charAt(i));
            sum += digit;
            product *= digit;
        }

        return sum == product;
    }

    public static boolean isSunny(long number) {
        long nextNumber = number + 1;
        long sqrt = (long) Math.sqrt(nextNumber);
        return sqrt * sqrt == nextNumber;
    }

    public static boolean isSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    public static boolean isJumping(long number) {
        String numStr = String.valueOf(number);

        for (int i = 0; i < numStr.length() - 1; i++) {
            int diff = Math.abs(numStr.charAt(i) - numStr.charAt(i + 1));
            if (diff != 1) {
                return false; // If difference is not 1, it's not a jumping number
            }
        }

        return true; // All adjacent digits have a difference of 1
    }

    public static boolean isHappy(long number) {
        while (number != 1 && number != 4) {
            long sum = 0;
            while (number > 0) {
                long digit = number % 10;
                sum += digit * digit;
                number /= 10;
            }
            number = sum;
        }

        return number == 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");

        String input;
        while (true) {
            System.out.println();
            System.out.print("Enter a request: ");
            input = scanner.nextLine();

            if (input.equals("0")) {
                break;
            }

            String[] inputArgs = input.split(" ");
            if (!checkInput(inputArgs)) {
                continue;
            }

            if (inputArgs.length == 1) {
                String number = inputArgs[0];

                List<Properties> properties = checkNumber(number);

                System.out.println("Properties of " + number);
                System.out.println("buzz: " + properties.contains(Properties.BUZZ));
                System.out.println("duck: " + properties.contains(Properties.DUCK));
                System.out.println("palindromic: " + properties.contains(Properties.PALINDROMIC));
                System.out.println("gapful: " + properties.contains(Properties.GAPFUL));
                System.out.println("spy: " + properties.contains(Properties.SPY));
                System.out.println("sunny: " + properties.contains(Properties.SUNNY));
                System.out.println("square: " + properties.contains(Properties.SQUARE));
                System.out.println("jumping: " + properties.contains(Properties.JUMPING));
                System.out.println("happy: " + properties.contains(Properties.HAPPY));
                System.out.println("sad: " + properties.contains(Properties.SAD));
                System.out.println("even: " + properties.contains(Properties.EVEN));
                System.out.println("odd: " + properties.contains(Properties.ODD));
            }

            if (inputArgs.length == 2) {
                String numberStr = inputArgs[0];
                String consecStr = inputArgs[1];

                long number = Long.parseLong(numberStr);
                int consec = Integer.parseInt(consecStr);

                for (int i = 0; i < consec; i++) {
                    System.out.println(numberLine(String.valueOf(number + i)));
                }
            }

            if (inputArgs.length >= 3) {
                String numberStr = inputArgs[0];
                String consecStr = inputArgs[1];
                List<Properties> selectedProperties = new ArrayList<Properties>();
                List<Properties> disabledProperties = new ArrayList<Properties>();

                int count = Integer.parseInt(consecStr);
                long n = Long.parseLong(numberStr);

                for (int i = 2; i < inputArgs.length; i++) {
                    String property = inputArgs[i];

                    if (property.startsWith("-")) {
                        disabledProperties.add(Properties.valueOf(property.substring(1).toUpperCase()));
                    } else {
                        selectedProperties.add(Properties.valueOf(property.toUpperCase()));
                    }
                }

                while (count > 0) {
                    List<Properties> properties = checkNumber(String.valueOf(n));

                    boolean fits = true;

                    for (Properties p : selectedProperties) {
                        if (!properties.contains(p)) {
                            fits = false;
                            break;
                        }
                    }

                    for (Properties p : disabledProperties) {
                        if (properties.contains(p)) {
                            fits = false;
                            break;
                        }
                    }

                    if (fits) {
                        count--;
                        System.out.println(numberLine(String.valueOf(n)));
                    }
                    n++;
                }
            }

            System.out.println();
        }
    }
}
