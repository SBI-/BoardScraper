package ch.sbi.scraper.mapper;

import ch.sbi.scraper.datatype.marshalling.Thread;
import ch.sbi.scraper.factory.MarshallerFactory;
import ch.sbi.scraper.library.utility.SourceBuilder;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by sbi on 18.04.16.
 */
public class ThreadMapper {
    private final SourceBuilder sourceBuilder;

    public ThreadMapper(SourceBuilder sourceBuilder) {
        this.sourceBuilder = sourceBuilder;
    }

    public Thread getThread(long id) {
        return getThread(id, 1);
    }

    public Thread getThread(long id, int page) {
        try {
            Source threadSource = sourceBuilder.getThreadSource(id, page);
            Unmarshaller unmarshaller = new MarshallerFactory(Thread.class).getUnmarshaller();
            return unmarshaller.unmarshal(threadSource, Thread.class).getValue();
        } catch (JAXBException e) {
            // object creation failed, return a dummy object
            // TODO: This should likely be what all mappers return.
            return new Thread();
        }
    }

    public Stream<Thread> getPages(long id) {
        return IntStream
                .range(1, estimateBound(id))
                .mapToObj(i -> sourceBuilder.getThreadSource(id, i))
                .map(this::unmarshall)
                .filter(t -> Integer.valueOf(t.getPosts().getCount()) > 0);
    }

    private int estimateBound(long id) {
        Thread thread = getThread(id);
        int step = Integer.valueOf(thread.getPosts().getCount());
        int count = thread.getNumberOfReplies().getValue().intValue();

        // count / step will round down, so we need to add 1. As pages are 1 and not 0 indexed, we need to add another 1.
        return (count / step) + 2;
    }

    private Thread unmarshall(Source source) {
        try {
            Unmarshaller unmarshaller = new MarshallerFactory(Thread.class).getUnmarshaller();
            return unmarshaller
                    .unmarshal(source, Thread.class)
                    .getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
            // TODO: Look into making this return a dummy object as well.
            return null;
        }
    }
}
