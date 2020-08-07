package projects;

import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.ISourceRoot;

import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCSourceRoot;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class SourceRootGroup implements IRelationBuilder<XCSourceRoot, XCProject>{

	@Override
	public Group<XCSourceRoot> buildGroup(XCProject arg0){
		Group<XCSourceRoot> res = new Group<>();
		try {
			ICProject m=(ICProject) arg0.getUnderlyingObject();
			ISourceRoot[] all =  m.getAllSourceRoots();
			for(ISourceRoot aCDTSourceRoot: all)
			{
				XCSourceRoot s=Factory.getInstance().createXCSourceRoot(aCDTSourceRoot);
				res.add(s);
			}
		   }catch(CModelException e) {
			   e.printStackTrace();
		   }
	return res;
	}
}
