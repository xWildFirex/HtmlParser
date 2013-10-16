import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class WriteToFile {

    public HSSFWorkbook workbook;
    public HSSFSheet workSheet;
    public HSSFRow row;
    public HSSFCell cell;
    public Integer rowNumber = 0;

    WriteToFile(List<DataItem> data) {
        this.workbook = new HSSFWorkbook();
        this.workSheet = workbook.createSheet("worksheet");
        for (DataItem dataItem : data){
            createRow();
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

    public void createRow(){
        row = workSheet.createRow(rowNumber);
        rowNumber++;
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

    public void write(File fileName){
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            workbook.write(file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
