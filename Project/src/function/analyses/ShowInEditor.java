package function.analyses;

import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;


import project.metamodel.entity.XCFunction;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCFunction, HListEmpty>{
	
	@Override
	public Void performAction(XCFunction arg0, HListEmpty arg1) {	
		
	    IASTFunctionDeclarator c = arg0.getUnderlyingObject();
		showInEditor(c);
		return null;
	
	}
}
