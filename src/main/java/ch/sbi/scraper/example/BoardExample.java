package ch.sbi.scraper.example;

import ch.sbi.scraper.mapper.CategoryMapper;
import ch.sbi.scraper.datatype.marshalling.Board;
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
        CategoryMapper categoryMapper = new CategoryMapper(sourceBuilder);

        Optional<Board> maybe = categoryMapper.getBoard(14);
        maybe.ifPresent(b -> printInformation(b));
        Optional<Board> maybeNot = categoryMapper.getBoard(3457);
        System.out.println(maybeNot.isPresent());
    }

    private static void printInformation(Board board) {
        String boardInfo = String.format("%s", board.getName());
        String threadInfo = String.format("Page %d / %d",
                board.getThreads().getPage(),
                board.getNumberOfThreads().getValue());

        System.out.println("Board Information: ");
        System.out.println(boardInfo);
        System.out.println("Thread Information: ");
        System.out.println(threadInfo);
    }
}
