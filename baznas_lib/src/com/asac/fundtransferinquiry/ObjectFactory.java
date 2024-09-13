
package com.asac.fundtransferinquiry;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fundtransferinquiry package. 
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

    private final static QName _ServiceResponseDestinationAccountType_QNAME = new QName("", "destinationAccountType");
    private final static QName _ServiceResponseResponseDesc_QNAME = new QName("", "responseDesc");
    private final static QName _ServiceResponseDestinationBankCode_QNAME = new QName("", "destinationBankCode");
    private final static QName _ServiceResponseDestinationAccountName_QNAME = new QName("", "destinationAccountName");
    private final static QName _ServiceRequestBankCode_QNAME = new QName("", "bankCode");
    private final static QName _ServiceRequestDestinationAccountNo_QNAME = new QName("", "destinationAccountNo");
    private final static QName _ServiceRequestTransactionAmount_QNAME = new QName("", "transactionAmount");
    private final static QName _ServiceRequestMessageId_QNAME = new QName("", "messageId");
    private final static QName _ServiceRequestSourceAccountNo_QNAME = new QName("", "sourceAccountNo");
    private final static QName _ServiceRequestSourceAccountCurrency_QNAME = new QName("", "sourceAccountCurrency");
    private final static QName _ServiceRequestTransactionDate_QNAME = new QName("", "transactionDate");
    private final static QName _ServiceRequestChargeCode_QNAME = new QName("", "chargeCode");
    private final static QName _ServiceRequestChargeDescription_QNAME = new QName("", "chargeDescription");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fundtransferinquiry
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceResponse }
     * 
     */
    public ServiceResponse createServiceResponse() {
        return new ServiceResponse();
    }

    /**
     * Create an instance of {@link ServiceRequest }
     * 
     */
    public ServiceRequest createServiceRequest() {
        return new ServiceRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "destinationAccountType", scope = ServiceResponse.class)
    public JAXBElement<String> createServiceResponseDestinationAccountType(String value) {
        return new JAXBElement<String>(_ServiceResponseDestinationAccountType_QNAME, String.class, ServiceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "responseDesc", scope = ServiceResponse.class)
    public JAXBElement<String> createServiceResponseResponseDesc(String value) {
        return new JAXBElement<String>(_ServiceResponseResponseDesc_QNAME, String.class, ServiceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "destinationBankCode", scope = ServiceResponse.class)
    public JAXBElement<String> createServiceResponseDestinationBankCode(String value) {
        return new JAXBElement<String>(_ServiceResponseDestinationBankCode_QNAME, String.class, ServiceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "destinationAccountName", scope = ServiceResponse.class)
    public JAXBElement<String> createServiceResponseDestinationAccountName(String value) {
        return new JAXBElement<String>(_ServiceResponseDestinationAccountName_QNAME, String.class, ServiceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "bankCode", scope = ServiceRequest.class)
    public JAXBElement<String> createServiceRequestBankCode(String value) {
        return new JAXBElement<String>(_ServiceRequestBankCode_QNAME, String.class, ServiceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "destinationBankCode", scope = ServiceRequest.class)
    public JAXBElement<String> createServiceRequestDestinationBankCode(String value) {
        return new JAXBElement<String>(_ServiceResponseDestinationBankCode_QNAME, String.class, ServiceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "destinationAccountNo", scope = ServiceRequest.class)
    public JAXBElement<String> createServiceRequestDestinationAccountNo(String value) {
        return new JAXBElement<String>(_ServiceRequestDestinationAccountNo_QNAME, String.class, ServiceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "transactionAmount", scope = ServiceRequest.class)
    public JAXBElement<String> createServiceRequestTransactionAmount(String value) {
        return new JAXBElement<String>(_ServiceRequestTransactionAmount_QNAME, String.class, ServiceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "messageId", scope = ServiceRequest.class)
    public JAXBElement<String> createServiceRequestMessageId(String value) {
        return new JAXBElement<String>(_ServiceRequestMessageId_QNAME, String.class, ServiceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sourceAccountNo", scope = ServiceRequest.class)
    public JAXBElement<String> createServiceRequestSourceAccountNo(String value) {
        return new JAXBElement<String>(_ServiceRequestSourceAccountNo_QNAME, String.class, ServiceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sourceAccountCurrency", scope = ServiceRequest.class)
    public JAXBElement<String> createServiceRequestSourceAccountCurrency(String value) {
        return new JAXBElement<String>(_ServiceRequestSourceAccountCurrency_QNAME, String.class, ServiceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "transactionDate", scope = ServiceRequest.class)
    public JAXBElement<String> createServiceRequestTransactionDate(String value) {
        return new JAXBElement<String>(_ServiceRequestTransactionDate_QNAME, String.class, ServiceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "chargeCode", scope = ServiceRequest.class)
    public JAXBElement<String> createServiceRequestChargeCode(String value) {
        return new JAXBElement<String>(_ServiceRequestChargeCode_QNAME, String.class, ServiceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "chargeDescription", scope = ServiceRequest.class)
    public JAXBElement<String> createServiceRequestChargeDescription(String value) {
        return new JAXBElement<String>(_ServiceRequestChargeDescription_QNAME, String.class, ServiceRequest.class, value);
    }

}
