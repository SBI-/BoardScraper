package ch.sbi.scraper.controller;

import ch.sbi.scraper.datatypes.marshalling.Categories;
import ch.sbi.scraper.datatypes.marshalling.Category;
import ch.sbi.scraper.factory.MarshallerFactory;
import ch.sbi.scraper.library.utility.SourceBuilder;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.List;

/**
 * Created by sbi on 10.04.16.
 */
public final class ForumController {

    private JAXBElement<Categories> forum;

    public static ForumController initialize(SourceBuilder sourceBuilder) throws JAXBException, IOException {
        Unmarshaller unmarshaller = new MarshallerFactory(Categories.class).getUnmarshaller();
        // TODO: Find out if there is a way to use IoC here and hide the marshaller from the controllers in order to
        // TODO: make sure that a source is only used once.
        // using the unmarshal overload with a specific class is necessary if the class doesn't have / isn't a root
        // element.
        JAXBElement<Categories> forum = unmarshaller.unmarshal(sourceBuilder.getBoardSource(), Categories.class);
        return new ForumController(forum);
    }

    private ForumController(JAXBElement<Categories> forum) {
        this.forum = forum;
    }

    public List<Category> getCategories() {
        return forum.getValue().getCategory();
    }
}
