package ch.sbi.scraper.mapper;

import ch.sbi.scraper.datatype.marshalling.Board;
import ch.sbi.scraper.factory.MarshallerFactory;
import ch.sbi.scraper.library.utility.SourceBuilder;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public Board getBoard(int id, int page) throws JAXBException {
        Source boardSource = sourceBuilder.getBoardSource(id, page);
        Unmarshaller unmarshaller = new MarshallerFactory(Board.class).getUnmarshaller();
        return unmarshaller.unmarshal(boardSource, Board.class).getValue();
    }

    /**
     * Forms a stream for iterating over the pages of a board.
     *
     * The maximum page count is only an estimation, as the thread count includes threads that aren't visible anymore,
     * so the maximum count calculated is definately an upper bound, but possibly not the absolutely correct value.
     *
     * @param id Id of the board over which to iterate.
     * @return A stream of board objects.
     * @throws JAXBException If xml is malformed or of the wrong type.
     */
    public Stream<Board> getPages(int id) throws JAXBException {
        return IntStream
                .range(1, estimateBound(id) + 1)
                .mapToObj(i -> sourceBuilder.getBoardSource(id, i))
                .map(s -> unmarshall(s))
                .filter(b -> b.getThreads().getCount().intValue() > 0);
    }

    private int estimateBound(int id) throws JAXBException {
        Board board = getBoard(id);
        int step = board.getThreads().getCount().intValue();
        int count = board.getNumberOfThreads().getValue().intValue();

        return (count / step) + 1;
    }

    private Board unmarshall(Source source) {
        try {
            Unmarshaller unmarshaller = new MarshallerFactory(Board.class).getUnmarshaller();
            return unmarshaller
                    .unmarshal(source, Board.class)
                    .getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
