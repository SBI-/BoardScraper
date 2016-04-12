package ch.sbi.scraper;

import ch.sbi.scraper.controller.ForumController;
import ch.sbi.scraper.datatypes.marshalling.Board;
import ch.sbi.scraper.datatypes.marshalling.Category;
import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.library.utility.UrlSourceBuilder;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {


    private static Logger logger = Logger.getLogger(Main.class.getName());

    static {
        try {
            System.setProperty("java.util.logging.config.file", "default.logging.properties");
            LogManager manager = LogManager.getLogManager();
            manager.readConfiguration();
        } catch (IOException e) {
            System.out.println("Setting up log configuration failed.");
        }
    }

    public static void main(String[] args) {
        try {
            SourceBuilder sourceBuilder = new UrlSourceBuilder(args[0]);
            ForumController forumController = new ForumController(sourceBuilder);

            for (Category category : forumController.getCategories()) {
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
        } catch (IOException e) {
            logger.severe("Url failed to open. Reason: " + e);
        } catch (JAXBException e) {
            logger.info("JAXB Error: " + e);
            e.printStackTrace();
        }
    }
}
