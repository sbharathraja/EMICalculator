
package com.temenos.webservices.loan.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "calculateLoan", namespace = "http://temenos.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateLoan", namespace = "http://temenos.com", propOrder = {
    "loanAmount",
    "loanType",
    "durationPeriod"
})
public class CalculateLoan {

    @XmlElement(name = "loanAmount", namespace = "")
    private double loanAmount;
    @XmlElement(name = "loanType", namespace = "")
    private com.temenos.services.loan.core.LoanType loanType;
    @XmlElement(name = "durationPeriod", namespace = "")
    private com.temenos.services.loan.core.DurationPeriods durationPeriod;

    /**
     * 
     * @return
     *     returns double
     */
    public double getLoanAmount() {
        return this.loanAmount;
    }

    /**
     * 
     * @param loanAmount
     *     the value for the loanAmount property
     */
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * 
     * @return
     *     returns LoanType
     */
    public com.temenos.services.loan.core.LoanType getLoanType() {
        return this.loanType;
    }

    /**
     * 
     * @param loanType
     *     the value for the loanType property
     */
    public void setLoanType(com.temenos.services.loan.core.LoanType loanType) {
        this.loanType = loanType;
    }

    /**
     * 
     * @return
     *     returns DurationPeriods
     */
    public com.temenos.services.loan.core.DurationPeriods getDurationPeriod() {
        return this.durationPeriod;
    }

    /**
     * 
     * @param durationPeriod
     *     the value for the durationPeriod property
     */
    public void setDurationPeriod(com.temenos.services.loan.core.DurationPeriods durationPeriod) {
        this.durationPeriod = durationPeriod;
    }

}
