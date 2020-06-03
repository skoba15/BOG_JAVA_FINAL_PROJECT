package ge.bog.finalproject.services;

import com.mysql.cj.jdbc.*;
import ge.bog.finalproject.DBConnection.*;
import ge.bog.finalproject.models.*;
import org.apache.log4j.*;
import org.hibernate.*;
import org.hibernate.query.Query;

public class CountryServiceImpl implements CountryService {

    private static final Logger LOGGER = Logger.getLogger(CompanyServiceImpl.class.getName());


    @Override
    public Long save(String countryName, String countryCode) {
        Session session = DBConnection.getSessionFactory().openSession();
        session.beginTransaction();
        LOGGER.info("adding country " + countryName + " with country code " + countryCode + " in database");
        Country country = new Country(countryName, countryCode);
        Long resultId = (Long) session.save(country);
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e);
            resultId = null;
        }
        session.close();
        return resultId;
    }

    @Override
    public Country getCountryByName(String countryName) {
        Session session = DBConnection.getSessionFactory().openSession();
        session.beginTransaction();
        LOGGER.info("getting country " + countryName + " from database ");
        String hql = "FROM Country as c WHERE c.name = :countryName";
        Query query = session.createQuery(hql);
        query.setParameter("countryName", countryName);
        Country country;
        try {
            country = (Country) query.getSingleResult();
        } catch (Exception e) {
            LOGGER.error(e);
            country = null;
        }
        session.getTransaction().commit();
        session.close();
        return country;
    }
}
