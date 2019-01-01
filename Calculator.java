package edu.purdue.gbowman.cardcostcalulator;

import java.util.Locale;

public class Calculator {

    // 9, 7, 4, 1 -> 0, 2, 5, 8 offsets
    private static final int COMMON = 0;
    private static final int RARE = 1;
    private static final int EPIC = 2;
    private static final int LEGENDARY = 3;

    //legendary is 5000 instead of 8000 (5k vs. 8k)                            v here v
    //                           level:  1  2  3   4   5    6    7     8     8     9     10     11     12      13
    private static final int[]   gold = {0, 5, 20, 50, 150, 400, 1000, 2000, 4000, 8000, 20000, 50000, 100000, 0};
    private static final int[]  cards = {1, 2, 4 , 10, 20 , 50 , 100 , 200 , 400 , 800 , 1000 , 2000 , 5000,   0};
    private static final int[] points = {0, 4, 5 , 6 , 10 , 25 , 50  , 100 , 200 , 400 , 600  , 800  , 1600,   0};
    //card cap overflow was removed in a recent update!
    //private static final int[] cardCap = {250, 100, 50, 10};

    private int exp;

    //OLD static calculate() method (changed cuz OOP)
    static{
//    /* calculate()
//     * Replies, level by level, the accumulative cost, exp, and cards remaining after the level up.
//     * Then shows final result if followed all the way through the upgrades.
//     */
//    private static void calculate() {
//        System.out.printf("Static calculate\n");
//        int x = 0; //used to ease card cap checking
//
//        System.out.printf("\n[CCC] NOTE: All values are accumulative from previous step!\n");
//        while (level < 13 && cardStart > 0) {
//            if (cardStart >= cards[level-rarity]) {
//                cardStart -= cards[level-rarity];
//                if (rarity == LEGENDARY && level == 9) cost += 5000;
//                else cost += gold[level];
//                exp += points[level];
//                level++;
//                System.out.printf("[CCC] To level %2d (%4d cards): -%6dg, %4d cards left\n",
//                        level, cards[level-1-rarity], cost, cardStart); //+%4decp
//            }
//            else break;
//        }
//        if (level == 13) {
//            switch (rarity) {
//                case 0: x = 0; break;
//                case 2: x = 1; break;
//                case 5: x = 2; break;
//                case 8: x = 3; break;
//                default: x = 0;
//            }
//            if (cardStart > cardCap[x]) cardStart = cardCap[x];
//        }
//        if (level < 13) {
//            System.out.printf("[CCC] This leaves you with (%d/%d) cards towards level (%d) having spent %dg\n", cardStart, cards[level - rarity], level + 1, cost);
//        }
//        else if (level == 13) {
//            System.out.printf("[CCC] Level 13! %d/%d extra\n", cardStart, cardCap[x]);
//        }
//    }//calculate
    }


    /**
     * given parameters, calculates stages of upgrading
     * @param rarity (0,2,5,8) valid inputs
     * @param level
     * @param cardStart
     * @return
     */
    public String calculate(int rarity, int level, int cardStart) {
        int offset = rarityToOffset(rarity);
        int x = 0; //used to ease card cap checking
        int cost = 0;
        //buffer the output text in the StringBuffer to return at the end
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(Locale.US, "[CCC] NOTE: Values shown as (per stage): accumulative\n"));

        //calculation loop, adding cost, exp, and level while decrementing card #
        while (level < 13 && cardStart > 0) {
            if (cardStart >= cards[level-offset]) {
                cardStart -= cards[level-offset];
                if (rarity == LEGENDARY && level == 9) cost -= 5000;
                else if (rarity == EPIC && level == 6) cost -= 400;
                else cost -= gold[level];
                exp += points[level];
                level++;
                sb.append(String.format(Locale.US, "[CCC] To level %2d (%4d cards/%5dg): %6dg, %4d left\n",
                        level, cards[level-1-offset], gold[level-1], cost, cardStart));
            }
            else break;
        }
        if (level < 13) {
            sb.append(String.format(Locale.US, "[CCC] This leaves you with (%d/%d) cards towards level %d having spent %dg\n",
                    cardStart, cards[level-offset], level + 1, cost*-1));
        }
        else if (level == 13) {
            sb.append(String.format(Locale.US, "[CCC] Card will be maxed at Level 13!\n"));
        }

        return sb.toString();
    }

    public int rarityToOffset(int rarity) {
        switch (rarity) {
            case 0: return 0;
            case 1: return 2;
            case 2: return 5;
            case 3: return 8;
            default: return 0;
        }
    }

} //CardCostCalculator

