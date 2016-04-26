package ch.sbi.scraper.test.mapper;

import ch.sbi.scraper.datatype.marshalling.Board;
import ch.sbi.scraper.mapper.BoardMapper;
import ch.sbi.scraper.test.helper.TestSourceBuilder;
import org.junit.Test;

import javax.xml.bind.JAXBException;

import static org.junit.Assert.*;

public class BoardMapperTest {
    private final BoardMapper mapper;

    public BoardMapperTest() {
        TestSourceBuilder sourceBuilder = new TestSourceBuilder();
        this.mapper = new BoardMapper(sourceBuilder);
    }

    @Test
    public void getPage1() throws JAXBException {
        Board board = mapper.getBoard(1);
        assertTrue(board.getId().intValue() == 14);
        assertTrue(board.getName().equalsIgnoreCase("public offtopic"));
        assertTrue(board.getThreads().getPage().intValue() == 1);
    }

    @Test
    public void getPage3() throws Exception {
        Board board = mapper.getBoard(1, 3);
        assertTrue(board.getId().intValue() == 14);
        assertTrue(board.getName().equalsIgnoreCase("public offtopic"));
        assertTrue(board.getThreads().getPage().intValue() == 3);
    }

}
