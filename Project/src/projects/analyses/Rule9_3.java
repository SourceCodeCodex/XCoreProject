package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCSpecifier;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 9.3 (required): In an enumerator list, the “=” construct shall not be used to explicitly initialise 
 * members other than the first,unless all items are explicitly initialised.
 */

@RelationBuilder
public class Rule9_3 implements IRelationBuilder<XCSpecifier,XCProject>{
	
	@Override
	public Group<XCSpecifier> buildGroup(XCProject arg0) {
		
		Group<XCCompUnit> compU = new Group<>();
		Group<XCSpecifier> enumeration = new Group<>();
		Group<XCSpecifier> e = new Group<>();
	
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements()) 
		{
			e = cu.enumerationWithIrregularInitializationGroup();
			for(XCSpecifier cs:e.getElements())
			{
				enumeration.add(cs);
			}
		}

		
		return enumeration;
	}
}