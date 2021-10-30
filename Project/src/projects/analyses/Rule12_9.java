package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpression;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/*
 * Rule 12.9 (required): The unary minus operator shall not be applied to an expression whose underlying type is unsigned.
 */
@RelationBuilder
public class Rule12_9 implements IRelationBuilder<XCExpression,XCProject>{
	
	@Override
	public Group<XCExpression> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCExpression> exprU = new Group<>();
		Group<XCExpression> exp = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			exp = cu.unsignedExpressionWithUnaryMinusGroup();
			exprU.addAll(exp.getElements());
	      
		}
	
		return exprU;
	}

}