package ge.bog.finalproject.api;

import com.google.gson.*;
import ge.bog.finalproject.models.*;
import ge.bog.finalproject.services.*;
import org.apache.log4j.*;
import org.ietf.jgss.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class GetCompaniesFromCountry extends HttpServlet {

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

    private String validateCompanyName(HttpServletRequest req, HttpServletResponse resp, String companyName) throws IOException {
        String result = req.getParameter(companyName);
        if (result != null) {
            return result;
        } else {
            LOGGER.info("parameter" + companyName + " missing\n");
            resp.getWriter().write("parameter " + companyName + " missing\n");
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Executing GET method");
        String companyName = validateCompanyName(req, resp, "companyName");
        String countryName = validateParameter(req, resp, "countryName");
        Gson gson = new Gson();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (companyName != null && countryName != null) {
            List<Company> companies = companyToCountryActions.getCompaniesfromCountry(companyName, countryName);
            if (companies != null) {
                String json = new Gson().toJson(companies);
                resp.getWriter().write(json);
            } else {
                LOGGER.info("Not found any company with name like " + companyName + " in " + countryName);
                resp.getWriter().write("invalid country name or no matches can be found for the given company name pattern in " + countryName);
            }
        }
    }


}
