package ge.bog.finalproject.services;

import ge.bog.finalproject.models.*;

public interface CountryService {

    Long save(String countryName, String countryCode);

    Country getCountryByName(String countryName);
}
