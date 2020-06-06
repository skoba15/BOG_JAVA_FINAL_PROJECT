package ge.bog.finalproject.api;

import com.google.gson.*;
import ge.bog.finalproject.models.*;
import ge.bog.finalproject.services.*;
import ge.bog.finalproject.utils.ParametersChecker;
import org.apache.log4j.*;
import org.ietf.jgss.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class GetCompaniesFromCountry extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(addCompanyToCountry.class.getName());

    private CompanyToCountryActions companyToCountryActions = new CompanyToCountryActionsImpl();

    private ParametersChecker parametersChecker = new ParametersChecker();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Executing GET method");
        String companyName =parametersChecker.validateCompanyName(req, resp, "companyName");
        String countryName = parametersChecker.validateName(req, resp, "countryName");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (companyName != null && countryName != null) {
            List<Company> companies = companyToCountryActions.getCompaniesfromCountry(companyName, countryName);
            if (companies != null) {
                if (companies.isEmpty()) {
                    LOGGER.info("No matches for the company " + companyName);
                    resp.getWriter().write("There are no offices of company " + companyName + " in " +  countryName);
                }
                else {
                    String json = new Gson().toJson(companies);
                    resp.getWriter().write(json);
                }
            } else {
                LOGGER.info("Invalid country name " + countryName);
                resp.getWriter().write("invalid country name " + countryName);
            }
        }
    }


}
