package ge.bog.finallproject.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.2
 * 2020-06-06T12:20:43.338+04:00
 * Generated source version: 3.0.2
 * 
 */
@WebService(targetNamespace = "http://soap.finallproject.bog.ge/", name = "SoapNetWorthCounter")
@XmlSeeAlso({ObjectFactory.class})
public interface SoapNetWorthCounter {

    @WebMethod
    @Action(input = "http://soap.finallproject.bog.ge/SoapNetWorthCounter/getNetWorthRequest", output = "http://soap.finallproject.bog.ge/SoapNetWorthCounter/getNetWorthResponse")
    @RequestWrapper(localName = "getNetWorth", targetNamespace = "http://soap.finallproject.bog.ge/", className = "ge.bog.finallproject.soap.GetNetWorth")
    @ResponseWrapper(localName = "getNetWorthResponse", targetNamespace = "http://soap.finallproject.bog.ge/", className = "ge.bog.finallproject.soap.GetNetWorthResponse")
    @WebResult(name = "return", targetNamespace = "")
    public int getNetWorth(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
