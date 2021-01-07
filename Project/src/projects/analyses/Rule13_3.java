package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpression;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/*
 * Rule 13.3 (required): Floating-point expressions shall not be tested for equality or
 * inequality.
 */
@RelationBuilder
public class Rule13_3 implements IRelationBuilder<XCExpression,XCProject>{
	
	@Override
	public Group<XCExpression> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCExpression> exprU = new Group<>();
		Group<XCExpression> exp = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			exp = cu.floatingPointExpressionsTestedForEqualityInequalityGroup();
	        for(XCExpression cs : exp.getElements()) 
	        {
				exprU.add(cs);
	        }
		}
	
		return exprU;
	}

}