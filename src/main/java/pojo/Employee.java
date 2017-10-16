package pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class Employee {
    @XmlElement(required = true)
    String company;
    @XmlElement(required = true)
    int employeeID;
    @XmlElement(required = true)
    String employeeName;
    @XmlElement(required = true)
    String employeePosition;
    @XmlElement(required = true)
    String eMail;
    @XmlElement(required = true)
    String password;
    @XmlElement(name = "taskInWork")
    int currentTask;




    public Employee(String employeeName, String employeePosition, String eMail, Company company, String password) {
        this.company = company.companyName;
        employeeID = company.getEmployeeIDCounter();
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.eMail = eMail;
        this.password = password;
        company.addWorkers(this);
    }

    public Employee() {
    }

    /* конструктор для БД */
    public Employee(int employeeID, String employeeName, String employeePosition, String eMail, int currentTask, String company, String password) {
        this.company = company;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.eMail = eMail;
        this.password = password;
        this.currentTask = currentTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return employeeID == employee.employeeID;
    }

    @Override
    public int hashCode() {
        int result = getEmployeeID();
        result = 31 * result + (getEmployeeName() != null ? getEmployeeName().hashCode() : 0);
        result = 31 * result + (getEMail() != null ? getEMail().hashCode() : 0);
        result = 31 * result + (getCompany() != null ? getCompany().hashCode() : 0);
        return result;
    }

    public String getCompany() {
        return company;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public String getEMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public int getCurrentTask() {
        return currentTask;
    }
}
