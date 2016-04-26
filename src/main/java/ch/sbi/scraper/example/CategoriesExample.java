package ch.sbi.scraper.example;

import ch.sbi.scraper.mapper.CategoryMapper;
import ch.sbi.scraper.datatype.marshalling.Board;
import ch.sbi.scraper.datatype.marshalling.Category;
import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.library.utility.UrlSourceBuilder;

import javax.xml.bind.JAXBException;

public class CategoriesExample {
    public static void printCategories(String baseUrl) throws JAXBException {
        SourceBuilder sourceBuilder = new UrlSourceBuilder(baseUrl);
        CategoryMapper categoryMapper = new CategoryMapper(sourceBuilder);

        int sum = 0;

        for (Category category : categoryMapper.getCategories()) {
            System.out.println(category.getName());
            // this check is for private boards
            if (category.getBoards() != null) {
                for (Board board : category.getBoards().getBoard()) {
                    String output = String.format("\t %s, Id: %s, Threadcount: %d",
                            board.getName(),board.getId(), board.getNumberOfThreads().getValue());
                    sum += board.getNumberOfThreads().getValue().intValue();
                    System.out.println(output);
                }
            }
        }

        System.out.println(sum);
    }
}
