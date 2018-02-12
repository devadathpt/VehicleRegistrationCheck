package vehicleRegCheck.utilities;

import com.monitorjbl.xlsx.StreamingReader;
        import org.apache.poi.ss.usermodel.Cell;
        import org.apache.poi.ss.usermodel.Row;
        import org.apache.poi.ss.usermodel.Sheet;
        import org.apache.poi.ss.usermodel.Workbook;
        import vehicleRegCheck.GetFileList;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.nio.file.Path;
        import java.util.*;

public class VehicleDataFromExcel {
    static Properties  con = null;



    public static Map<String, List<String>> getData() throws IOException
    {
        con = new Properties();
        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//vehicleRegCheck//config//config.properties");
        con.load(fs);
        Path dataFile = GetFileList.getDataFileUri(con.getProperty("Directory"));
        InputStream is = null;
        Map<String, List<String>> vehicleData = new HashMap<String, List<String>>();

        is = (is == null) ? new FileInputStream(new File(dataFile.toUri())) : is;

        Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);
        Sheet sheet = workbook.sheetIterator().next();

        Iterator rows = sheet.rowIterator();
        rows.next();
        while (rows.hasNext()) {
            Row row = (Row)rows.next();
            List<String> vehicleAttributes = new ArrayList<String>();
            Iterator cells = row.cellIterator();


            String regNumber = ((Cell) cells.next()).getStringCellValue();

            while (cells.hasNext()) {
                Cell cell = (Cell)cells.next();
                vehicleAttributes.add(cell.getStringCellValue());
            }

            vehicleData.put(regNumber, vehicleAttributes);
        }
        return vehicleData;

    }

}



