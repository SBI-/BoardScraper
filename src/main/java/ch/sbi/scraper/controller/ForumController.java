package ch.sbi.scraper.controller;

import ch.sbi.scraper.datatypes.marshalling.Board;
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

    private final SourceBuilder sourceBuilder;
    private JAXBElement<Categories> categories;

    public ForumController(SourceBuilder sourceBuilder) throws JAXBException, IOException {
        this.sourceBuilder = sourceBuilder;
        Unmarshaller unmarshaller = new MarshallerFactory(Categories.class).getUnmarshaller();
        categories = unmarshaller.unmarshal(sourceBuilder.getCategoriesSource(), Categories.class);
    }

    public List<Category> getCategories() {
        return categories.getValue().getCategory();
    }

    public Board getBoard(int id) throws JAXBException {
        Unmarshaller unmarshaller = new MarshallerFactory(Board.class).getUnmarshaller();

        // TODO; Instead of getting the board this way, I could build the stream up to flatmap in the constructor and
        // TODO: check if that id exists in that way. This also paves the way for creating a board controller that then
        // TODO: just doens't make that check before trying to unmarshall a source. Or there might be a nicer stream.
        boolean notFound = getCategories()
                .stream()
                .filter(a -> a.getBoards() != null)
                .flatMap(a -> a.getBoards().getBoard().stream())
                .noneMatch(b -> b.getId().intValue() == id);

        if (notFound) {
            throw new IllegalArgumentException("Invalid item id.");
        } else {
            return unmarshaller
                    .unmarshal(sourceBuilder.getBoardSource(id), Board.class)
                    .getValue();
        }
    }
}
