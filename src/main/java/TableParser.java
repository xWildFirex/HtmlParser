import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: vt
 * Date: 23.09.13
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class TableParser {

    static String OutFileName;
    static String InFileName;

    public void tableParser(String fileName) throws IOException {

        List<DataItem> dataItemList = new ArrayList<DataItem>();
        InFileName = fileName;
        OutFileName = InFileName + ".xls";
        Document document = Jsoup.parse(new File(InFileName), "cp1251");
        Elements elements = document.select("table");

        Elements tableRowElements = elements.select(":not(thead) tr");
        WriteToFile writeToFile = new WriteToFile(OutFileName);

        for (int i = 17; i < tableRowElements.size(); i++) {
            Element row = tableRowElements.get(i);
            Elements rowItems = row.select("td:lt(5)");
            if(!(rowItems.get(0).text().isEmpty())){
                writeToFile.createRow();
                String telephoneNumber = rowItems.get(0).text();
                String conversationDate  = rowItems.get(1).text();
                String callNumber = rowItems.get(2).text();
                String callTown = rowItems.get(3).text();
                String callDuration = rowItems.get(4).text();
                DataItem dataItem = new DataItem(telephoneNumber, conversationDate, callNumber, callTown, callDuration);
                dataItemList.add(dataItem);
            }


            for (int j = 0; j < rowItems.size(); j++) {
                String r = rowItems.get(j).text();
                if(!r.isEmpty()){
                    writeToFile.setCell(r, j);
                }
            }
        }
        writeToFile.writeData();
    }
}

