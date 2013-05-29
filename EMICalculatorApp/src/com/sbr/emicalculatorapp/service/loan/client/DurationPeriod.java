package com.sbr.emicalculatorapp.service.loan.client;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 * Adapter class for DurationPeriod type in webservice.
 * 
 * @author sbharathraja
 * 
 */
public class DurationPeriod implements KvmSerializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4081612726487338312L;

	private int duration;

	private TimePeriod timePeriod;

	public DurationPeriod() {

	}

	public DurationPeriod(int duration, TimePeriod timePeriod) {
		this.duration = duration;
		this.timePeriod = timePeriod;
	}

	@Override
	public Object getProperty(int index) {
		switch (index) {
		case 0:
			return duration;
		case 1:
			return timePeriod.toString();
		}
		return null;
	}

	@Override
	public int getPropertyCount() {
		// total instance member count...
		return 2;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		// set the information about the each property(instance members)
		switch (index) {
		case 0:
			info.type = PropertyInfo.INTEGER_CLASS;
			info.name = "duration";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "timePeriod";
			break;
		}
	}

	@Override
	public void setProperty(int index, Object propertyValue) {
		if (propertyValue == null) {
			return;
		}
		if (!(propertyValue instanceof Integer)
				&& !(propertyValue instanceof TimePeriod)) {
			return;
		}

		// setting the value to the property
		switch (index) {
		case 0:
			duration = (Integer) propertyValue;
			break;
		case 1:
			timePeriod = (TimePeriod) propertyValue;
			break;
		}

	}

}
