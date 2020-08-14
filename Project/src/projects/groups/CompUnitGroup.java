package projects.groups;

import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.ISourceRoot;
import org.eclipse.cdt.core.model.ITranslationUnit;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * compilation units group
 */

@RelationBuilder
public class CompUnitGroup implements IRelationBuilder<XCCompUnit, XCProject>{

	@Override
	public Group<XCCompUnit> buildGroup(XCProject arg0){
		
		Group<XCCompUnit> res = new Group<>();
		try {
				ICProject m = (ICProject) arg0.getUnderlyingObject();
				ISourceRoot[] all =  m.getAllSourceRoots();
				for(ISourceRoot aCDTSourceRoot: all)
				{
					ITranslationUnit[] allCompUnit = aCDTSourceRoot.getTranslationUnits();
					for(ITranslationUnit aCDTCompUnit: allCompUnit)
					{
						XCCompUnit c = Factory.getInstance().createXCCompUnit(aCDTCompUnit);
						res.add(c);
					}
			
				}
		   }catch(CModelException e)
			{
			   e.printStackTrace();
			}
	return res;
		
	}
}
