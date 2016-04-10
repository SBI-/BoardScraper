package ch.sbi.scraper;

import ch.sbi.scraper.controller.ForumController;
import ch.sbi.scraper.datatypes.marshalling.Board;
import ch.sbi.scraper.datatypes.marshalling.Category;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
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
            URL url = new URL(args[0] + "xml/boards.php");

            ForumController forumController = ForumController.initializeAccessible(url);

            for (Category category : forumController.getForum().getValue().getCategory()) {
                System.out.println(category.getName());
                // this check is for private boards
                if (category.getBoards() != null) {
                    for (Board board : category.getBoards().getBoard()) {
                        System.out.println("\t" + board.getName() + ": " + board.getId());
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
