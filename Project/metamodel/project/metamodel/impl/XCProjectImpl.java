package project.metamodel.impl;

import project.metamodel.entity.*;
import projects.Rule14_8;
import projects.Rule16_5;
import projects.ToString;
import projects.Rule14_5;
import projects.Rule16_1;
import projects.SourceRootGroup;

public class XCProjectImpl implements XCProject {

	private java.lang.Object underlyingObj_;

	private static final Rule14_8 Rule14_8_INSTANCE = new Rule14_8();
	private static final Rule16_5 Rule16_5_INSTANCE = new Rule16_5();
	private static final ToString ToString_INSTANCE = new ToString();
	private static final Rule14_5 Rule14_5_INSTANCE = new Rule14_5();
	private static final Rule16_1 Rule16_1_INSTANCE = new Rule16_1();
	private static final SourceRootGroup SourceRootGroup_INSTANCE = new SourceRootGroup();

	public XCProjectImpl(java.lang.Object underlyingObj) {
		underlyingObj_ = underlyingObj;
	}

	@Override
	public java.lang.Object getUnderlyingObject() {
		return underlyingObj_;
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String rule14_8() {
		return Rule14_8_INSTANCE.compute(this);
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

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<XCSourceRoot> sourceRootGroup() {
		return SourceRootGroup_INSTANCE.buildGroup(this);
	}

	public boolean equals(Object obj) {
		if (null == obj || !(obj instanceof XCProjectImpl)) {
			return false;
		}
		XCProjectImpl iObj = (XCProjectImpl)obj;
		return underlyingObj_.equals(iObj.underlyingObj_);
	}

	public int hashCode() {
		return 97 * underlyingObj_.hashCode();
	}
}
