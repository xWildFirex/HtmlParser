import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TableParser {

    public static List<DataItem> parseFile(String fileName) throws IOException {

        List<DataItem> dataItemList = new ArrayList<DataItem>();
        Document document = Jsoup.parse(new File(fileName), "cp1251");
        Elements elements = document.select("table");
        Elements tableRowElements = elements.select(":not(thead) tr");

        for (int i = 17; i < tableRowElements.size(); i++) {
            Element row = tableRowElements.get(i);
            Elements rowItems = row.select("td:lt(5)");
            if(!(rowItems.get(0).text().isEmpty())){

                String telephoneNumber = rowItems.get(0).text();
                String conversationDate  = rowItems.get(1).text();
                String callNumber = rowItems.get(2).text();
                String callTown = rowItems.get(3).text();
                String callDuration = rowItems.get(4).text();
                DataItem dataItem = new DataItem(telephoneNumber, conversationDate, callNumber, callTown, callDuration);
                dataItemList.add(dataItem);
            }
        }
        return dataItemList;
    }
}

