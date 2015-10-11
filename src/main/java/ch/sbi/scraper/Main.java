package ch.sbi.scraper;

import ch.sbi.scraper.Library.BoardReader;
import ch.sbi.scraper.Library.DataTypes.Board;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            BoardReader reader = new BoardReader(args[1]);
            List<Board> boards = reader.getBoards();

            for (Board board : boards) {
                System.out.println(board);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
