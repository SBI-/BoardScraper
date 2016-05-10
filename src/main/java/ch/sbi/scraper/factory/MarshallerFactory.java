package ch.sbi.scraper.factory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class MarshallerFactory {
    private final JAXBContext context;

    public MarshallerFactory(Class c) throws JAXBException {
        this(c.getPackage().getName());
    }

    public MarshallerFactory(String packageName) throws JAXBException {
        context = JAXBContext.newInstance(packageName);
    }

    public Unmarshaller getUnmarshaller() throws JAXBException {
        return context.createUnmarshaller();
    }
}
