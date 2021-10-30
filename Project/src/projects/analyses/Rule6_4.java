package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCDeclaration;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 6.4 (required): Bit fields shall only be defined to be of type unsigned int or signed int.
 */

@RelationBuilder
public class Rule6_4 implements IRelationBuilder<XCDeclaration,XCProject>{
	
	@Override
	public Group<XCDeclaration> buildGroup(XCProject arg0) {
	
		Group<XCCompUnit> compU = new Group<>();
		Group<XCDeclaration> decl = new Group<>();
		Group<XCDeclaration> f = new Group<>();
		
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			f = cu.bitFieldDeclaredWithoutAppropriateTypeGroup();
			decl.addAll(f.getElements());
			
		}
	
		
		return decl;
	}

}
