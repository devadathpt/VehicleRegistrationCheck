package vehicleRegCheck.utilities;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import vehicleRegCheck.model.Vehicle;

import java.io.*;
import java.util.*;

public class getVehicleDataFromExcel {
   static Properties CONFIG = null;



    public static LinkedHashMap<Integer, List<String>> getData() throws IOException
    {
        CONFIG = new Properties();
        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//vehicleRegCheck//config//config.properties");
        CONFIG.load(fs);
        String path1 = CONFIG.getProperty("Directory")+CONFIG.getProperty("fileName");

        LinkedHashMap<Integer, List<String>> vehicleData = new LinkedHashMap<Integer, List<String>>();
        InputStream is = new FileInputStream(new File(path1));
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
                vehicleData.put(row.getRowNum(), rowData);


            }

        }
        return vehicleData;



    }

    }



