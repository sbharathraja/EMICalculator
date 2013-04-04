
package com.temenos.webservices.loan.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "calculateLoan", namespace = "http://temenos.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateLoan", namespace = "http://temenos.com", propOrder = {
    "arg0",
    "arg1",
    "arg2"
})
public class CalculateLoan {

    @XmlElement(name = "arg0", namespace = "")
    private double arg0;
    @XmlElement(name = "arg1", namespace = "")
    private com.temenos.services.loan.core.LoanType arg1;
    @XmlElement(name = "arg2", namespace = "")
    private com.temenos.services.loan.core.DurationPeriods arg2;

    /**
     * 
     * @return
     *     returns double
     */
    public double getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(double arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns LoanType
     */
    public com.temenos.services.loan.core.LoanType getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(com.temenos.services.loan.core.LoanType arg1) {
        this.arg1 = arg1;
    }

    /**
     * 
     * @return
     *     returns DurationPeriods
     */
    public com.temenos.services.loan.core.DurationPeriods getArg2() {
        return this.arg2;
    }

    /**
     * 
     * @param arg2
     *     the value for the arg2 property
     */
    public void setArg2(com.temenos.services.loan.core.DurationPeriods arg2) {
        this.arg2 = arg2;
    }

}
