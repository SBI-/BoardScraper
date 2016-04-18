package ch.sbi.scraper.mapper;

import ch.sbi.scraper.datatype.marshalling.Board;
import ch.sbi.scraper.datatype.marshalling.Categories;
import ch.sbi.scraper.datatype.marshalling.Category;
import ch.sbi.scraper.factory.MarshallerFactory;
import ch.sbi.scraper.library.utility.SourceBuilder;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Class that maps a category xml to its runtime type.
 *
 * Also allows unmarshalling of boards directly through it's parent category.
 */
public final class CategoryMapper {

    private final SourceBuilder sourceBuilder;
    private JAXBElement<Categories> categories;

    public CategoryMapper(SourceBuilder sourceBuilder) {
        this.sourceBuilder = sourceBuilder;
    }

    /**
     * Returns all the categories in a forum.
     *
     * Utilizes lazy loading, categories are fetched and unmarshalled on the first call to this function.
     *
     * @return List of all categories
     * @throws JAXBException
     * @throws IOException
     */
    public List<Category> getCategories() throws JAXBException, IOException {
        if (categories != null) {
            return categories.getValue().getCategory();
        }

        Unmarshaller unmarshaller = new MarshallerFactory(Categories.class).getUnmarshaller();
        categories = unmarshaller.unmarshal(sourceBuilder.getCategoriesSource(), Categories.class);

        return categories.getValue().getCategory();
    }

    /**
     * Returns a board that has a corresponding category.
     *
     * This function will only return a valid board if it is assigned to a category. Will likely be deprecated in the
     * future, as this functionality will be part of a different mapper class.
     *
     * @param id Id of the board to return
     * @return Will contain a board if it exists inside a category
     * @throws JAXBException If xml is malformed or of the wrong type
     * @throws IOException If xml url cannot be reached
     */
    public Optional<Board> getBoard(int id) throws JAXBException, IOException {
        return getCategories()
                .stream()
                .filter(c -> c.getBoards() != null)
                .flatMap(c -> c.getBoards().getBoard().stream())
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
