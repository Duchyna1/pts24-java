package sk.uniba.fmph.dcs.game_board;

import java.util.Random;

public class Throw {
    private int randomDice(){
        return (int) (Math.random() * 6);
    }
    public int[] hod(int dices){
        int[] res = new int[dices];
        for(int i =0;i<dices;i++){
            res[i] = randomDice();
        }
        return res;
    }
}
