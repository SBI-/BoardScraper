package ch.sbi.scraper;

import ch.sbi.scraper.Library.Forum;
import ch.sbi.scraper.Library.DataTypes.Board;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Forum forum = new Forum(args[1]);
            List<Board> boards = forum.getBoards();

            for (Board board : boards) {
                System.out.println(board);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
