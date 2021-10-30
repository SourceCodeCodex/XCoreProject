package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCSpecifier;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 18.4: Unions shall not be used
 */


@RelationBuilder
public class Rule18_4 implements IRelationBuilder<XCSpecifier,XCProject>{
	
	@Override
	public Group<XCSpecifier> buildGroup(XCProject arg0) {

		Group<XCCompUnit> compU = new Group<>();
		Group<XCSpecifier> unions = new Group<>();
		Group<XCSpecifier> u = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			u = cu.unionGroup();
			unions.addAll(u.getElements());
	      
		}
		
		return unions;
	}

}