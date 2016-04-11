package ch.sbi.scraper.controller;

import ch.sbi.scraper.datatypes.marshalling.Categories;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.net.URL;

/**
 * Created by sbi on 10.04.16.
 */
public class ForumController {

    private JAXBElement<Categories> forum;

    public static ForumController initialize(URL url) throws JAXBException, IOException {
        String packageName = Categories.class.getPackage().getName();
        JAXBContext context = JAXBContext.newInstance(packageName);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Source source = new StreamSource(url.openStream());
        // using the unmarshal overload with a specific class is necessary if the class doesn't have / isn't a root
        // element.
        JAXBElement<Categories> forum = unmarshaller.unmarshal(source, Categories.class);
        return new ForumController(forum);
    }

    private ForumController(JAXBElement<Categories> forum) {
        this.forum = forum;
    }

    public JAXBElement<Categories> getForum() {
        return forum;
    }
}
