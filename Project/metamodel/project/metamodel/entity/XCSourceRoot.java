package project.metamodel.entity;

public interface XCSourceRoot extends ro.lrg.xcore.metametamodel.XEntity {

	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String toString();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<XCCompUnit> compUnitGroup();

	java.lang.Object getUnderlyingObject();
}
