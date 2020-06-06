package ge.bog.finalproject.api;

import ge.bog.finalproject.models.*;
import ge.bog.finalproject.services.*;
import ge.bog.finalproject.utils.ParametersChecker;
import org.apache.log4j.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class addCompanyToCountry extends HttpServlet {


    private static final Logger LOGGER = Logger.getLogger(addCompanyToCountry.class.getName());

    private CompanyToCountryActions companyToCountryActions = new CompanyToCountryActionsImpl();

    private ParametersChecker parametersChecker = new ParametersChecker();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Executing POST method");
        String companyName = parametersChecker.validateName(req, resp, "companyName");
        String countryName = parametersChecker.validateName(req, resp, "countryName");
        if (companyName != null && countryName != null) {
            LOGGER.info("Passed company with name " + companyName + " and country with name " + countryName);
            Long result = companyToCountryActions.add(companyName, countryName);
            if (result != null) {
                LOGGER.info("office of a company named " + companyName + " in a country named " + countryName + " successfully added");
                resp.getWriter().write("Office Successfully added!\n");
            } else {
                LOGGER.info("country with name " + countryName + " or company with name " + companyName + " does not exist");
                resp.getWriter().write("country with name " + countryName + " or company with name " + companyName + " does not exist\n");
            }
        }
    }
}
