
package com.asac.accountinquiry;

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
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "AccountInquiryService", targetNamespace = "urn:AccountInquiry", wsdlLocation = "file:/D:/PROJECT/Pak_Indra/workspace/ASAC/src/conf/xml-resources/web-services/AccountInquiry/wsdl/AccountInquiry.wsdl")
public class AccountInquiryService
    extends Service
{

    private final static URL ACCOUNTINQUIRYSERVICE_WSDL_LOCATION;
    private final static WebServiceException ACCOUNTINQUIRYSERVICE_EXCEPTION;
    private final static QName ACCOUNTINQUIRYSERVICE_QNAME = new QName("urn:AccountInquiry", "AccountInquiryService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/D:/PROJECT/Pak_Indra/workspace/ASAC/src/conf/xml-resources/web-services/AccountInquiry/wsdl/AccountInquiry.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ACCOUNTINQUIRYSERVICE_WSDL_LOCATION = url;
        ACCOUNTINQUIRYSERVICE_EXCEPTION = e;
    }

    public AccountInquiryService() {
        super(__getWsdlLocation(), ACCOUNTINQUIRYSERVICE_QNAME);
    }

    public AccountInquiryService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns ServicePort
     */
    @WebEndpoint(name = "AccountInquiryPort")
    public ServicePort getAccountInquiryPort() {
        return super.getPort(new QName("urn:AccountInquiry", "AccountInquiryPort"), ServicePort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServicePort
     */
    @WebEndpoint(name = "AccountInquiryPort")
    public ServicePort getAccountInquiryPort(WebServiceFeature... features) {
        return super.getPort(new QName("urn:AccountInquiry", "AccountInquiryPort"), ServicePort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ACCOUNTINQUIRYSERVICE_EXCEPTION!= null) {
            throw ACCOUNTINQUIRYSERVICE_EXCEPTION;
        }
        return ACCOUNTINQUIRYSERVICE_WSDL_LOCATION;
    }

}
