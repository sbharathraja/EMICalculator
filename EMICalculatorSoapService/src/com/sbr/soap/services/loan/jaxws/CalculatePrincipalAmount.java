
package com.sbr.soap.services.loan.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sbr.services.loan.core.DurationPeriod;
import com.sbr.services.loan.core.InterestRate;

@XmlRootElement(name = "calculatePrincipalAmount", namespace = "http://loan.services.sbr.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculatePrincipalAmount", namespace = "http://loan.services.sbr.com", propOrder = {
    "totalInterestAmountPayable",
    "durationPeriod",
    "interestRate"
})
public class CalculatePrincipalAmount {

    @XmlElement(name = "totalInterestAmountPayable", namespace = "")
    private double totalInterestAmountPayable;
    @XmlElement(name = "DurationPeriod", namespace = "")
    private DurationPeriod durationPeriod;
    @XmlElement(name = "InterestRate", namespace = "")
    private InterestRate interestRate;

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

}
