package selenium;

import java.time.LocalDate;

public interface SearchPage extends Page {

    /**
     * Download bing images for a give date
     *
     * @param localDate
     */
    void download(LocalDate localDate);
}
