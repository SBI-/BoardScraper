package ch.sbi.scraper.mapper;

import ch.sbi.scraper.datatype.marshalling.Board;
import ch.sbi.scraper.factory.MarshallerFactory;
import ch.sbi.scraper.library.utility.SourceBuilder;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;

/**
 * Class that maps a category xml to its runtime type.
 */
public class BoardMapper {
    private final SourceBuilder sourceBuilder;

    public BoardMapper(SourceBuilder sourceBuilder) {
        this.sourceBuilder = sourceBuilder;
    }

    /**
     * Returns the Board corresponding to the passed id.
     *
     * If a board with the given id doesn't exist, an empty object is returned, because the server returns a valid
     * xml file containing "<invalid-board/>". Client code currently has to check for this. TODO: Handle this in lib.
     *
     * @param id Board id
     * @return Unmarshalled board with the corresponding id
     * @throws JAXBException If xml is invalid
     */
    public Board getBoard(int id) throws JAXBException {
        Source boardSource = sourceBuilder.getBoardSource(id);
        Unmarshaller unmarshaller = new MarshallerFactory(Board.class).getUnmarshaller();
        return unmarshaller.unmarshal(boardSource, Board.class).getValue();
    }
}
