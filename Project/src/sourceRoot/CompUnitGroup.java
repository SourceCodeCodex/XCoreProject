package sourceRoot;


import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.ISourceRoot;
import org.eclipse.cdt.core.model.ITranslationUnit;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCSourceRoot;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class CompUnitGroup implements IRelationBuilder<XCCompUnit, XCSourceRoot>{

	@Override
	public Group<XCCompUnit> buildGroup(XCSourceRoot arg0){
		Group<XCCompUnit> res = new Group<>();
		try {
			ISourceRoot m=(ISourceRoot) arg0.getUnderlyingObject();
			ITranslationUnit[] all =  m.getTranslationUnits();
			for(ITranslationUnit aCDTCompUnit: all)
			{
				XCCompUnit c=Factory.getInstance().createXCCompUnit(aCDTCompUnit);
				res.add(c);
			}
		   }catch(CModelException e) {
			   e.printStackTrace();
		   }
	return res;
	}
}
