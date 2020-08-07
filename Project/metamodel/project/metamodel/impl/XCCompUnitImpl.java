package project.metamodel.impl;

import project.metamodel.entity.*;
import compUnit.ToString;
import compUnit.Print;
import compUnit.StatementWithoutEnclosedBodyGroup;
import compUnit.ContinueStatementGroup;
import compUnit.FunctionsWithNoParamGroup;
import compUnit.FunctionsWithVariableNoArgGroup;

public class XCCompUnitImpl implements XCCompUnit {

	private java.lang.Object underlyingObj_;

	private static final ToString ToString_INSTANCE = new ToString();
	private static final Print Print_INSTANCE = new Print();
	private static final StatementWithoutEnclosedBodyGroup StatementWithoutEnclosedBodyGroup_INSTANCE = new StatementWithoutEnclosedBodyGroup();
	private static final ContinueStatementGroup ContinueStatementGroup_INSTANCE = new ContinueStatementGroup();
	private static final FunctionsWithNoParamGroup FunctionsWithNoParamGroup_INSTANCE = new FunctionsWithNoParamGroup();
	private static final FunctionsWithVariableNoArgGroup FunctionsWithVariableNoArgGroup_INSTANCE = new FunctionsWithVariableNoArgGroup();

	public XCCompUnitImpl(java.lang.Object underlyingObj) {
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
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String print() {
		return Print_INSTANCE.compute(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<XCStatement> statementWithoutEnclosedBodyGroup() {
		return StatementWithoutEnclosedBodyGroup_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<XCContinueStatement> continueStatementGroup() {
		return ContinueStatementGroup_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<XCFunctionDeclarator> functionsWithNoParamGroup() {
		return FunctionsWithNoParamGroup_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<XCFunctionDeclarator> functionsWithVariableNoArgGroup() {
		return FunctionsWithVariableNoArgGroup_INSTANCE.buildGroup(this);
	}

	public boolean equals(Object obj) {
		if (null == obj || !(obj instanceof XCCompUnitImpl)) {
			return false;
		}
		XCCompUnitImpl iObj = (XCCompUnitImpl)obj;
		return underlyingObj_.equals(iObj.underlyingObj_);
	}

	public int hashCode() {
		return 97 * underlyingObj_.hashCode();
	}
}
