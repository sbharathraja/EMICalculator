package com.sbr.emicalculatorapp.service.loan.client;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 * Adapter class to map with EmiData Type in loan service.
 * 
 * @author sbharathraja
 * 
 */
public class EmiData implements KvmSerializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2332771603983965751L;

	private double loanAmount;

	private LoanType loanType;

	private DurationPeriod durationPeriod;

	private InterestRate interestRate;

	private Emi emi;

	private double totalInterestAmount;

	public EmiData() {

	}

	@Override
	public Object getProperty(int index) {
		switch (index) {
		case 0:
			return loanAmount;
		case 1:
			return loanType;
		case 2:
			return durationPeriod;
		case 3:
			return interestRate;
		case 4:
			return emi;
		case 5:
			return totalInterestAmount;
		}
		return null;
	}

	@Override
	public int getPropertyCount() {
		return 6;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		switch (index) {
		case 0:
			info.type = Double.class;
			info.name = "loanAmount";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "loanType";
			break;
		case 2:
			info.type = DurationPeriod.class;
			info.name = "durationPeriod";
			break;
		case 3:
			info.type = InterestRate.class;
			info.name = "interestRate";
			break;
		case 4:
			info.type = Emi.class;
			info.name = "emi";
			break;
		case 5:
			info.type = Double.class;
			info.name = "totalInterestAmount";
			break;
		}

	}

	@Override
	public void setProperty(int index, Object propertyValue) {
		if (propertyValue == null) {
			return;
		}
		switch (index) {
		case 0:
			if (!(propertyValue instanceof Double)) {
				return;
			}
			loanAmount = (Double) propertyValue;
			break;
		case 1:
			if (!(propertyValue instanceof LoanType)) {
				return;
			}
			loanType = (LoanType) propertyValue;
			break;
		case 2:
			if (!(propertyValue instanceof DurationPeriod)) {
				return;
			}
			durationPeriod = (DurationPeriod) propertyValue;
			break;
		case 3:
			if (!(propertyValue instanceof InterestRate)) {
				return;
			}
			interestRate = (InterestRate) propertyValue;
			break;
		case 4:
			if (!(propertyValue instanceof Emi)) {
				return;
			}
			emi = (Emi) propertyValue;
			break;
		case 5:
			if (!(propertyValue instanceof Double)) {
				return;
			}
			totalInterestAmount = (Double) propertyValue;
			break;
		}

	}

	public String getLoanAmount() {
		return String.valueOf(loanAmount);
	}

	public String getTotalInterestAmount() {
		return String.valueOf(totalInterestAmount);
	}

	public String getInterestRate() {
		return String.valueOf(interestRate.getProperty(0)) + " %";
	}

	public String getEmi() {
		long rate = Math.round((Double) emi.getProperty(0));
		return String.valueOf(rate);
	}

}
