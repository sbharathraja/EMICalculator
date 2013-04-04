
package com.temenos.webservices.loan.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "calculateLoanResponse", namespace = "http://temenos.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateLoanResponse", namespace = "http://temenos.com")
public class CalculateLoanResponse {

    @XmlElement(name = "return", namespace = "")
    private com.temenos.services.loan.core.LoanMetaData _return;

    /**
     * 
     * @return
     *     returns LoanMetaData
     */
    public com.temenos.services.loan.core.LoanMetaData getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.temenos.services.loan.core.LoanMetaData _return) {
        this._return = _return;
    }

}
