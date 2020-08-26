package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCFunctionCallExpression;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 20.11: The library functions abort, exit, getenv and system from library shall not be used
 */
@RelationBuilder
public class Rule20_11 implements IRelationBuilder<XCFunctionCallExpression,XCProject>{
	
	@Override
	public Group<XCFunctionCallExpression> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCFunctionCallExpression> functionCall = new Group<>();
		Group<XCFunctionCallExpression> f = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu : compU.getElements())
		{
			f = cu.abortExitGetenvSystemFunGroup();
	        for(XCFunctionCallExpression cs : f.getElements()) 
	        {
				functionCall.add(cs);
	        }
		}
	
		return functionCall;
	}

}