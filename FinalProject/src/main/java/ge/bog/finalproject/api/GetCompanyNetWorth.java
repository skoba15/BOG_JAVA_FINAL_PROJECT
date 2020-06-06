package ge.bog.finalproject.api;

import ge.bog.finallproject.soap.*;
import ge.bog.finalproject.models.*;
import ge.bog.finalproject.services.*;
import ge.bog.finalproject.utils.ParametersChecker;
import org.apache.cxf.jaxws.*;
import org.apache.log4j.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class GetCompanyNetWorth extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(addCompanyToCountry.class.getName());

    private CompanyService companyService = new CompanyServiceImpl();

    private ParametersChecker parametersChecker = new ParametersChecker();



    private SoapNetWorthCounter getWs () {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SoapNetWorthCounter.class);
        factory.setWsdlURL("http://localhost:9898/SoapNetWorthCounter?wsdl");
        SoapNetWorthCounter soapNetWorthCounter = (SoapNetWorthCounter) factory.create();
        return soapNetWorthCounter;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Executing GET method");

        String companyName = parametersChecker.validateName(req, resp, "companyName");

        if (companyName != null) {
            Company company = companyService.getCompanyByName(companyName);
            if (company != null) {
                SoapNetWorthCounter soapNetWorthCounter = getWs();
                int companyNetWorth = soapNetWorthCounter.getNetWorth(companyName);
                resp.getWriter().write("Net worth of a company named " + companyName + " is " + companyNetWorth);
            }
            else {
                LOGGER.info("company with name " + companyName + " does not exist");
                resp.getWriter().write("company with name " + companyName + " does not exist");
            }
        }
    }
}
