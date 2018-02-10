import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.stream.Stream;

public  class GetFileList extends SimpleFileVisitor<Path>{

        public static void listFiles() throws IOException
        {
            Path source = Paths.get("C://VehicleRegCheck/");
            Stream<Path> result = Files.walk(source).filter(Files::isRegularFile);
            Iterable<Path> iterable = result::iterator;
            for (Path p : iterable) {
                BasicFileAttributes attr = Files.readAttributes(p, BasicFileAttributes.class);
                System.out.println("(Filename :  "+p.getFileName()+"   ===========   "+"MimeType : "+Files.probeContentType(p) +"   ============    FileExtension :  "+ getFileExtension(p) +"   ===============   " +attr.size() + "  bytes)" );
            }
        }

    private static String getFileExtension(Path file) {
        String fileName = file.getFileName().toString();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    //public static void main(String[] args) throws IOException {

//             listFiles();
  //  }



    }



