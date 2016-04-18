package ch.sbi.scraper.mapper;

import ch.sbi.scraper.datatype.marshalling.*;
import ch.sbi.scraper.datatype.marshalling.Thread;
import ch.sbi.scraper.factory.MarshallerFactory;
import ch.sbi.scraper.library.utility.SourceBuilder;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;

/**
 * Created by sbi on 18.04.16.
 */
public class ThreadMapper {
    private final SourceBuilder sourceBuilder;

    public ThreadMapper(SourceBuilder sourceBuilder) {
        this.sourceBuilder = sourceBuilder;
    }

    public Thread getThread(int id) throws JAXBException {
        return getThread(id, 1);
    }

    public Thread getThread(int id, int page) throws JAXBException {
        Source threadSource = sourceBuilder.getThreadSource(id, page);
        Unmarshaller unmarshaller = new MarshallerFactory(Thread.class).getUnmarshaller();
        return unmarshaller.unmarshal(threadSource, Thread.class).getValue();
    }
}
