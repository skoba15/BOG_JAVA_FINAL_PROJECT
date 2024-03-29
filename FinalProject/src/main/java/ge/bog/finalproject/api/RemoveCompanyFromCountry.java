package ge.bog.finalproject.api;

import ge.bog.finalproject.services.*;
import ge.bog.finalproject.utils.ParametersChecker;
import org.apache.log4j.*;

import javax.net.ssl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class RemoveCompanyFromCountry extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(addCompanyToCountry.class.getName());

    private CompanyToCountryActions companyToCountryActions = new CompanyToCountryActionsImpl();

    private ParametersChecker parametersChecker = new ParametersChecker();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Executing POST method");
        String companyName = parametersChecker.validateName(req, resp, "companyName");
        String countryName = parametersChecker.validateName(req, resp, "countryName");

        if (companyName != null && countryName != null) {
            LOGGER.info("Passed company with name " + companyName + " and country " + countryName);
            Long result = companyToCountryActions.remove(companyName, countryName);
            if (result == -1) {
                LOGGER.info("country with name " + countryName + " or company with name " + companyName + " does not exist");
                resp.getWriter().write("country with name " + countryName + " or company with name " + companyName + " does not exist\n");
            } else if (result == 0) {
                LOGGER.info("No offices of company " + companyName + " found in a country named " + countryName);
                resp.getWriter().write("No offices found\n");
            } else {
                LOGGER.info("office of a company named " + companyName + " successfully removed from a country named " + countryName);
                resp.getWriter().write("Office Successfully removed!\n");
            }
        }
    }
}
