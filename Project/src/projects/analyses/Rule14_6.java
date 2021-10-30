package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCStatement;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 14.6 (required): For any iteration statement there shall be at most one break
 * statement used for loop termination.
 */

@RelationBuilder
public class Rule14_6 implements IRelationBuilder<XCStatement,XCProject>{
	
	@Override
	public Group<XCStatement> buildGroup(XCProject arg0) {

		Group<XCCompUnit> compU = new Group<>();
		Group<XCStatement> loopS = new Group<>();
		Group<XCStatement> x = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			x = cu.loopWithMoreThanABreakStatementGroup();
			loopS.addAll(x.getElements());
	        
		}
		
		return loopS;
	}

}
