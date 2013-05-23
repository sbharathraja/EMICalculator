
package com.sbr.soap.services.loan.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "calculatePrincipalAmountResponse", namespace = "http://loan.services.sbr.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculatePrincipalAmountResponse", namespace = "http://loan.services.sbr.com")
public class CalculatePrincipalAmountResponse {

    @XmlElement(name = "principalAmount", namespace = "")
    private double principalAmount;

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

}
