package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpression;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/*
 * Rule 12.7 (required): Bitwise operators shall not be applied to operands whose
 *      underlying type is signed.
 */

@RelationBuilder
public class Rule12_7 implements IRelationBuilder<XCExpression,XCProject>{
	
	@Override
	public Group<XCExpression> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCExpression> exprU = new Group<>();
		Group<XCExpression> exp = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			exp = cu.bitwiseOperatorsAppliedToSignedOperandsGroup();
			exprU.addAll(exp.getElements());
	   
		}
	
		return exprU;
	}

}