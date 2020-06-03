package ge.bog.finalproject.api;

import ge.bog.finalproject.services.*;
import org.apache.log4j.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AddCompany extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddCountry.class.getName());

    private CompanyService companyService = new CompanyServiceImpl();

    private String validateName(HttpServletRequest req, HttpServletResponse resp, String parameterName) throws IOException {
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

    private Long validateEmployeesNumber(HttpServletRequest req, HttpServletResponse resp, String parameterName) throws IOException {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Executing POST method");

        String companyName = validateName(req, resp, "companyName");
        Long totalEmployees = validateEmployeesNumber(req, resp, "employeesNumber");

        if (companyName != null && totalEmployees != null) {
            LOGGER.info("Passed company with name " + companyName + " with total number of employees of " + totalEmployees);
            Long result = companyService.save(companyName, totalEmployees);
            if (result != null) {
                LOGGER.info("company with name " + companyName + " and number of employees of " + totalEmployees + "successfully added");
                resp.getWriter().write("Company Successfully added!\n");
            } else {
                LOGGER.info("company with name " + companyName + " already exists");
                resp.getWriter().write("company with such name already exists");
            }
        }
    }


}
