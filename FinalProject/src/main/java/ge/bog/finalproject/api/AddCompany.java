package ge.bog.finalproject.api;

import ge.bog.finalproject.services.*;
import ge.bog.finalproject.utils.ParametersChecker;
import org.apache.log4j.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AddCompany extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddCountry.class.getName());

    private CompanyService companyService = new CompanyServiceImpl();

    private ParametersChecker parametersChecker = new ParametersChecker();




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Executing POST method");

        String companyName = parametersChecker.validateName(req, resp, "companyName");
        Long totalEmployees = parametersChecker.validateEmployeesNumber(req, resp, "employeesNumber");

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
