package projects.groups;

import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.IParent;
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
		ICProject m = arg0.getUnderlyingObject();
		res = getCompUnits(m);
	
	    return res;
		
	}
	
	private Group<XCCompUnit> getCompUnits(ICElement container){
		Group<XCCompUnit> res = new Group<XCCompUnit>();
		Group<XCCompUnit> r = new Group<XCCompUnit>();
		try {
		ICElement[] all = ((IParent) container).getChildren();
		
		for(ICElement a:all) 
		{
			if(a instanceof ITranslationUnit)
			{
				XCCompUnit c = Factory.getInstance().createXCCompUnit((ITranslationUnit)a);
				res.add(c);
			}
			else
			{
				r = getCompUnits(a);
			    for(XCCompUnit comp: r.getElements())
			    {
			    	res.add(comp);
			    }
			}
		}
		}catch(CModelException e)
		{
		   e.printStackTrace();
		}
		
		return res;
	}
}
