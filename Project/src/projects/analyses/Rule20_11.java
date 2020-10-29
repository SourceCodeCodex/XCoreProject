package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpression;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 20.11: The library functions abort, exit, getenv and system from library shall not be used
 */
@RelationBuilder
public class Rule20_11 implements IRelationBuilder<XCExpression,XCProject>{
	
	@Override
	public Group<XCExpression> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCExpression> functionCall = new Group<>();
		Group<XCExpression> f = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu : compU.getElements())
		{
			f = cu.abortExitGetenvSystemFunGroup();
	        for(XCExpression cs : f.getElements()) 
	        {
				functionCall.add(cs);
	        }
		}
	
		return functionCall;
	}

}