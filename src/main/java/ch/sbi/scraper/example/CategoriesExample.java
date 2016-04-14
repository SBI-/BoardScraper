package ch.sbi.scraper.example;

import ch.sbi.scraper.controller.ForumMapper;
import ch.sbi.scraper.datatypes.marshalling.Board;
import ch.sbi.scraper.datatypes.marshalling.Category;
import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.library.utility.UrlSourceBuilder;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by sbi on 12.04.16.
 */
public class CategoriesExample {
    public static void printCategories(String baseUrl) throws JAXBException, IOException {
        SourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        ForumMapper forumMapper = new ForumMapper(sourceBuilder);

        for (Category category : forumMapper.getCategories()) {
            System.out.println(category.getName());
            // this check is for private boards
            if (category.getBoards() != null) {
                for (Board board : category.getBoards().getBoard()) {
                    String output = String.format("\t %s, Id: %s, Threadcount: %d",
                            board.getName(),board.getId(), board.getNumberOfThreads().getValue());
                    System.out.println(output);
                }
            }
        }
    }
}
