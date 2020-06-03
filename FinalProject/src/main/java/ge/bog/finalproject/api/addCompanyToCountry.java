package ge.bog.finalproject.api;

import ge.bog.finalproject.models.*;
import ge.bog.finalproject.services.*;
import org.apache.log4j.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class addCompanyToCountry extends HttpServlet {


    private static final Logger LOGGER = Logger.getLogger(addCompanyToCountry.class.getName());

    private CompanyToCountryActions companyToCountryActions = new CompanyToCountryActionsImpl();

    private String validateParameter(HttpServletRequest req, HttpServletResponse resp, String parameterName) throws IOException {
        String result = req.getParameter(parameterName);
        if (result != null) {
            if (result.equals("")) {
                LOGGER.info("empty " + parameterName + " passed");
                resp.getWriter().write("empty parameter " + parameterName + " not allowed\n");
                return null;
            }
            return result;
        } else {
            LOGGER.info("parameter" + parameterName + " missing\n");
            resp.getWriter().write("parameter " + parameterName + " missing\n");
            return null;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Executing POST method");
        String companyName = validateParameter(req, resp, "companyName");
        String countryName = validateParameter(req, resp, "countryName");
        if (companyName != null && countryName != null) {
            LOGGER.info("Passed company with name " + companyName + " and country with name " + countryName);
            Long result = companyToCountryActions.add(companyName, countryName);
            if (result != null) {
                LOGGER.info("office of a company named " + companyName + " in a country named " + countryName + " successfully added");
                resp.getWriter().write("Office Successfully added!\n");
            } else {
                LOGGER.info("country with name " + countryName + " or company with name " + companyName + " does not exist");
                resp.getWriter().write("country with name " + countryName + "or company with name " + companyName + "does not exist\n");
            }
        }
    }
}
