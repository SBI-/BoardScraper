package ch.sbi.scraper.example;

import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.library.utility.UrlSourceBuilder;
import ch.sbi.scraper.mapper.BoardMapper;
import ch.sbi.scraper.mapper.ThreadMapper;

import javax.xml.bind.JAXBException;

/**
 * Created by sbi on 18.04.16.
 */
public class TraverseExample {
    public static void traversePages(String baseUrl) throws JAXBException {
        SourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        BoardMapper boardMapper = new BoardMapper(sourceBuilder);
        ThreadMapper threadMapper = new ThreadMapper(sourceBuilder);

        boardMapper
                .getPages(14)
                .limit(3)
                .flatMap(p -> p.getThreads().getThread().stream())
                .forEach(t -> System.out.println(t.getTitle() + " marshalled: " + (t.getPosts() != null)));

        boardMapper
                .getPages(14)
                .limit(3)
                .flatMap(p -> p.getThreads().getThread().stream())
                .map(t -> {
                    try {
                        return threadMapper.getThread(t.getId().intValue());
                    } catch (JAXBException e) {
                        return null;
                    }
                })
                .forEach(t -> System.out.printf("%s marshalled: %b\n", t.getTitle(), t.getPosts() != null));
    }
}
