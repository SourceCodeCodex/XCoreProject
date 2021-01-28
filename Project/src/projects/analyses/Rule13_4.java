package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCStatement;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 13.4 (required): The controlling expression of a for statement shall not contain
 * any objects of floating type.
 */

@RelationBuilder
public class Rule13_4 implements IRelationBuilder<XCStatement,XCProject>{
	

	@Override
	public Group<XCStatement> buildGroup(XCProject arg0) {

		Group<XCCompUnit> compU = new Group<>();
		Group<XCStatement> forS = new Group<>();
		Group<XCStatement> x = new Group<>();
		
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			x = cu.forStatementWithFloatingObjectsInControllingExpressionGroup();
	        for(XCStatement cs:x.getElements()) 
	        {
				forS.add(cs);
	        }
		}
		
		return forS;
	}

}
