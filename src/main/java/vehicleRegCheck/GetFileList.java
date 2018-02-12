package vehicleRegCheck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.Iterator;
import java.util.stream.Stream;

public class GetFileList extends SimpleFileVisitor<Path>{

    private static Stream<Path> listFiles(final String uri) throws IOException
    {
        Path source = Paths.get(uri);

        Stream<Path> list = Files.list(source).filter(Files::isRegularFile);
        return list;
    }


    public static Path getDataFileUri(String uri) throws IOException {
        //String uri = "src/test/resources";
        //String uri = "C://VehicleRegCheck//";

        Iterator<Path> iterator = listFiles(uri).iterator();

        while(iterator.hasNext())
        {
            Path fileName = iterator.next();
            if(fileName.toString().contains(".xlsx"))
                return fileName;
        }
        throw new RuntimeException("No XLSX file found in the path");
    }

}



