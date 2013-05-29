package com.sbr.emicalculatorapp.service.loan.client;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 * Adapter class to map with Interest Rate in loan service.
 * 
 * @author sbharathraja
 * 
 */
public class InterestRate implements KvmSerializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5602659289554218625L;
	private float rate;

	@Override
	public Object getProperty(int index) {
		switch (index) {
		case 0:
			return rate;
		}
		return null;
	}

	@Override
	public int getPropertyCount() {
		return 1;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		switch (index) {
		case 0:
			info.type = Float.class;
			info.name = "rate";
			break;
		}

	}

	@Override
	public void setProperty(int index, Object propertyValue) {
		if (propertyValue == null) {
			return;
		}
		if (!(propertyValue instanceof Float)) {
			return;
		}
		switch (index) {
		case 0:
			rate = (Float) propertyValue;
			break;
		}
	}

}
