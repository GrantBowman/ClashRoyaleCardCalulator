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
    private static final int[] points =   {0, 4, 5,  6,  10,  25,  50,   100,  200,  400,  600,   800,   1600};
    private static final int[] cardCap = {250, 100, 50, 10};


    private static int rarity = 0;
    private static int level = 0;
    private static int cardStart = 0;
    private static int cost = 0;
    private static int exp = 0;

    public static void main(String[] args) {

        Scanner scans = new Scanner(System.in);

        //input: [rarity] [level] [cardStart]
        makeRarity(scans);
        makeLevel(scans);
        makeCardStart(scans);

        //System.out.printf("[DEBUG] rarity:%d, level:%d, cardStart:%d, gold:%d\n", rarity, level, cardStart, cost);
        calculate();
        //System.out.printf("[DEBUG] rarity:%d, level:%d, cardStart:%d, gold:%d\n", rarity, level, cardStart, cost);

    } //main


    /* makeRarity(Scanner scans)
     * Prompts the user for card rarity input. Scans only the first character of their input.
     * If the character matches Common, Rare, Epic, or Legendary, it will read that letter and
     * assign that rarity. If input does not match, it will prompt again until successful.
     */
    private static void makeRarity(Scanner scans) {
        String input;

        System.out.printf("[CCC] Please input card rarity (c/r/e/l): ");
        rarity:
        while (scans.hasNext()) {
            input = scans.nextLine();
            input = input.toLowerCase();
            if (input.length() == 0) {
                System.out.printf("[CCC] Invalid input. Please enter rarity [c/r/e/l]: ");
                continue;
            }
            char ch = input.charAt(0);
            switch (ch) {
                case 'c':
                    rarity = COMMON;
                    break rarity;
                case 'r':
                    rarity = RARE;
                    break rarity;
                case 'e':
                    rarity = EPIC;
                    break rarity;
                case 'l':
                    rarity = LEGENDARY;
                    break rarity;
                default:
                    System.out.printf("[CCC] Invalid input. Please enter rarity [c/r/e/l]: ");
            }

        } //rarity

    } //makeRarity


    /* makeLevel(Scanner scans)
     * Prompts the user for the level the card is at. This will be asked after the rarity is give,
     * so there will be a boundary between the lowest possible level and level 13 (max). Keeps prompting
     * until successful input. (int input)
     */
    private static void makeLevel(Scanner scans) {
        String input;

        System.out.printf("[CCC] Please input current level (%d-13): ", rarity+1);
        while (scans.hasNext()) {
            input = scans.nextLine();
            try {
                level = Integer.parseInt(input);
                if (level >= (rarity+1) && level <= 13) {
                    break;
                }
            }
            catch (NumberFormatException e) {
                level = 0;
            }
            System.out.printf("[CCC] Invalid input. Please input card level (%d-13): ", rarity+1);
        } //level

    } //makeLevel


    /* makeCardStart(Scanner scans)
     * Prompts the user for how many cards they currently have of the card. This will be used for
     * calculating how far they can progress leveling it. Keeps prompting until successful. (int input)
     */
    private static void makeCardStart(Scanner scans) {
        String input;

        System.out.printf("[CCC] Please input card count: ");
        while (scans.hasNext()) {
            input = scans.nextLine();
            try {
                cardStart = Integer.parseInt(input);
                if (cardStart >=0) {
                    break;
                }
            }
            catch (NumberFormatException e) {
                cardStart = 0;
            }
            System.out.printf("[CCC] Invalid input. Please input card count: ");
        } //cardStart

    }


    /* calculate()
     * Replies, level by level, the accumulative cost, exp, and cards remaining after the level up.
     * Then shows final result if followed all the way through the upgrades.
     */
    private static void calculate() {
        int x = 0; //used to ease card cap checking

        System.out.printf("\n[CCC] NOTE: All values are accumulative from previous step!\n");
        while (level < 13 && cardStart > 0) {
            if (cardStart >= cards[level-rarity]) {
                cardStart -= cards[level-rarity];
                if (rarity == LEGENDARY && level == 9) cost += 5000;
                else cost += gold[level];
                exp += points[level];
                level++;
                System.out.printf("[CCC] To level %2d (%4d cards): -%6dg, %4d cards left\n",
                        level, cards[level-1-rarity], cost, cardStart); //+%4decp
            }
            else break;
        }
        if (level == 13) {
            switch (rarity) {
                case 0: x = 0; break;
                case 2: x = 1; break;
                case 5: x = 2; break;
                case 8: x = 3; break;
                default: x = 0;
            }
            if (cardStart > cardCap[x]) cardStart = cardCap[x];
        }
        if (level < 13) {
            System.out.printf("[CCC] This leaves you with (%d/%d) cards towards level (%d) having spent %dg\n", cardStart, cards[level - rarity], level + 1, cost);
        }
        else if (level == 13) {
            System.out.printf("[CCC] Level 13! %d/%d extra\n", cardStart, cardCap[x]);
        }
    }

} //CardCostCalculator
