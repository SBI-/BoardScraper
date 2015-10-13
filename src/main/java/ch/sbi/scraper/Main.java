package ch.sbi.scraper;

import ch.sbi.scraper.Library.Forum;
import ch.sbi.scraper.Library.DataTypes.Board;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Forum forum = new Forum(args[0]);
            Board pot = forum.getBoardById(14);
            pot.getPageCount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
