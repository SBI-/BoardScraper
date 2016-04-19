package ch.sbi.scraper.example;

import ch.sbi.scraper.datatype.marshalling.*;
import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.library.utility.UrlSourceBuilder;
import ch.sbi.scraper.mapper.BoardMapper;
import ch.sbi.scraper.mapper.ThreadMapper;

import javax.xml.bind.JAXBException;
import java.util.stream.Stream;

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
                .limit(1)
                .flatMap(p -> p.getThreads().getThread().stream())
                .map(t -> threadMapper.getThread(t.getId().intValue()))
                .flatMap(t -> t.getPosts().getPost().stream())
                .forEach(p -> System.out.printf("%s %s: %s\n", p.getInThread().getId(), p.getUser().getValue(), p.getMessage().getContent()));
    }
}
