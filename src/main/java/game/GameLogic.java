package game;

import com.app.fourinline.GameGrid;

import java.util.Random;

public class GameLogic {

    // n - blank
    // r - red token
    // y - yellow token
    private static char opponent;
    private static char localp;
    private static char ntp;          // next to play ('r' or 'y')
    private static String LAST_MOVE;  // example:  "r5"
    private static char[][] gameMtx;


    public static void init() {
        gameMtx = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                gameMtx[i][j] = 'n';
            }
        }
        // random colors
        Random r = new Random();
        if (r.nextInt(2) % 2 == 0) { opponent = 'r'; localp = 'y'; }
        else { opponent = 'y'; localp = 'r'; }

        // random ftp
        if (r.nextInt(2) % 2 == 0) ntp = 'r';
        else ntp = 'y';


    }





    public static void move(char player, int index, GameGrid g) {
        for (int i = 0; i < 6; i++) {
            if (i == 5 || gameMtx[i+1][index] != 'n') {
                gameMtx[i][index] = player;
                g.animate(index+1,i+1,player);
                return;
            }
        }
    }

    public static void updateNtp() {
        if (ntp == 'r') ntp = 'y';
        else ntp = 'r';
    }






    public static void print() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(gameMtx[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }





    public static char getOpponent() {
        return opponent;
    }

    public static char getLocalp() {
        return localp;
    }

    public static char getNtp() {
        return ntp;
    }

    public static char[][] getGameMtx() {
        return gameMtx;
    }

    public static String getLastMove() {
        return LAST_MOVE;
    }

    public static void setLastMove(String lastMove) {
        LAST_MOVE = lastMove;
    }
}
