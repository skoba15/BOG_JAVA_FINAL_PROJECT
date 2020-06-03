package ge.bog.finalproject.services;

import ge.bog.finalproject.DBConnection.*;
import ge.bog.finalproject.api.*;
import ge.bog.finalproject.models.*;
import org.apache.log4j.*;
import org.hibernate.*;
import org.hibernate.query.Query;

public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOGGER = Logger.getLogger(CompanyServiceImpl.class.getName());


    @Override
    public Long save(String companyName, Long totalEmployees) {
        Session session = DBConnection.getSessionFactory().openSession();
        session.beginTransaction();
        LOGGER.info("adding company " + companyName + " with employees number of " + totalEmployees + " in database");
        Company company = new Company(companyName, totalEmployees);
        Long resultId = (Long) session.save(company);
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e);
            resultId = null;
        }
        session.close();
        return resultId;
    }


    public Company getCompanyByName(String companyName) {
        Session session = DBConnection.getSessionFactory().openSession();
        session.beginTransaction();
        LOGGER.info("getting company " + companyName + " from database");
        String hql = "FROM Company as c WHERE c.name = :companyName";
        Query query = session.createQuery(hql);
        query.setParameter("companyName", companyName);
        Company company;
        try {
            company = (Company) query.getSingleResult();
        } catch (Exception e) {
            LOGGER.error(e);
            company = null;
        }
        session.getTransaction().commit();
        session.close();
        return company;
    }
}
