
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

    @XmlElement(name = "LoanDetails", namespace = "")
    private com.temenos.services.loan.core.LoanMetaData loanDetails;

    /**
     * 
     * @return
     *     returns LoanMetaData
     */
    public com.temenos.services.loan.core.LoanMetaData getLoanDetails() {
        return this.loanDetails;
    }

    /**
     * 
     * @param loanDetails
     *     the value for the loanDetails property
     */
    public void setLoanDetails(com.temenos.services.loan.core.LoanMetaData loanDetails) {
        this.loanDetails = loanDetails;
    }

}
