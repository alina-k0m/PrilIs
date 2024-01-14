package jaxb.test;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: User
 * Date: 17.01.2022
 * Time: 19:43
 */

import jaxb.model.Department;
import jaxb.model.Employee;
import jaxb.model.Organization;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestExample {
    private static final String XML_FILE = "organization-info.xml";

    public static void main(String[] args) {

        Employee emp1 = new Employee("E01", "Tom", null);
        Employee emp2 = new Employee("E02", "Mary", "E01");
        Employee emp3 = new Employee("E03", "John", null);

        List<Employee> list = new ArrayList<Employee>();
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);

        Department dept = new Department("D01", "ACCOUNTING", "NEW YORK");
        List<Department> list1 = new ArrayList<Department>();
        list1.add(dept);

        Organization org = new Organization("Oriflame");
        List<Organization> list0 = new ArrayList<Organization>();
        list0.add(org);

        org.setDepartments(list1);
        org.setEmployees(list);
        try {
            // create JAXB context and instantiate marshaller
            JAXBContext context = JAXBContext.newInstance(Organization.class);

            // (1) Marshaller : Java Object to XML content.
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(org, System.out);

            // Write to File
            File outFile = new File(XML_FILE);
            m.marshal(org, outFile);

            System.err.println("Write to file: " + outFile.getAbsolutePath());
            // (2) Unmarshaller : Read XML content to Java Object.
            Unmarshaller um = context.createUnmarshaller();

            // XML file create before.
            Organization orgFromFile2 = (Organization) um.unmarshal(new FileReader(
                    XML_FILE));
            List<Department> deps = orgFromFile2.getDepartments();
            for (Department dep : deps) {
                System.out.println("Department: " + dep.getDeptName());

                List<Employee> emps = dep.getEmployees();
                for (Employee emp : emps) {
                    System.out.println("Employee: " + emp.getEmpName());
                }
            }
        } catch (PropertyException e) {
            throw new RuntimeException(e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
