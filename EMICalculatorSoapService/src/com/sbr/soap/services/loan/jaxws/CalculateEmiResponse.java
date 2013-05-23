
package com.sbr.soap.services.loan.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sbr.services.loan.core.EmiData;

@XmlRootElement(name = "calculateEmiResponse", namespace = "http://loan.services.sbr.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateEmiResponse", namespace = "http://loan.services.sbr.com")
public class CalculateEmiResponse {

    @XmlElement(name = "EmiData", namespace = "")
    private EmiData emiData;

    /**
     * 
     * @return
     *     returns EmiData
     */
    public EmiData getEmiData() {
        return this.emiData;
    }

    /**
     * 
     * @param emiData
     *     the value for the emiData property
     */
    public void setEmiData(EmiData emiData) {
        this.emiData = emiData;
    }

}
