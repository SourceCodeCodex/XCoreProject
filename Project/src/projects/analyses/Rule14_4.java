package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCStatement;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * The goto statement shall not be used
 */

@RelationBuilder
public class Rule14_4 implements IRelationBuilder<XCStatement,XCProject>{
	
	@Override
	public Group<XCStatement> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCStatement> gotoS = new Group<>();
		Group<XCStatement> gtS = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			gtS = cu.gotoStatementGroup();
	        for(XCStatement cs:gtS.getElements()) 
	        {
				gotoS.add(cs);
	        }
		}
	
		return gotoS;
	}

}

