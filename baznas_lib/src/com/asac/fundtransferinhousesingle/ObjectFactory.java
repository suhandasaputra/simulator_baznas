
package com.asac.fundtransferinhousesingle;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fundtransferinhousesingle package. 
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

    private final static QName _ServiceRequestBankCode_QNAME = new QName("", "bankCode");
    private final static QName _ServiceRequestMessageId_QNAME = new QName("", "messageId");
    private final static QName _ServiceResponseResponseDesc_QNAME = new QName("", "responseDesc");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fundtransferinhousesingle
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
    @XmlElementDecl(namespace = "", name = "responseDesc", scope = ServiceResponse.class)
    public JAXBElement<String> createServiceResponseResponseDesc(String value) {
        return new JAXBElement<String>(_ServiceResponseResponseDesc_QNAME, String.class, ServiceResponse.class, value);
    }

}
