
package com.sbr.soap.services.loan.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sbr.services.loan.core.DurationPeriod;

@XmlRootElement(name = "calculateInterestRate", namespace = "http://loan.services.sbr.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateInterestRate", namespace = "http://loan.services.sbr.com", propOrder = {
    "principalAmount",
    "durationPeriod",
    "totalInterestAmountPayable"
})
public class CalculateInterestRate {

    @XmlElement(name = "principalAmount", namespace = "")
    private double principalAmount;
    @XmlElement(name = "DurationPeriod", namespace = "")
    private DurationPeriod durationPeriod;
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
