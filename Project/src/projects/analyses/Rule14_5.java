package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCStatement;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * The continue statement shall not be used
 */

@RelationBuilder
public class Rule14_5 implements IRelationBuilder<XCStatement,XCProject>{
	
	@Override
	public Group<XCStatement> buildGroup(XCProject arg0) {

		Group<XCCompUnit> compU = new Group<>();
		Group<XCStatement> continueS = new Group<>();
		Group<XCStatement> contS = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			contS = cu.continueStatementGroup();
			continueS.addAll(contS.getElements());
	        
		}
		
		return continueS;
	}

}
