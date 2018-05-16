
package pt.ulisboa.tecnico.sdis.kerby;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sessionKeyView complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sessionKeyView">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="encodedKeyXY" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="nounce" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sessionKeyView", propOrder = {
    "encodedKeyXY",
    "nounce"
})
public class SessionKeyView {

    protected byte[] encodedKeyXY;
    protected long nounce;

    /**
     * Gets the value of the encodedKeyXY property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getEncodedKeyXY() {
        return encodedKeyXY;
    }

    /**
     * Sets the value of the encodedKeyXY property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setEncodedKeyXY(byte[] value) {
        this.encodedKeyXY = value;
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

}
