
package com.sbr.soap.services.loan.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sbr.services.loan.core.DurationPeriod;
import com.sbr.services.loan.core.Emi;

@XmlRootElement(name = "calculateInterestRateFromEmi", namespace = "http://loan.services.sbr.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateInterestRateFromEmi", namespace = "http://loan.services.sbr.com", propOrder = {
    "principalAmount",
    "durationPeriod",
    "emi"
})
public class CalculateInterestRateFromEmi {

    @XmlElement(name = "principalAmount", namespace = "")
    private double principalAmount;
    @XmlElement(name = "DurationPeriod", namespace = "")
    private DurationPeriod durationPeriod;
    @XmlElement(name = "Emi", namespace = "")
    private Emi emi;

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
     *     returns Emi
     */
    public Emi getEmi() {
        return this.emi;
    }

    /**
     * 
     * @param emi
     *     the value for the emi property
     */
    public void setEmi(Emi emi) {
        this.emi = emi;
    }

}
