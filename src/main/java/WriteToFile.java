import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: korbut-ve
 * Date: 26.09.13
 * Time: 11:29
 * To change this template use File | Settings | File Templates.
 */
public class WriteToFile {
    public static String FileName;
    public static FileOutputStream file;
    public static HSSFWorkbook workbook;
    public static HSSFSheet workSheet;
    public HSSFRow row;
    public HSSFCell cell;
    public Integer rowNumber = 0;

    public WriteToFile(String fileName) throws FileNotFoundException {
        this.FileName = fileName;
        this.file = new FileOutputStream(FileName);
        this.workbook = new HSSFWorkbook();
        this.workSheet = workbook.createSheet("worksheet");
    }

    public void createRow(){
        row = workSheet.createRow(rowNumber);
        rowNumber++;
    }

    public void setCell (String cellValue, int cellNumber) {
        cell = row.createCell(cellNumber);
        cell.setCellValue(cellValue);
    }

    public void addCell(String dataCell) {

    }

    public void setData (List<DataItem> dataItemList) {
        for (DataItem dataItem : dataItemList){
            createRow();
            addCell(dataItem.getTelephoneNumber());
            addCell(dataItem.getConversationDate());

            cell = row.createCell(1);
            cell.setCellValue(dataItem.getTelephoneNumber());
            cell = row.createCell(2);
            cell.setCellValue(dataItem.getConversationDate());
            cell = row.createCell(3);
            cell.setCellValue(dataItem.getCallNumber());
            cell = row.createCell(4);
            cell.setCellValue(dataItem.getCallTown());
            cell = row.createCell(5);
            cell.setCellValue(dataItem.getCallDuration());
        }
    }


    public void writeData(){
        try {
            workbook.write(file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
