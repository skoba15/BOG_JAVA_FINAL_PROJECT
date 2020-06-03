package ge.bog.finallproject.soap;

import javax.jws.*;
import javax.xml.ws.*;

@WebService
public class SoapNetWorthCounter {

    @WebMethod
    public int getNetWorth(String companyName) {
        int netWorth = 0;
        for (int i = 0; i < companyName.length(); i++) {
            netWorth += (int)companyName.charAt(i);
        }
        return netWorth * 100;
    }

    public static void main(String[] args) {
        String bindingURI = "http://localhost:9898/SoapNetWorthCounter";
        SoapNetWorthCounter webService = new SoapNetWorthCounter();
        Endpoint.publish(bindingURI, webService);
        System.out.println("Server started at: " + bindingURI);
    }

}
