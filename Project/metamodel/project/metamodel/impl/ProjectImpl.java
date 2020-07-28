package project.metamodel.impl;

import project.metamodel.entity.*;
import projects.ToString;

public class ProjectImpl implements Project {

	private java.lang.Object underlyingObj_;

	private static final ToString ToString_INSTANCE = new ToString();

	public ProjectImpl(java.lang.Object underlyingObj) {
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
		if (null == obj || !(obj instanceof ProjectImpl)) {
			return false;
		}
		ProjectImpl iObj = (ProjectImpl)obj;
		return underlyingObj_.equals(iObj.underlyingObj_);
	}

	public int hashCode() {
		return 97 * underlyingObj_.hashCode();
	}
}
