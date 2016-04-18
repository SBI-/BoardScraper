package ch.sbi.scraper;

import ch.sbi.scraper.example.BoardExample;
import ch.sbi.scraper.example.ThreadExample;

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
            ThreadExample.printInfo(args[0]);
            ThreadExample.printPage(args[0]);
            ThreadExample.countPages(args[0]);
        } catch (JAXBException e) {
            logger.info("JAXB Error: " + e);
            e.printStackTrace();
        }
    }
}
