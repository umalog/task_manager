package services;

import pojo.Employee;

public interface AuthorizationService {
    Employee auth(String eMail, String password);

}
