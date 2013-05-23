
package com.sbr.soap.services.loan.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sbr.services.loan.core.DurationPeriod;
import com.sbr.services.loan.core.LoanType;

@XmlRootElement(name = "calculateEmi", namespace = "http://loan.services.sbr.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateEmi", namespace = "http://loan.services.sbr.com", propOrder = {
    "principalAmount",
    "loanType",
    "durationPeriod"
})
public class CalculateEmi {

    @XmlElement(name = "principalAmount", namespace = "")
    private double principalAmount;
    @XmlElement(name = "LoanType", namespace = "")
    private LoanType loanType;
    @XmlElement(name = "DurationPeriod", namespace = "")
    private DurationPeriod durationPeriod;

    /**
     * 
     * @return
     *     returns double
     */
    public double getPrincipalAmount() {
        return this.principalAmount;
    }

    /**
     * 
     * @param principalAmount
     *     the value for the principalAmount property
     */
    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    /**
     * 
     * @return
     *     returns LoanType
     */
    public LoanType getLoanType() {
        return this.loanType;
    }

    /**
     * 
     * @param loanType
     *     the value for the loanType property
     */
    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    /**
     * 
     * @return
     *     returns DurationPeriod
     */
    public DurationPeriod getDurationPeriod() {
        return this.durationPeriod;
    }

    /**
     * 
     * @param durationPeriod
     *     the value for the durationPeriod property
     */
    public void setDurationPeriod(DurationPeriod durationPeriod) {
        this.durationPeriod = durationPeriod;
    }

}
