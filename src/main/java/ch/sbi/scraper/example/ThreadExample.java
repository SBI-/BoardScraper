package ch.sbi.scraper.example;

import ch.sbi.scraper.datatype.marshalling.Post;
import ch.sbi.scraper.datatype.marshalling.Thread;
import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.library.utility.UrlSourceBuilder;
import ch.sbi.scraper.mapper.ThreadMapper;

import javax.xml.bind.JAXBException;

public class ThreadExample {
    public static void printInfo(String baseUrl) throws JAXBException {
        SourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        ThreadMapper threadMapper = new ThreadMapper(sourceBuilder);
        Thread thread = threadMapper.getThread(214387);
        print(thread);

    }

    public static void printPage(String baseUrl) throws JAXBException {
        UrlSourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        ThreadMapper threadMapper = new ThreadMapper(sourceBuilder);
        Thread thread = threadMapper.getThread(214387, 96);
        print(thread);
    }

    public static void countPages(String baseUrl) throws JAXBException {
        UrlSourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        ThreadMapper threadMapper = new ThreadMapper(sourceBuilder);

        long count = threadMapper
                .getPages(214387)
                .parallel()
                .count();

        System.out.println("Page count: " + count);
    }

    public static void printThread(String baseUrl) throws JAXBException {
        UrlSourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        ThreadMapper threadMapper = new ThreadMapper(sourceBuilder);

        threadMapper
                .getPages(214387)
                .flatMap(t -> t.getPosts().getPost().stream())
                .forEach(ThreadExample::printPost);
    }

    private static void printPost(Post post) {
        System.out.printf("Author: %s%n Content: %s%n --- %n",
                post.getUser().getValue(),
                post.getMessage().getContent());
    }

    private static void print(Thread thread) {
        System.out.println("Thread Id: " + thread.getId());
        System.out.println("In Board: " + thread.getInBoard().getId());
        System.out.println("Page: " + thread.getPosts().getPage());
        System.out.println("Post on page: " + thread.getPosts().getCount());
        System.out.println("Closed: " + thread.getFlags().getIsClosed().isValue());
    }
}
