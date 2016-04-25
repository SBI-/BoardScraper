package ch.sbi.scraper.test.mapper;

import ch.sbi.scraper.datatype.marshalling.Board;
import ch.sbi.scraper.mapper.BoardMapper;
import ch.sbi.scraper.test.helper.TestSourceBuilder;
import org.junit.Test;

import javax.xml.bind.JAXBException;

public class BoardMapperTest {
    private final BoardMapper mapper;

    public BoardMapperTest() {
        TestSourceBuilder sourceBuilder = new TestSourceBuilder();
        this.mapper = new BoardMapper(sourceBuilder);
    }

    @Test
    public void GetPage1() throws JAXBException {
        Board board = mapper.getBoard(1);
        assert board.getId().intValue() == 14;
        assert board.getName().equalsIgnoreCase("public offtopic");
        assert board.getThreads().getPage().intValue() == 1;
    }

    @Test
    public void GetPage3() throws Exception {
        Board board = mapper.getBoard(1, 3);
        assert board.getId().intValue() == 14;
        assert board.getName().equalsIgnoreCase("public offtopic");
        assert board.getThreads().getPage().intValue() == 3;
    }

}
