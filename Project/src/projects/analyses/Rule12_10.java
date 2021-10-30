package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpression;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 12.10: The comma operator shall not be used
 */


@RelationBuilder
public class Rule12_10 implements IRelationBuilder<XCExpression,XCProject>{
	
	@Override
	public Group<XCExpression> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCExpression> exprL = new Group<>();
		Group<XCExpression> exp = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			exp = cu.expressionListGroup();
			exprL.addAll(exp.getElements());
			
		}
	
		return exprL;
	}

}

