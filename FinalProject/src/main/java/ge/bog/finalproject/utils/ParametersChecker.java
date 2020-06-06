package ge.bog.finalproject.utils;

import ge.bog.finalproject.api.AddCountry;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ParametersChecker {

    private static final Logger LOGGER = Logger.getLogger(ParametersChecker.class.getName());

    public static String validateName(HttpServletRequest req, HttpServletResponse resp, String parameterName) throws IOException {
        String result = req.getParameter(parameterName);
        if (result != null) {
            if (result.equals("")) {
                LOGGER.info("empty " + parameterName + " passed");
                resp.getWriter().write("empty parameter " + parameterName + " not allowed\n");
                return null;
            }
            else if (result.length() > 100) {
                LOGGER.info(parameterName + " is too long: " + result.length());
                resp.getWriter().write("names with length more than 100 are not allowed");
                return null;
            }
            return result;
        } else {
            LOGGER.info("parameter" + parameterName + " missing\n");
            resp.getWriter().write("parameter " + parameterName + " missing\n");
            return null;
        }
    }

    public static Long validateEmployeesNumber(HttpServletRequest req, HttpServletResponse resp, String parameterName) throws IOException {
        Long resultNumber;
        String result = req.getParameter(parameterName);
        if (result != null) {
            try {
                resultNumber = Long.valueOf(result);
                if (resultNumber < 0) {
                    LOGGER.info("Negative number entered for the total number " + resultNumber);
                    resp.getWriter().write("total number of employees can't be a negative number");
                    return null;
                }
                return resultNumber;
            } catch (NumberFormatException e) {
                LOGGER.error(e.getMessage(), e);
                resp.getWriter().write("ERROR: invalid number for " + parameterName + " \n");
                return null;
            }
        } else {
            LOGGER.info("parameter" + parameterName + " missing\n");
            resp.getWriter().write("parameter " + parameterName + " missing\n");
            return null;
        }
    }

    public static String validateCompanyName(HttpServletRequest req, HttpServletResponse resp, String companyName) throws IOException {
        String result = req.getParameter(companyName);
        if (result != null) {
            return result;
        } else {
            LOGGER.info("parameter" + companyName + " missing\n");
            resp.getWriter().write("parameter " + companyName + " missing\n");
            return null;
        }
    }
}
