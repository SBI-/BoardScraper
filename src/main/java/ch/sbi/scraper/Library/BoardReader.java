package ch.sbi.scraper.Library;

import ch.sbi.scraper.Library.DataTypes.Board;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class BoardReader extends Reader {

    public BoardReader(String url) throws IOException {
        super(url);
    }

    public List<Board> getBoards() {
        // class nu seems to be what contains a board title
        Elements nu = document.getElementsByClass("nu");
        // nu, however, is used for multiple types of links, so we filter out the ones we don't need
        nu.removeClass("wht");

        List<Board> boards = new ArrayList<Board>();
        for (Element e : nu) {
            // we only want boards, but not threads.
            if (e.attr("href").contains("board")) {
                Board board = new Board(e.text(), e.attr("abs:href"));
                boards.add(board);
            }
        }

        return boards;
    }
}
