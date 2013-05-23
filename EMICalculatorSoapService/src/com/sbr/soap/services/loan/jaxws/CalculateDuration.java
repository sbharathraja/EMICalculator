
package com.sbr.soap.services.loan.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sbr.services.loan.core.InterestRate;

@XmlRootElement(name = "calculateDuration", namespace = "http://loan.services.sbr.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateDuration", namespace = "http://loan.services.sbr.com", propOrder = {
    "principalAmount",
    "interestRate",
    "totalInterestAmountPayable"
})
public class CalculateDuration {

    @XmlElement(name = "principalAmount", namespace = "")
    private double principalAmount;
    @XmlElement(name = "InterestRate", namespace = "")
    private InterestRate interestRate;
    @XmlElement(name = "totalInterestAmountPayable", namespace = "")
    private double totalInterestAmountPayable;

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
     *     returns InterestRate
     */
    public InterestRate getInterestRate() {
        return this.interestRate;
    }

    /**
     * 
     * @param interestRate
     *     the value for the interestRate property
     */
    public void setInterestRate(InterestRate interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * 
     * @return
     *     returns double
     */
    public double getTotalInterestAmountPayable() {
        return this.totalInterestAmountPayable;
    }

    /**
     * 
     * @param totalInterestAmountPayable
     *     the value for the totalInterestAmountPayable property
     */
    public void setTotalInterestAmountPayable(double totalInterestAmountPayable) {
        this.totalInterestAmountPayable = totalInterestAmountPayable;
    }

}
