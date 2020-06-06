package ge.bog.finalproject.services;

import ge.bog.finalproject.DBConnection.*;
import ge.bog.finalproject.models.*;
import org.apache.log4j.*;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.util.*;

public class CompanyToCountryActionsImpl implements CompanyToCountryActions {

    private CountryService countryService = new CountryServiceImpl();

    private CompanyService companyService = new CompanyServiceImpl();


    private static final Logger LOGGER = Logger.getLogger(CompanyToCountryActionsImpl.class.getName());


    @Override
    public Long add(String companyName, String countryName) {
        Session session = DBConnection.getSessionFactory().openSession();
        session.beginTransaction();
        LOGGER.info("Trying to add " + "an office of a company named " + companyName + " in " + countryName);
        CountryService countryService = new CountryServiceImpl();
        Company company = companyService.getCompanyByName(companyName);
        Country country = countryService.getCountryByName(countryName);
        if (company == null || country == null) {
            session.close();
            return null;
        }
        Long result = (long) 1;
        CompanyToCountry companyToCountry = getRelationByNames(companyName, countryName);
        if (companyToCountry != null) {

            Long officesNumber = companyToCountry.getOfficesNumber();
            companyToCountry.setOfficesNumber(officesNumber + 1);
            session.merge(companyToCountry);
        } else {
            CompanyToCountryKey id = new CompanyToCountryKey(company.getId(), country.getId());
            companyToCountry = new CompanyToCountry(id, company, country, (long) 1);
            session.save(companyToCountry);
        }
        LOGGER.info("increased number of offices of a company named " + companyName + " in " + countryName + " by 1");
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Long remove(String companyName, String countryName) {
        Session session = DBConnection.getSessionFactory().openSession();
        session.beginTransaction();
        LOGGER.info("Trying to remove " + "an office of a company named " + companyName + " from " + countryName);
        CountryService countryService = new CountryServiceImpl();
        Company company = companyService.getCompanyByName(companyName);
        Country country = countryService.getCountryByName(countryName);
        if (company == null || country == null) {
            session.close();
            return (long) -1;
        }
        String hql = "SELECT c FROM CompanyToCountry c WHERE c.company.name = :companyName AND c.country.name = :countryName";
        Query query = session.createQuery(hql);
        query.setParameter("companyName", companyName);
        query.setParameter("countryName", countryName);
        CompanyToCountry result;
        try {
            result = (CompanyToCountry) query.getSingleResult();
        } catch (Exception e) {
            LOGGER.error(e);
            result = null;
        }
        if (result == null || result.getOfficesNumber() == 0) {
            session.close();
            return (long) 0;
        } else {
            Long officesNumber = result.getOfficesNumber();
            result.setOfficesNumber(officesNumber - 1);
            session.merge(result);
        }
        session.getTransaction().commit();
        session.close();
        return (long) 1;
    }

    @Override
    public CompanyToCountry getRelationByNames(String companyName, String countryName) {
        Session session = DBConnection.getSessionFactory().openSession();
        session.beginTransaction();
        LOGGER.info("Searching for relation " + "of a company named " + companyName + " and country " + countryName + " in the database");
        String hql = "SELECT c FROM CompanyToCountry c WHERE c.company.name = :companyName AND c.country.name = :countryName";
        Query query = session.createQuery(hql);
        query.setParameter("companyName", companyName);
        query.setParameter("countryName", countryName);
        CompanyToCountry result;
        try {
            result = (CompanyToCountry) query.getSingleResult();
        } catch (Exception e) {
            LOGGER.error(e);
            result = null;
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Company> getCompaniesfromCountry(String companyName, String countryName) {
        Session session = DBConnection.getSessionFactory().openSession();
        session.beginTransaction();
        LOGGER.info("Searching for companies " + "with name like " + companyName + " in " + countryName + " in the database");
        Country country = countryService.getCountryByName(countryName);
        if (country == null) {
            session.close();
            return null;
        }
        String hql = "SELECT c.company FROM CompanyToCountry c WHERE c.company.name like :companyName AND c.country.name = :countryName AND c.officesNumber > 0";
        Query query = session.createQuery(hql);
        query.setParameter("companyName", "%" + companyName + "%");
        query.setParameter("countryName", countryName);

        List<Company> result;
        try {
            result = query.getResultList();
        } catch (Exception e) {
            LOGGER.error(e);
            result = null;
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

}
