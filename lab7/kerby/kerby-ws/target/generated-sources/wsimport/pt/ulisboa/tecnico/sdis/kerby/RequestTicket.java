
package pt.ulisboa.tecnico.sdis.kerby;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for requestTicket complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="requestTicket">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="client" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="server" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nounce" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ticketDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestTicket", propOrder = {
    "client",
    "server",
    "nounce",
    "ticketDuration"
})
public class RequestTicket {

    protected String client;
    protected String server;
    protected long nounce;
    protected int ticketDuration;

    /**
     * Gets the value of the client property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClient() {
        return client;
    }

    /**
     * Sets the value of the client property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClient(String value) {
        this.client = value;
    }

    /**
     * Gets the value of the server property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServer() {
        return server;
    }

    /**
     * Sets the value of the server property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServer(String value) {
        this.server = value;
    }

    /**
     * Gets the value of the nounce property.
     * 
     */
    public long getNounce() {
        return nounce;
    }

    /**
     * Sets the value of the nounce property.
     * 
     */
    public void setNounce(long value) {
        this.nounce = value;
    }

    /**
     * Gets the value of the ticketDuration property.
     * 
     */
    public int getTicketDuration() {
        return ticketDuration;
    }

    /**
     * Sets the value of the ticketDuration property.
     * 
     */
    public void setTicketDuration(int value) {
        this.ticketDuration = value;
    }

}
