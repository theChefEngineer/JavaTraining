import com.pckg.objects.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MarshallerProcessor {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        UnMarshalling();
    }

    private static void Marshalling() throws JAXBException, FileNotFoundException {
        //Creating the context
        JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
        //Creating the Marshaller
        Marshaller marshaller = jaxbContext.createMarshaller();
        //Setting the property to show xml format output
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //Setting the values in the POJO
        Student student = new Student("Abdessamad", 1190, "Hadoop");
        //Calling the marshaller method
        marshaller.marshal(student, new FileOutputStream("C:\\Users\\bouhl\\OneDrive\\Bureau\\CAPGEMINI\\student.xml"));
    }


    public static void UnMarshalling() throws JAXBException {
        File file = new File("C:\\Users\\bouhl\\OneDrive\\Bureau\\CAPGEMINI\\student.xml");
        //Creating the context
        JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
        //Creating the unmarshaller object
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        //calling the unmarshall method
        Student student = (Student) unmarshaller.unmarshal(file);

        System.out.println(" " + student.getName() + " " + student.getId() + " " + student.getSubject());

    }
}
