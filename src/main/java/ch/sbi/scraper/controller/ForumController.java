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
import java.util.Optional;

/**
 * Created by sbi on 10.04.16.
 */
public final class ForumController {

    private final SourceBuilder sourceBuilder;
    private JAXBElement<Categories> categories;

    // TODO: Contemplate using lazy loading instead of unmarshalling in the constructor.
    public ForumController(SourceBuilder sourceBuilder) throws JAXBException, IOException {
        this.sourceBuilder = sourceBuilder;
        Unmarshaller unmarshaller = new MarshallerFactory(Categories.class).getUnmarshaller();
        categories = unmarshaller.unmarshal(sourceBuilder.getCategoriesSource(), Categories.class);
    }

    public List<Category> getCategories() {
        return categories.getValue().getCategory();
    }

    public Optional<Board> getBoard(int id) throws JAXBException {
        return getCategories()
                .stream()
                .filter(a -> a.getBoards() != null)
                .flatMap(a -> a.getBoards().getBoard().stream())
                .filter(b -> b.getId().intValue() == id)
                .map(b -> unmarshal(b.getId().intValue()))
                .findFirst();
    }

    private Board unmarshal(int id) {
        try {
            Unmarshaller unmarshaller = new MarshallerFactory(Board.class).getUnmarshaller();
            return  unmarshaller
                    .unmarshal(sourceBuilder.getBoardSource(id), Board.class)
                    .getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
