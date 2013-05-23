
package com.sbr.soap.services.loan.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sbr.services.loan.core.DurationPeriod;

@XmlRootElement(name = "calculateDurationResponse", namespace = "http://loan.services.sbr.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateDurationResponse", namespace = "http://loan.services.sbr.com")
public class CalculateDurationResponse {

    @XmlElement(name = "DurationPeriod", namespace = "")
    private DurationPeriod durationPeriod;

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
