
package pt.ulisboa.tecnico.sdis.kerby;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pt.ulisboa.tecnico.sdis.kerby package. 
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

    private final static QName _BadTicketRequest_QNAME = new QName("http://kerby.sdis.tecnico.ulisboa.pt/", "BadTicketRequest");
    private final static QName _RequestTicket_QNAME = new QName("http://kerby.sdis.tecnico.ulisboa.pt/", "requestTicket");
    private final static QName _RequestTicketResponse_QNAME = new QName("http://kerby.sdis.tecnico.ulisboa.pt/", "requestTicketResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pt.ulisboa.tecnico.sdis.kerby
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BadTicketRequest }
     * 
     */
    public BadTicketRequest createBadTicketRequest() {
        return new BadTicketRequest();
    }

    /**
     * Create an instance of {@link RequestTicket }
     * 
     */
    public RequestTicket createRequestTicket() {
        return new RequestTicket();
    }

    /**
     * Create an instance of {@link RequestTicketResponse }
     * 
     */
    public RequestTicketResponse createRequestTicketResponse() {
        return new RequestTicketResponse();
    }

    /**
     * Create an instance of {@link CipheredView }
     * 
     */
    public CipheredView createCipheredView() {
        return new CipheredView();
    }

    /**
     * Create an instance of {@link RequestTimeView }
     * 
     */
    public RequestTimeView createRequestTimeView() {
        return new RequestTimeView();
    }

    /**
     * Create an instance of {@link AuthView }
     * 
     */
    public AuthView createAuthView() {
        return new AuthView();
    }

    /**
     * Create an instance of {@link SessionKeyView }
     * 
     */
    public SessionKeyView createSessionKeyView() {
        return new SessionKeyView();
    }

    /**
     * Create an instance of {@link SessionKeyAndTicketView }
     * 
     */
    public SessionKeyAndTicketView createSessionKeyAndTicketView() {
        return new SessionKeyAndTicketView();
    }

    /**
     * Create an instance of {@link TicketView }
     * 
     */
    public TicketView createTicketView() {
        return new TicketView();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BadTicketRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://kerby.sdis.tecnico.ulisboa.pt/", name = "BadTicketRequest")
    public JAXBElement<BadTicketRequest> createBadTicketRequest(BadTicketRequest value) {
        return new JAXBElement<BadTicketRequest>(_BadTicketRequest_QNAME, BadTicketRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://kerby.sdis.tecnico.ulisboa.pt/", name = "requestTicket")
    public JAXBElement<RequestTicket> createRequestTicket(RequestTicket value) {
        return new JAXBElement<RequestTicket>(_RequestTicket_QNAME, RequestTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://kerby.sdis.tecnico.ulisboa.pt/", name = "requestTicketResponse")
    public JAXBElement<RequestTicketResponse> createRequestTicketResponse(RequestTicketResponse value) {
        return new JAXBElement<RequestTicketResponse>(_RequestTicketResponse_QNAME, RequestTicketResponse.class, null, value);
    }

}
