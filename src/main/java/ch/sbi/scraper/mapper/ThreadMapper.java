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
 * Class for fetching and mapping Thread XML files to their runtime representation.
 */
public class ThreadMapper {
    private final SourceBuilder sourceBuilder;

    public ThreadMapper(SourceBuilder sourceBuilder) {
        this.sourceBuilder = sourceBuilder;
    }

    /**
     * Unmarshalls the first page of the thread with the given id.
     *
     * @param id Thread id
     * @return Unmarshalled thread, or a dummy thread object containing no information.
     */
    public Thread getThread(long id) {
        return getThread(id, 1);
    }

    /**
     * Unmarshalls the given page of the thread with the given id.
     *
     * @param id Thread id
     * @param page Thread page
     * @return Unmarshalled page, or a dummy thread object containing no information.
     */
    public Thread getThread(long id, int page) {
        try {
            Source threadSource = sourceBuilder.getThreadSource(id, page);
            Unmarshaller unmarshaller = new MarshallerFactory(Thread.class).getUnmarshaller();
            return unmarshaller.unmarshal(threadSource, Thread.class).getValue();
        } catch (JAXBException e) {
            // object creation failed, return a dummy object
            // TODO: This should also use Optional
            return new Thread();
        }
    }

    /**
     * Returns a Stream of thread objects, each representing a page of the given Thread.
     *
     * @param id Thread for which to stream all pages
     * @return Stream with all pages of the given thread
     */
    public Stream<Thread> getPages(long id) {
        Thread thread = getThread(id);
        return IntStream
                .rangeClosed(1, thread.getNumberOfPages().getValue().intValue())
                .mapToObj(i -> getThread(id, i))
                .filter(t -> Integer.valueOf(t.getPosts().getCount()) > 0);
    }
}
