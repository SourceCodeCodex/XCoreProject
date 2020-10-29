package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCStatement;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * The statement forming the body of a switch, while, do … while or for statement shall be a compound statement
 */

@RelationBuilder
public class Rule14_8 implements IRelationBuilder<XCStatement,XCProject>{
	
	@Override
	public Group<XCStatement> buildGroup(XCProject arg0) {
		
		Group<XCCompUnit> compU = new Group<>();
		Group<XCStatement> statement = new Group<>();
		Group<XCStatement> s = new Group<>();
	
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements()) 
		{
			s = cu.statementWithoutEnclosedBodyGroup();
			for(XCStatement cs:s.getElements())
			{
				statement.add(cs);
			}
		}

		
		return statement;
	}
}

