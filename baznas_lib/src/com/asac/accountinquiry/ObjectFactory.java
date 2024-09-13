
package com.asac.accountinquiry;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the accountinquiry package. 
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

    private final static QName _ServiceResponseAccountStatus_QNAME = new QName("", "accountStatus");
    private final static QName _ServiceResponseResponseDesc_QNAME = new QName("", "responseDesc");
    private final static QName _ServiceResponseBalance_QNAME = new QName("", "balance");
    private final static QName _ServiceResponseAccountName_QNAME = new QName("", "accountName");
    private final static QName _ServiceResponseCurrency_QNAME = new QName("", "currency");
    private final static QName _ServiceResponseBalanceSign_QNAME = new QName("", "balanceSign");
    private final static QName _ServiceResponseProductType_QNAME = new QName("", "productType");
    private final static QName _ServiceRequestBankCode_QNAME = new QName("", "bankCode");
    private final static QName _ServiceRequestMessageId_QNAME = new QName("", "messageId");
    private final static QName _ServiceRequestAccountCurrency_QNAME = new QName("", "accountCurrency");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: accountinquiry
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
    @XmlElementDecl(namespace = "", name = "accountStatus", scope = ServiceResponse.class)
    public JAXBElement<String> createServiceResponseAccountStatus(String value) {
        return new JAXBElement<String>(_ServiceResponseAccountStatus_QNAME, String.class, ServiceResponse.class, value);
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
    @XmlElementDecl(namespace = "", name = "balance", scope = ServiceResponse.class)
    public JAXBElement<String> createServiceResponseBalance(String value) {
        return new JAXBElement<String>(_ServiceResponseBalance_QNAME, String.class, ServiceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "accountName", scope = ServiceResponse.class)
    public JAXBElement<String> createServiceResponseAccountName(String value) {
        return new JAXBElement<String>(_ServiceResponseAccountName_QNAME, String.class, ServiceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "currency", scope = ServiceResponse.class)
    public JAXBElement<String> createServiceResponseCurrency(String value) {
        return new JAXBElement<String>(_ServiceResponseCurrency_QNAME, String.class, ServiceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "balanceSign", scope = ServiceResponse.class)
    public JAXBElement<String> createServiceResponseBalanceSign(String value) {
        return new JAXBElement<String>(_ServiceResponseBalanceSign_QNAME, String.class, ServiceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "productType", scope = ServiceResponse.class)
    public JAXBElement<String> createServiceResponseProductType(String value) {
        return new JAXBElement<String>(_ServiceResponseProductType_QNAME, String.class, ServiceResponse.class, value);
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
    @XmlElementDecl(namespace = "", name = "messageId", scope = ServiceRequest.class)
    public JAXBElement<String> createServiceRequestMessageId(String value) {
        return new JAXBElement<String>(_ServiceRequestMessageId_QNAME, String.class, ServiceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "accountCurrency", scope = ServiceRequest.class)
    public JAXBElement<String> createServiceRequestAccountCurrency(String value) {
        return new JAXBElement<String>(_ServiceRequestAccountCurrency_QNAME, String.class, ServiceRequest.class, value);
    }

}
