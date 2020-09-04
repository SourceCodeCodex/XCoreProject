package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCUnion;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 18.4: Unions shall not be used
 */


@RelationBuilder
public class Rule18_4 implements IRelationBuilder<XCUnion,XCProject>{
	
	@Override
	public Group<XCUnion> buildGroup(XCProject arg0) {

		Group<XCCompUnit> compU = new Group<>();
		Group<XCUnion> unions = new Group<>();
		Group<XCUnion> u = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			u = cu.unionGroup();
	        for(XCUnion cs: u.getElements()) 
	        {
				unions.add(cs);
	        }
		}
		
		return unions;
	}

}