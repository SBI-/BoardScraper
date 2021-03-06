package ch.sbi.scraper.example;

import ch.sbi.scraper.mapper.BoardMapper;
import ch.sbi.scraper.mapper.CategoryMapper;
import ch.sbi.scraper.datatype.marshalling.Board;
import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.library.utility.UrlSourceBuilder;

import javax.xml.bind.JAXBException;
import java.util.Optional;

public class BoardExample {
    public static void printBoardCategory(String baseUrl) throws JAXBException {
        SourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        CategoryMapper categoryMapper = new CategoryMapper(sourceBuilder);

        Optional<Board> maybe = categoryMapper.getBoard(14);
        maybe.ifPresent(BoardExample::printInformation);
        Optional<Board> maybeNot = categoryMapper.getBoard(3457);
        System.out.println(maybeNot.isPresent());
    }

    public static void printBoard(String baseUrl) throws JAXBException {
        SourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        BoardMapper boardMapper = new BoardMapper(sourceBuilder);

        Board board = boardMapper.getBoard(14);
        printInformation(board);
        Board noBoard = boardMapper.getBoard(1337);
        if (noBoard.getId() != null) {
            printInformation(noBoard);
        }
    }

    public static void printBoardPage(String baseUrl) throws JAXBException {
        SourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        BoardMapper boardMapper = new BoardMapper(sourceBuilder);

        Board board = boardMapper.getBoard(14, 4);
        printInformation(board);
    }

    public static void pageStream(String baseUrl) throws JAXBException {
        SourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        BoardMapper boardMapper = new BoardMapper(sourceBuilder);

        long count = boardMapper
                .getPages(16)
                .parallel()
                .count();

        System.out.println(count);
    }

    private static void printInformation(Board board) {
        String boardInfo = String.format("%s", board.getName());
        String threadInfo = String.format("Page %d / %d",
                board.getThreads().getPage(),
                board.getNumberOfThreads().getValue());

        System.out.println("Board Information: ");
        System.out.println(boardInfo);
        System.out.println("Thread Information: ");
        System.out.println(threadInfo);
    }
}
