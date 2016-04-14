package ch.sbi.scraper.example;

import ch.sbi.scraper.controller.ForumController;
import ch.sbi.scraper.datatypes.marshalling.Board;
import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.library.utility.UrlSourceBuilder;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by sbi on 12.04.16.
 */
public class BoardExample {
    public static void printBoard(String baseUrl) throws JAXBException, IOException {
        SourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        ForumController forumController = new ForumController(sourceBuilder);

        Optional<Board> maybe = forumController.getBoard(14);
        Board board = maybe.get();
        printInformation(board);
        Optional<Board> maybeNot = forumController.getBoard(3457);
        System.out.println(maybeNot.isPresent());
    }

    private static void printInformation(Board board) {
        String boardInfo = String.format("%s", board.getName());
        String threadInfo = String.format("Page %d / %d", board.getThreads().getPage(), board.getNumberOfThreads().getValue());
        System.out.println("Board Information: ");
        System.out.println(boardInfo);
        System.out.println("Thread Information: ");
        System.out.println(threadInfo);
    }
}
