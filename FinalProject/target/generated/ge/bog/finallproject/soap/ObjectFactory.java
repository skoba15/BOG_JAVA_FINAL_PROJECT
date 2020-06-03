
package ge.bog.finallproject.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ge.bog.finallproject.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetNetWorth_QNAME = new QName("http://soap.finallproject.bog.ge/", "getNetWorth");
    private final static QName _GetNetWorthResponse_QNAME = new QName("http://soap.finallproject.bog.ge/", "getNetWorthResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ge.bog.finallproject.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetNetWorth }
     * 
     */
    public GetNetWorth createGetNetWorth() {
        return new GetNetWorth();
    }

    /**
     * Create an instance of {@link GetNetWorthResponse }
     * 
     */
    public GetNetWorthResponse createGetNetWorthResponse() {
        return new GetNetWorthResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNetWorth }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.finallproject.bog.ge/", name = "getNetWorth")
    public JAXBElement<GetNetWorth> createGetNetWorth(GetNetWorth value) {
        return new JAXBElement<GetNetWorth>(_GetNetWorth_QNAME, GetNetWorth.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNetWorthResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.finallproject.bog.ge/", name = "getNetWorthResponse")
    public JAXBElement<GetNetWorthResponse> createGetNetWorthResponse(GetNetWorthResponse value) {
        return new JAXBElement<GetNetWorthResponse>(_GetNetWorthResponse_QNAME, GetNetWorthResponse.class, null, value);
    }

}
