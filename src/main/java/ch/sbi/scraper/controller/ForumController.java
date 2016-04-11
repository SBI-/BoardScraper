package ch.sbi.scraper.controller;

import ch.sbi.scraper.datatypes.marshalling.Categories;
import ch.sbi.scraper.datatypes.marshalling.Category;
import ch.sbi.scraper.factory.MarshallerFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by sbi on 10.04.16.
 */
public class ForumController {

    private JAXBElement<Categories> forum;

    public static ForumController initialize(URL url) throws JAXBException, IOException {
        Unmarshaller unmarshaller = new MarshallerFactory(Categories.class).getUnmarshaller();
        // using the unmarshal overload with a specific class is necessary if the class doesn't have / isn't a root
        // element.
        Source source = new StreamSource(url.openStream());
        JAXBElement<Categories> forum = unmarshaller.unmarshal(source, Categories.class);
        return new ForumController(forum);
    }

    private ForumController(JAXBElement<Categories> forum) {
        this.forum = forum;
    }

    public List<Category> getCategories() {
        return forum.getValue().getCategory();
    }
}
