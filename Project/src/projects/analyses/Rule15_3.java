package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCStatement;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;


/**
 * Rule 15.3: The final clause of a switch statement shall be the default clause
 */

@RelationBuilder
public class Rule15_3 implements IRelationBuilder<XCStatement,XCProject>{
	
	@Override
	public Group<XCStatement> buildGroup(XCProject arg0) {
		
		Group<XCCompUnit> compU = new Group<>();
		Group<XCStatement> switchS = new Group<>();
		Group<XCStatement> s = new Group<>();
	
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements()) 
		{
			s = cu.switchStatementWithoutDefaultClauseGroup();
			for(XCStatement cs:s.getElements())
			{
				switchS.add(cs);
			}
		}

		
		return switchS;
	}
}
