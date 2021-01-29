
package com.ajeet.hackerrank.java;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class ComparatorExample {

    public static void main(String[] args) {


        Player[] players = new Player[5];
        players[0] = new Player("amy", 100);
        players[1] = new Player("david", 100);
        players[2] = new Player("heraldo", 50);
        players[3] = new Player("aakansha", 75);
        players[4] = new Player("aleksa", 150);

        Arrays.sort(players, new Checker());

        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i]);
        }

    }

}

/*
 write a comparator that sorts them in order of decreasing score;
 if 2 or more players have the same score, sort those players alphabetically by name.
* */
class Checker implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {

        if (o1.score == o2.score) {
            return o1.name.compareTo(o2.name);
        }

        return Integer.compare(o2.score, o1.score);
    }
}

class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "[" + name + " " + score + ']';
    }
}
