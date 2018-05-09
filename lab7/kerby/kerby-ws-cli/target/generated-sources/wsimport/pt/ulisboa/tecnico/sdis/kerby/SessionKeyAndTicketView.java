
package pt.ulisboa.tecnico.sdis.kerby;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sessionKeyAndTicketView complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sessionKeyAndTicketView">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionKey" type="{http://kerby.sdis.tecnico.ulisboa.pt/}cipheredView" minOccurs="0"/>
 *         &lt;element name="ticket" type="{http://kerby.sdis.tecnico.ulisboa.pt/}cipheredView" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sessionKeyAndTicketView", propOrder = {
    "sessionKey",
    "ticket"
})
public class SessionKeyAndTicketView {

    protected CipheredView sessionKey;
    protected CipheredView ticket;

    /**
     * Gets the value of the sessionKey property.
     * 
     * @return
     *     possible object is
     *     {@link CipheredView }
     *     
     */
    public CipheredView getSessionKey() {
        return sessionKey;
    }

    /**
     * Sets the value of the sessionKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link CipheredView }
     *     
     */
    public void setSessionKey(CipheredView value) {
        this.sessionKey = value;
    }

    /**
     * Gets the value of the ticket property.
     * 
     * @return
     *     possible object is
     *     {@link CipheredView }
     *     
     */
    public CipheredView getTicket() {
        return ticket;
    }

    /**
     * Sets the value of the ticket property.
     * 
     * @param value
     *     allowed object is
     *     {@link CipheredView }
     *     
     */
    public void setTicket(CipheredView value) {
        this.ticket = value;
    }

}
