package com.sbr.emicalculatorapp.service.loan.client;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 * Adapter class to map with Emi in loan service.
 * 
 * @author sbharathraja
 * 
 */
public class Emi implements KvmSerializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3768687019896715164L;
	private double emi;

	@Override
	public Object getProperty(int index) {
		switch (index) {
		case 0:
			return emi;
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
			info.type = Double.class;
			info.name = "emi";
			break;
		}
	}

	@Override
	public void setProperty(int index, Object propertyValue) {
		if (propertyValue == null) {
			return;
		}
		if (!(propertyValue instanceof Double)) {
			return;
		}
		switch (index) {
		case 0:
			emi = (Double) propertyValue;
			break;
		}

	}

}
