
package pt.ulisboa.tecnico.sdis.kerby;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "KerbyService", targetNamespace = "http://kerby.sdis.tecnico.ulisboa.pt/", wsdlLocation = "file:/Users/goncalo/Downloads/kerby/kerby-ws-cli/../kerby-contract/KerbyService.wsdl")
public class KerbyService
    extends Service
{

    private final static URL KERBYSERVICE_WSDL_LOCATION;
    private final static WebServiceException KERBYSERVICE_EXCEPTION;
    private final static QName KERBYSERVICE_QNAME = new QName("http://kerby.sdis.tecnico.ulisboa.pt/", "KerbyService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/Users/goncalo/Downloads/kerby/kerby-ws-cli/../kerby-contract/KerbyService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        KERBYSERVICE_WSDL_LOCATION = url;
        KERBYSERVICE_EXCEPTION = e;
    }

    public KerbyService() {
        super(__getWsdlLocation(), KERBYSERVICE_QNAME);
    }

    public KerbyService(WebServiceFeature... features) {
        super(__getWsdlLocation(), KERBYSERVICE_QNAME, features);
    }

    public KerbyService(URL wsdlLocation) {
        super(wsdlLocation, KERBYSERVICE_QNAME);
    }

    public KerbyService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, KERBYSERVICE_QNAME, features);
    }

    public KerbyService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public KerbyService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns KerbyPortType
     */
    @WebEndpoint(name = "KerbyPort")
    public KerbyPortType getKerbyPort() {
        return super.getPort(new QName("http://kerby.sdis.tecnico.ulisboa.pt/", "KerbyPort"), KerbyPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns KerbyPortType
     */
    @WebEndpoint(name = "KerbyPort")
    public KerbyPortType getKerbyPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://kerby.sdis.tecnico.ulisboa.pt/", "KerbyPort"), KerbyPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (KERBYSERVICE_EXCEPTION!= null) {
            throw KERBYSERVICE_EXCEPTION;
        }
        return KERBYSERVICE_WSDL_LOCATION;
    }

}
