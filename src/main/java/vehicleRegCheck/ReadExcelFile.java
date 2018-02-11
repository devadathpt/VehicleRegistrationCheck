package vehicleRegCheck;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class ReadExcelFile {

    public static void main(String[] args) throws IOException {
        LinkedHashMap<Integer, List> vechicleData = new LinkedHashMap<Integer, List>();
        InputStream is = new FileInputStream(new File("classpath://VechicleRegList.xlsx"));
        Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);

        for (Sheet sheet : workbook) {
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                Row row = (Row)rows.next();
                Iterator cells = row.cellIterator();

                List rowData = new LinkedList();
                while (cells.hasNext()) {
                    Cell cell = (Cell)cells.next();
                    rowData.add(cell.getStringCellValue());
                }
                vechicleData.put(row.getRowNum(), rowData);

            }

        }
            System.out.println(vechicleData);
    }
}





