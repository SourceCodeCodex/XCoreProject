package projects.analyses;
import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCStatement;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
/**
 * Rule 14.9 (required): An if (expression) construct shall be followed by a compound statement. The else keyword shall be followed by either a
 * compound statement, or another if statement.
 */
@RelationBuilder
public class Rule14_9 implements IRelationBuilder<XCStatement,XCProject>{
	
	@Override
	public Group<XCStatement> buildGroup(XCProject arg0) {
		
		Group<XCCompUnit> compU = new Group<>();
		Group<XCStatement> statement = new Group<>();
		Group<XCStatement> s = new Group<>();
	
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements()) 
		{
			s = cu.ifWithoutCompoundStatementGroup();
			statement.addAll(s.getElements());
			
		}

		
		return statement;
	}
}
