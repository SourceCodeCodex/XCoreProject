package project.metamodel.impl;

import project.metamodel.entity.*;
import sourceRoot.ToString;
import sourceRoot.CompUnitGroup;

public class XCSourceRootImpl implements XCSourceRoot {

	private java.lang.Object underlyingObj_;

	private static final ToString ToString_INSTANCE = new ToString();
	private static final CompUnitGroup CompUnitGroup_INSTANCE = new CompUnitGroup();

	public XCSourceRootImpl(java.lang.Object underlyingObj) {
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

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<XCCompUnit> compUnitGroup() {
		return CompUnitGroup_INSTANCE.buildGroup(this);
	}

	public boolean equals(Object obj) {
		if (null == obj || !(obj instanceof XCSourceRootImpl)) {
			return false;
		}
		XCSourceRootImpl iObj = (XCSourceRootImpl)obj;
		return underlyingObj_.equals(iObj.underlyingObj_);
	}

	public int hashCode() {
		return 97 * underlyingObj_.hashCode();
	}
}
