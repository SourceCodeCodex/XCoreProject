package project.metamodel.impl;

import project.metamodel.entity.*;
import functions.Rule16_5;
import functions.ToString;
import functions.Rule14_5;
import functions.Rule16_1;

public class FunctionImpl implements Function {

	private java.lang.Object underlyingObj_;

	private static final Rule16_5 Rule16_5_INSTANCE = new Rule16_5();
	private static final ToString ToString_INSTANCE = new ToString();
	private static final Rule14_5 Rule14_5_INSTANCE = new Rule14_5();
	private static final Rule16_1 Rule16_1_INSTANCE = new Rule16_1();

	public FunctionImpl(java.lang.Object underlyingObj) {
		underlyingObj_ = underlyingObj;
	}

	@Override
	public java.lang.Object getUnderlyingObject() {
		return underlyingObj_;
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String rule16_5() {
		return Rule16_5_INSTANCE.compute(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String toString() {
		return ToString_INSTANCE.compute(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String rule14_5() {
		return Rule14_5_INSTANCE.compute(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String rule16_1() {
		return Rule16_1_INSTANCE.compute(this);
	}

	public boolean equals(Object obj) {
		if (null == obj || !(obj instanceof FunctionImpl)) {
			return false;
		}
		FunctionImpl iObj = (FunctionImpl)obj;
		return underlyingObj_.equals(iObj.underlyingObj_);
	}

	public int hashCode() {
		return 97 * underlyingObj_.hashCode();
	}
}
