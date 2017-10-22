package connection.dao;

import pojo.Company;

public interface CompanyDAO {

    class CompanyDAOException extends Exception {
    }

    Company getByName(String name) throws CompanyDAOException, TaskDAOimpl.TaskDAOException, EmployeeDAOimpl.EmployeeDAOException;

    void deleteAll() throws CompanyDAOException, EmployeeDAOimpl.EmployeeDAOException, TaskDAOimpl.TaskDAOException;

    void insertCompany(Company company) throws CompanyDAOException, TaskDAOimpl.TaskDAOException, EmployeeDAOimpl.EmployeeDAOException;

    void updateCompany(Company company)throws CompanyDAOException;
}
