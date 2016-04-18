package ch.sbi.scraper.example;

import ch.sbi.scraper.datatype.marshalling.Thread;
import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.library.utility.UrlSourceBuilder;
import ch.sbi.scraper.mapper.ThreadMapper;

import javax.xml.bind.JAXBException;

/**
 * Created by sbi on 18.04.16.
 */
public class ThreadExample {
    public static void printInfo(String baseUrl) throws JAXBException {
        SourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        ThreadMapper threadMapper = new ThreadMapper(sourceBuilder);
        Thread thread = threadMapper.getThread(214387);
        print(thread);

    }

    private static void print(Thread thread) {
        System.out.println("Thread Id: " + thread.getId());
        System.out.println("In Board: " + thread.getInBoard().getId());
        System.out.println("Closed: " + thread.getFlags().getIsClosed().isValue());
    }
}
