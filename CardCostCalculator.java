import java.util.Scanner;

public class CardCostCalculator {

    // 9, 7, 4, 1
    private static final int COMMON = 0;
    private static final int RARE = 2;
    private static final int EPIC = 5;
    private static final int LEGENDARY = 8;

    //legendary is 5000 instead of 8000 (5k vs. 8k)                            v here v
    private static final int[] gold =  {0, 5, 20, 50, 150, 400, 1000, 2000, 4000, 8000, 20000, 50000, 100000};
    private static final int[] cards = {1, 2, 4,  10, 20,  50,  100,  200,  400,  800,  1000,  2000,  5000};
    private static final int[] exp =   {0, 4, 5,  6,  10,  25,  50,   100,  200,  400,  600,   800,   1600};
    private static final int[] cap = {250, 100, 50, 10};

    public static void main(String[] args) {

        Scanner scans = new Scanner(System.in);
        String input;
        int rarity = 0;
        int level = 0;
        int cardStart = 0;
        int cardEnd = 0;


        //input: [rarity] [level] [cardStart]
        rarity = makeRarity(scans);
        level = makeLevel(scans);
        cardStart = makeCardStart(scans);

        System.out.printf("[DEBUG] rarity:%d, level:%d, cardStart:%d\n", rarity, level, cardStart);

    } //main

    private static int makeRarity(Scanner scans) {
        int result = 0;
        String input;

        System.out.printf("[CardCostCalculator] Please input card rarity (c/r/e/l): ");
        rarity:
        while (scans.hasNext()) {
            input = scans.nextLine();
            if (input.length() == 0) {
                System.out.printf("In valid input. Please enter rarity [c/r/e/l]: ");
                continue;
            }
            char ch = input.charAt(0);
            switch (ch) {
                case 'c':
                    result = COMMON;
                    break rarity;
                case 'r':
                    result = RARE;
                    break rarity;
                case 'e':
                    result = EPIC;
                    break rarity;
                case 'l':
                    result = LEGENDARY;
                    break rarity;
                default:
                    System.out.printf("In valid input. Please enter rarity [c/r/e/l]: ");
            }

        } //rarity

        return result;
    } //makeRarity

    private static int makeLevel(Scanner scans) {
        int result = 0;
        String input;

        System.out.printf("[CardCostCalculator] Please input current level (1-13): ");
        while (scans.hasNext()) {
            input = scans.nextLine();
            try {
                result = Integer.parseInt(input);
                if (result>= 1 && result<= 13) {
                    break;
                }
            }
            catch (NumberFormatException e) {
                result= 0;
            }
            System.out.printf("[CardCostCalculator] Invalid input. Please input card level (1-13): ");
        } //level

        return result;
    } //makeLevel

    private static int makeCardStart(Scanner scans) {
        int result = 0;
        String input;

        System.out.printf("[CardCostCalculator] Please input card count: ");
        while (scans.hasNext()) {
            input = scans.nextLine();
            try {
                result = Integer.parseInt(input);
                if (result >=0) {
                    break;
                }
            }
            catch (NumberFormatException e) {
                result = 0;
            }
            System.out.printf("[CardCostCalculator] Invalid input. Please input card count: ");
        } //cardStart

        return result;
    }


} //CardCostCalculator
