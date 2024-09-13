
package com.asac.accountinquiry;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rqDatetime" type="{urn:AccountInquiry}DateTime"/>
 *         &lt;element name="bankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequest", propOrder = {
    "messageId",
    "rqDatetime",
    "bankCode",
    "accountNo",
    "accountCurrency"
})
public class ServiceRequest {

    @XmlElementRef(name = "messageId", type = JAXBElement.class)
    protected JAXBElement<String> messageId;
    @XmlElement(required = true)
    protected String rqDatetime;
    @XmlElementRef(name = "bankCode", type = JAXBElement.class)
    protected JAXBElement<String> bankCode;
    @XmlElement(required = true)
    protected String accountNo;
    @XmlElementRef(name = "accountCurrency", type = JAXBElement.class)
    protected JAXBElement<String> accountCurrency;

    /**
     * Gets the value of the messageId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMessageId() {
        return messageId;
    }

    /**
     * Sets the value of the messageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMessageId(JAXBElement<String> value) {
        this.messageId = value;
    }

    /**
     * Gets the value of the rqDatetime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRqDatetime() {
        return rqDatetime;
    }

    /**
     * Sets the value of the rqDatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRqDatetime(String value) {
        this.rqDatetime = value;
    }

    /**
     * Gets the value of the bankCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBankCode() {
        return bankCode;
    }

    /**
     * Sets the value of the bankCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBankCode(JAXBElement<String> value) {
        this.bankCode = value;
    }

    /**
     * Gets the value of the accountNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Sets the value of the accountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNo(String value) {
        this.accountNo = value;
    }

    /**
     * Gets the value of the accountCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAccountCurrency() {
        return accountCurrency;
    }

    /**
     * Sets the value of the accountCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAccountCurrency(JAXBElement<String> value) {
        this.accountCurrency = value;
    }

}
