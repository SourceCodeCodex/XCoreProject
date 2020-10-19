package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCUnaryExpression;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/*
 * Rule 12.9 (required): The unary minus operator shall not be applied to an expression whose underlying type is unsigned.
 */
@RelationBuilder
public class Rule12_9 implements IRelationBuilder<XCUnaryExpression,XCProject>{
	
	@Override
	public Group<XCUnaryExpression> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCUnaryExpression> exprU = new Group<>();
		Group<XCUnaryExpression> exp = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			exp = cu.unsignedExpressionWithUnaryMinusGroup();
	        for(XCUnaryExpression cs : exp.getElements()) 
	        {
				exprU.add(cs);
	        }
		}
	
		return exprU;
	}

}