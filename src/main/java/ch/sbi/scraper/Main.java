package ch.sbi.scraper;

import ch.sbi.scraper.DataTypes.Board;
import ch.sbi.scraper.DataTypes.Boards;
import ch.sbi.scraper.DataTypes.Categories;
import ch.sbi.scraper.DataTypes.Category;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            URL url = new URL(args[0] + "xml/boards.php");

            String packageName = "ch.sbi.scraper.DataTypes";
            JAXBContext context = JAXBContext.newInstance(packageName);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Source source = new StreamSource(url.openStream());
            // using the unmarshal overload with a specific class is necessary if the class doesn't have / isn't a root
            // element.
            JAXBElement<Categories> forum = unmarshaller.unmarshal(source, Categories.class);
        } catch (IOException e) {
            logger.severe("Url failed to open. Reason: " + e);
        } catch (JAXBException e) {
            logger.info("JAXB Error: " + e);
            e.printStackTrace();
        }
    }

    public static void setupLogging() throws IOException {
        LocalDateTime date = LocalDateTime.now();
        FileHandler logFile = new FileHandler("logs/" + date + ".log");
        logger.addHandler(logFile);
    }
}
