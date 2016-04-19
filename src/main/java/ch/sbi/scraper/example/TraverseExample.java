package ch.sbi.scraper.example;

import ch.sbi.scraper.datatype.marshalling.*;
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

//        boardMapper
//                .getPages(14)
//                .limit(3)
//                .flatMap(p -> p.getThreads().getThread().stream())
//                .forEach(t -> printThread(t));
//
        boardMapper
                .getPages(14)
                .limit(1)
                /*
                This works, but will take forever on a large board
                .parallel()
                */
                .flatMap(p -> p.getThreads().getThread().stream())
                .map(t -> threadMapper.getThread(t.getId().intValue()))
                .flatMap(t -> t.getPosts().getPost().stream())
                .forEach(TraverseExample::printPost);

        boardMapper
                .getPages(122)
                .parallel()
                .flatMap(p -> p.getThreads().getThread().stream())
                .map(t -> threadMapper.getThread(t.getId().intValue()))
                .forEach(TraverseExample::printThread);
    }

    private static void printThread(ch.sbi.scraper.datatype.marshalling.Thread t) {
        System.out.println(t.getTitle() + " marshalled: " + (t.getPosts() != null));
    }

    private static void printPost(Post p) {
        System.out.printf("%s %s: %s\n", p.getInThread().getId(), p.getUser().getValue(), p.getMessage().getContent());
    }
}
