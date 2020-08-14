package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCContinueStatement;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.PropertyComputer;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * The continue statement shall not be used
 */

@RelationBuilder
public class Rule14_5 implements IRelationBuilder<XCContinueStatement,XCProject>{
	
	@Override
	public Group<XCContinueStatement> buildGroup(XCProject arg0) {

		Group<XCCompUnit> compU = new Group<>();
		Group<XCContinueStatement> continueS = new Group<>();
		Group<XCContinueStatement> contS = new Group<>();
		
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			contS = cu.continueStatementGroup();
	        for(XCContinueStatement cs:contS.getElements()) 
	        {
				continueS.add(cs);
	        }
		}
		
		return continueS;
	}

}
