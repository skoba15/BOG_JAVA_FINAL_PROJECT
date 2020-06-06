package ge.bog.finalproject.api;

import ge.bog.finalproject.services.*;
import ge.bog.finalproject.utils.ParametersChecker;
import org.apache.log4j.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.validation.constraints.*;
import java.io.*;
import java.util.*;

public class AddCountry extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddCountry.class.getName());

    private CountryService countryService = new CountryServiceImpl();

    private ParametersChecker parametersChecker = new ParametersChecker();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Executing POST method");

        String countryName = parametersChecker.validateName(req, resp, "countryName");
        String countryCode = parametersChecker.validateName(req, resp, "countryCode");

        if (countryName != null && countryCode != null) {
            LOGGER.info("Passed country with name " + countryName + " and country code " + countryCode);
            Long result = countryService.save(countryName, countryCode);
            if (result != null) {
                LOGGER.info("country with name " + countryName + " and country code " + countryCode + "successfully added");
                resp.getWriter().write("Country Successfully added!\n");
            } else {
                LOGGER.info("country with name " + countryName + " or country code with name " + countryCode + " already exists");
                resp.getWriter().write("country with name " + countryName + " or country code with name " + countryCode + " already exists\n");
            }
        }
    }
}
