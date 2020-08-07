package project.metamodel.impl;

import project.metamodel.entity.*;
import functionDeclarator.ToString;

public class XCFunctionDeclaratorImpl implements XCFunctionDeclarator {

	private java.lang.Object underlyingObj_;

	private static final ToString ToString_INSTANCE = new ToString();

	public XCFunctionDeclaratorImpl(java.lang.Object underlyingObj) {
		underlyingObj_ = underlyingObj;
	}

	@Override
	public java.lang.Object getUnderlyingObject() {
		return underlyingObj_;
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String toString() {
		return ToString_INSTANCE.compute(this);
	}

	public boolean equals(Object obj) {
		if (null == obj || !(obj instanceof XCFunctionDeclaratorImpl)) {
			return false;
		}
		XCFunctionDeclaratorImpl iObj = (XCFunctionDeclaratorImpl)obj;
		return underlyingObj_.equals(iObj.underlyingObj_);
	}

	public int hashCode() {
		return 97 * underlyingObj_.hashCode();
	}
}
