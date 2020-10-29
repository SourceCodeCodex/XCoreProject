package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpression;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/*
 * Rule 12.13 (advisory): The increment (++) and decrement (--) operators should not be mixed with other operators 
 * in an expression.
 */

@RelationBuilder
public class Rule12_13 implements IRelationBuilder<XCExpression,XCProject>{
	
	@Override
	public Group<XCExpression> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCExpression> exprB = new Group<>();
		Group<XCExpression> exp = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			exp = cu.binaryExpressionWithIncrAndDecrOperatorsGroup();
	        for(XCExpression cs : exp.getElements()) 
	        {
				exprB.add(cs);
	        }
		}
	
		return exprB;
	}

}