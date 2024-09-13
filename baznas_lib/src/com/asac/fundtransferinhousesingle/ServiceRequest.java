
package com.asac.fundtransferinhousesingle;

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
 *         &lt;element name="rqDatetime" type="{urn:FundTransferInHouseSingle}DateTime"/>
 *         &lt;element name="bankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceAccountCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceAccountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destinationAccountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sourceAccountCurrency",
    "sourceAccountNo",
    "destinationAccountNo",
    "transactionAmount",
    "chargeAmount",
    "chargeCode",
    "transactionId",
    "description",
    "chargeDescription"
})
public class ServiceRequest {

    @XmlElementRef(name = "messageId", type = JAXBElement.class)
    protected JAXBElement<String> messageId;
    @XmlElement(required = true)
    protected String rqDatetime;
    @XmlElementRef(name = "bankCode", type = JAXBElement.class)
    protected JAXBElement<String> bankCode;
    protected String sourceAccountCurrency;
    protected String sourceAccountNo;
    protected String destinationAccountNo;
    protected String transactionAmount;
    protected String chargeAmount;
    protected String chargeCode;
    protected String transactionId;
    protected String description;
    protected String chargeDescription;

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
     * Gets the value of the sourceAccountCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceAccountCurrency() {
        return sourceAccountCurrency;
    }

    /**
     * Sets the value of the sourceAccountCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceAccountCurrency(String value) {
        this.sourceAccountCurrency = value;
    }

    /**
     * Gets the value of the sourceAccountNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceAccountNo() {
        return sourceAccountNo;
    }

    /**
     * Sets the value of the sourceAccountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceAccountNo(String value) {
        this.sourceAccountNo = value;
    }

    /**
     * Gets the value of the destinationAccountNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationAccountNo() {
        return destinationAccountNo;
    }

    /**
     * Sets the value of the destinationAccountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationAccountNo(String value) {
        this.destinationAccountNo = value;
    }

    /**
     * Gets the value of the transactionAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Sets the value of the transactionAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionAmount(String value) {
        this.transactionAmount = value;
    }

    /**
     * Gets the value of the chargeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeAmount() {
        return chargeAmount;
    }

    /**
     * Sets the value of the chargeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeAmount(String value) {
        this.chargeAmount = value;
    }

    /**
     * Gets the value of the chargeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeCode() {
        return chargeCode;
    }

    /**
     * Sets the value of the chargeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeCode(String value) {
        this.chargeCode = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the chargeDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeDescription() {
        return chargeDescription;
    }

    /**
     * Sets the value of the chargeDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeDescription(String value) {
        this.chargeDescription = value;
    }

}
