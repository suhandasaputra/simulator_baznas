
package com.asac.fundtransferinquiry;

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
 *         &lt;element name="rqDatetime" type="{urn:FundTransferInquiry}DateTime"/>
 *         &lt;element name="bankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceAccountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceAccountCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destinationBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destinationAccountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sourceAccountNo",
    "sourceAccountCurrency",
    "destinationBankCode",
    "destinationAccountNo",
    "transactionAmount",
    "chargeCode",
    "chargeDescription",
    "transactionDate"
})
public class ServiceRequest {

    @XmlElementRef(name = "messageId", type = JAXBElement.class, required = false)
    protected JAXBElement<String> messageId;
    @XmlElement(required = true)
    protected String rqDatetime;
    @XmlElementRef(name = "bankCode", type = JAXBElement.class, required = false)
    protected JAXBElement<String> bankCode;
    @XmlElementRef(name = "sourceAccountNo", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceAccountNo;
    @XmlElementRef(name = "sourceAccountCurrency", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceAccountCurrency;
    @XmlElementRef(name = "destinationBankCode", type = JAXBElement.class, required = false)
    protected JAXBElement<String> destinationBankCode;
    @XmlElementRef(name = "destinationAccountNo", type = JAXBElement.class, required = false)
    protected JAXBElement<String> destinationAccountNo;
    @XmlElementRef(name = "transactionAmount", type = JAXBElement.class, required = false)
    protected JAXBElement<String> transactionAmount;
    @XmlElementRef(name = "chargeCode", type = JAXBElement.class, required = false)
    protected JAXBElement<String> chargeCode;
    @XmlElementRef(name = "chargeDescription", type = JAXBElement.class, required = false)
    protected JAXBElement<String> chargeDescription;
    @XmlElementRef(name = "transactionDate", type = JAXBElement.class, required = false)
    protected JAXBElement<String> transactionDate;

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
     * Gets the value of the sourceAccountNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSourceAccountNo() {
        return sourceAccountNo;
    }

    /**
     * Sets the value of the sourceAccountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSourceAccountNo(JAXBElement<String> value) {
        this.sourceAccountNo = value;
    }

    /**
     * Gets the value of the sourceAccountCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSourceAccountCurrency() {
        return sourceAccountCurrency;
    }

    /**
     * Sets the value of the sourceAccountCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSourceAccountCurrency(JAXBElement<String> value) {
        this.sourceAccountCurrency = value;
    }

    /**
     * Gets the value of the destinationBankCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDestinationBankCode() {
        return destinationBankCode;
    }

    /**
     * Sets the value of the destinationBankCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDestinationBankCode(JAXBElement<String> value) {
        this.destinationBankCode = value;
    }

    /**
     * Gets the value of the destinationAccountNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDestinationAccountNo() {
        return destinationAccountNo;
    }

    /**
     * Sets the value of the destinationAccountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDestinationAccountNo(JAXBElement<String> value) {
        this.destinationAccountNo = value;
    }

    /**
     * Gets the value of the transactionAmount property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Sets the value of the transactionAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTransactionAmount(JAXBElement<String> value) {
        this.transactionAmount = value;
    }

    /**
     * Gets the value of the chargeCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getChargeCode() {
        return chargeCode;
    }

    /**
     * Sets the value of the chargeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setChargeCode(JAXBElement<String> value) {
        this.chargeCode = value;
    }

    /**
     * Gets the value of the chargeDescription property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getChargeDescription() {
        return chargeDescription;
    }

    /**
     * Sets the value of the chargeDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setChargeDescription(JAXBElement<String> value) {
        this.chargeDescription = value;
    }

    /**
     * Gets the value of the transactionDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the transactionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTransactionDate(JAXBElement<String> value) {
        this.transactionDate = value;
    }

}
