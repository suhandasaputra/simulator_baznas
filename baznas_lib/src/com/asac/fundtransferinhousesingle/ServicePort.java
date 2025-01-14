
package com.asac.fundtransferinhousesingle;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ServicePort", targetNamespace = "urn:FundTransferInHouseSingle")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServicePort {


    /**
     * 
     * @param serviceRequest
     * @return
     *     returns fundtransferinhousesingle.ServiceResponse
     */
    @WebMethod(operationName = "FundTransferInHouseSingle", action = "FundTransferInHouseSingle")
    @WebResult(name = "ServiceResponse", partName = "ServiceResponse")
    public ServiceResponse fundTransferInHouseSingle(
        @WebParam(name = "ServiceRequest", partName = "ServiceRequest")
        ServiceRequest serviceRequest);

}
