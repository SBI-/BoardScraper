package ch.sbi.scraper;

import ch.sbi.scraper.DataTypes.Marshalling.Board;
import ch.sbi.scraper.DataTypes.Marshalling.Categories;
import ch.sbi.scraper.DataTypes.Marshalling.Category;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
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
            URL url = new URL(args[0] + "xml/boards.phpabc");

            String packageName = "ch.sbi.scraper.DataTypes";
            JAXBContext context = JAXBContext.newInstance(packageName);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Source source = new StreamSource(url.openStream());
            // using the unmarshal overload with a specific class is necessary if the class doesn't have / isn't a root
            // element.
            JAXBElement<Categories> forum = unmarshaller.unmarshal(source, Categories.class);
            for (Category category : forum.getValue().getCategory()) {
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
