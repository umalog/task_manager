package pojo;


import connection.dao.CompanyDAO;
import connection.dao.CompanyDAOimpl;
import connection.dao.EmployeeDAOimpl;
import connection.dao.TaskDAOimpl;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLFactory {
    private static CompanyDAO companyDAO= new CompanyDAOimpl();
    private static final Logger logger = Logger.getLogger(XMLFactory.class);
//    static {
//        PropertyConfigurator.configure("/log4j.properties");
//    }

    public static void main(String[] args) throws EmployeeDAOimpl.EmployeeDAOException, TaskDAOimpl.TaskDAOException, CompanyDAOimpl.CompanyDAOException {

        /* очистка SQL */
//        companyDAO.deleteAll();

        /* создание обьектов, сохранение в XML */
        Company xmlCorp = new Company("xmlCorp");
        Employee director = new Employee("Ivan Ivanovich", "Director", "test@inno.ru", xmlCorp, "password");
        Employee windowCleaner = new Employee("Vasya", "Window Cleaner", "work@inno.ru", xmlCorp, "password");
        Task bag = new Task("мыть окна", "все окна", windowCleaner, director, java.time.LocalDate.parse("2017-11-10"), xmlCorp);
        Task alert = new Task("Бу!","I need help!",windowCleaner, java.time.LocalDate.parse("2017-11-05"),xmlCorp);
        saveCompany(xmlCorp);
        logger.info(xmlCorp+" marshalling successful");
//
        /* из XML в SQL */
//        Company xmlCorp = loadCompany();
//        companyDAO.insertCompany(xmlCorp);

        /* из SQL в XML */
//        Company cmp = companyDAO.getByName("xmlCorp");
//        if (cmp != null) saveCompany(cmp);

        /* чтение из XML */
//        Company myCompany = loadCompany();



    }


    private static void saveCompany(Company company) {
        try {
            File file = new File("/Users/umalog/IdeaProjects/task_manager/src/main/java/file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(company, file);
            jaxbMarshaller.marshal(company, System.out);

        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }


    private static Company loadCompany() {
        try {
            File file = new File("/Users/umalog/IdeaProjects/task_manager/src/main/java/file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Company company = (Company) jaxbUnmarshaller.unmarshal(file);
            System.out.println(company);
            return company;
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
