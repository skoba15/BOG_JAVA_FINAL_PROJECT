package ge.bog.finalproject.services;

import ge.bog.finalproject.models.*;

public interface CompanyService {

    Long save(String companyName, Long employeesNumber);

    Company getCompanyByName(String companyName);
}
