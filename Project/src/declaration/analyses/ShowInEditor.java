package declaration.analyses;

import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import project.metamodel.entity.XCDeclaration;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCDeclaration, HListEmpty>{
	
	@Override
	public Void performAction(XCDeclaration arg0, HListEmpty arg1) {	
	
	     IASTDeclarator c = arg0.getUnderlyingObject();
		 showInEditor(c);
	
		 return null;
	}
}
