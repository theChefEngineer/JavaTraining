import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class XmlFileComparator {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ParseException, InterruptedException {
        File file = new File("C:\\Users\\Abdessamad\\Desktop\\x.txt");

        if(file.delete())
        {
            System.out.println("File deleted successfully");
             System.out.println("File deleted successfully");
             System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }

    }

    private static void extracted() throws IOException, ParserConfigurationException, SAXException, ParseException, InterruptedException {
        while(true) {


            String directoryPath = "C:\\Users\\Abdessamad\\Desktop\\1001";
            String fileName = "TempDateFile.txt";
            String fullFilePath = directoryPath + "/" + fileName;
            //First of all we need to check if the directory exist if not we create it
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            } else {
                System.out.println("the Directory is already created");
            }
            File file = new File(fullFilePath);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                System.out.println("File already exists");
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(extracted2());
            bw.close();

            //compare between sysdate and date in file
            Date sysDate = new Date();
            System.out.println("Today date is ! " + sysDate);

            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sysDate;
            Date d2 = sdformat.parse(extracted2());
            if (d1.compareTo(d2) > 0) {
                System.out.println("Date 1 occurs after Date 2");
            } else if (d1.compareTo(d2) < 0) {
                System.out.println("Date 1 occurs before Date 2");
            } else if (d1.compareTo(d2) == 0) {
                System.out.println("Both dates are equal");
            }
            Thread.sleep(3000);
        }
    }

    private static String extracted2() throws ParserConfigurationException, SAXException, IOException {
        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Build Document
        Document document = builder.parse(new File("C:\\Users\\Abdessamad\\Desktop\\employees.xml"));

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());
        System.out.println("This is the date of birth "+root.getAttribute("dateOfBirth"));
        return root.getAttribute("dateOfBirth");
    }

    public String timeMarkerExtracterAndComparator(String dateFromXml,String dateFromFile){
        //2018-09-16T08:00:00
        String compartor = "";
        LocalDateTime localDateTimeXML = LocalDateTime.parse(dateFromXml);
        LocalDateTime localDateTimeFile = LocalDateTime.parse(dateFromFile);

        if(localDateTimeFile.isAfter(localDateTimeXML)){
            compartor= "isAfter";
        }else if (localDateTimeFile.isBefore(localDateTimeXML)){
            compartor = "isBefore";
        }else{
            compartor="isEqual";
        }
        System.out.println("The date in the xml file is "+localDateTimeXML);
        System.out.println("The date in the stored file is "+localDateTimeFile);
        System.out.println("The date in the stored  file  "+compartor+" the date in the XML file ");
        return compartor;
    }


    public void fileDeleter(){
        File file = new File("C:\\Users\\Abdessamad\\Desktop\\justARatherDocument.txt");

        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
    }
}
