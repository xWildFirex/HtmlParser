import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableParser {
    private static Integer START_ROW = 17;
    private static Integer TELEPHONE_NUMBER_FIELD = 0;
    private static Integer CONVERSATION_DATE_FIELD = 1;
    private static Integer CALL_NUMBER_FIELD = 2;
    private static Integer CALL_TOWN_FIELD = 3;
    private static Integer CALL_DURATION_FIELD = 4;

    public static List<DataItem> parseFile(String fileName) throws IOException {

        List<DataItem> dataItemList = new ArrayList<DataItem>();
        Document document = Jsoup.parse(new File(fileName), "cp1251");
        Elements elements = document.select("table");
        Elements tableRowElements = elements.select(":not(thead) tr");

        for (int i = START_ROW; i < tableRowElements.size(); i++) {
            Element row = tableRowElements.get(i);
            Elements rowItems = row.select("td:lt(5)");
            String telephoneNumber = rowItems.get(TELEPHONE_NUMBER_FIELD).text();
            if(!telephoneNumber.isEmpty()){
                String conversationDate  = rowItems.get(CONVERSATION_DATE_FIELD).text();
                String callNumber = rowItems.get(CALL_NUMBER_FIELD).text();
                String callTown = rowItems.get(CALL_TOWN_FIELD).text();
                String callDuration = rowItems.get(CALL_DURATION_FIELD).text();
                DataItem dataItem = new DataItem(telephoneNumber, conversationDate, callNumber, callTown, callDuration);
                dataItemList.add(dataItem);
            }
        }
        return dataItemList;
    }
}

