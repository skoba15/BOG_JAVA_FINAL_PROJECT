package ge.bog.finalproject.services;

import ge.bog.finalproject.models.*;

import java.util.*;

public interface CompanyToCountryActions {

    Long add(String companyName, String countryName);

    Long remove(String companyName, String countryName);

    CompanyToCountry getRelationByNames(String companyName, String countryName);

    List<Company> getCompaniesfromCountry(String companyName, String countryName);

}
