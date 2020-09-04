package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCEnumeration;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 9.3 (required): In an enumerator list, the “=” construct shall not be used to explicitly initialise 
 * members other than the first,unless all items are explicitly initialised.
 */

@RelationBuilder
public class Rule9_3 implements IRelationBuilder<XCEnumeration,XCProject>{
	
	@Override
	public Group<XCEnumeration> buildGroup(XCProject arg0) {
		
		Group<XCCompUnit> compU = new Group<>();
		Group<XCEnumeration> enumeration = new Group<>();
		Group<XCEnumeration> e = new Group<>();
	
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements()) 
		{
			e = cu.enumerationWithIrregularInitializationGroup();
			for(XCEnumeration cs:e.getElements())
			{
				enumeration.add(cs);
			}
		}

		
		return enumeration;
	}
}