package ch.sbi.scraper.mapper;

import ch.sbi.scraper.datatype.marshalling.*;
import ch.sbi.scraper.library.utility.SourceBuilder;

/**
 * Created by sbi on 18.04.16.
 */
public class ThreadMapper {
    private final SourceBuilder sourceBuilder;

    public ThreadMapper(SourceBuilder sourceBuilder) {
        this.sourceBuilder = sourceBuilder;
    }

    public ch.sbi.scraper.datatype.marshalling.Thread getThread(int id) {
        sourceBuilder.getThreadSource(214387);
        return null;
    }
}
