package ch.sbi.scraper.example;

import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.library.utility.UrlSourceBuilder;
import ch.sbi.scraper.mapper.ThreadMapper;

/**
 * Created by sbi on 18.04.16.
 */
public class ThreadExample {
    public static void printInfo(String baseUrl) {
        SourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        ThreadMapper threadMapper = new ThreadMapper(sourceBuilder);
    }
}
