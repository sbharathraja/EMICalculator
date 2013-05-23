
package com.sbr.soap.services.loan.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sbr.services.loan.core.InterestRate;

@XmlRootElement(name = "calculateInterestRateFromEmiResponse", namespace = "http://loan.services.sbr.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateInterestRateFromEmiResponse", namespace = "http://loan.services.sbr.com")
public class CalculateInterestRateFromEmiResponse {

    @XmlElement(name = "InterestRate", namespace = "")
    private InterestRate interestRate;

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
