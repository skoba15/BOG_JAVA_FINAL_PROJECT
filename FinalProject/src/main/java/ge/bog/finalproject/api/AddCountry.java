package ge.bog.finalproject.api;

import ge.bog.finalproject.services.*;
import org.apache.log4j.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.validation.constraints.*;
import java.io.*;
import java.util.*;

public class AddCountry extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddCountry.class.getName());

    private CountryService countryService = new CountryServiceImpl();


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

        String countryName = validateParameter(req, resp, "countryName");
        String countryCode = validateParameter(req, resp, "countryCode");

        if (countryName != null && countryCode != null) {
            LOGGER.info("Passed country with name " + countryName + " and country code " + countryCode);
            Long result = countryService.save(countryName, countryCode);
            if (result != null) {
                LOGGER.info("country with name " + countryName + " and country code " + countryCode + "successfully added");
                resp.getWriter().write("Country Successfully added!\n");
            } else {
                LOGGER.info("country with name " + countryName + " or country code with name " + countryCode + " already exists");
                resp.getWriter().write("country with name " + countryName + "or country code with name " + countryCode + "already exists\n");
            }
        }
    }
}
