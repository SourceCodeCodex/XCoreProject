package statements.continueStatement.analyses;

import org.eclipse.cdt.core.dom.ast.IASTContinueStatement;

import project.metamodel.entity.XCContinueStatement;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCContinueStatement, HListEmpty>{
	
	@Override
	public Void performAction(XCContinueStatement arg0, HListEmpty arg1) {	
		
		IASTContinueStatement c = arg0.getUnderlyingObject();
		showInEditor(c);
	
		return null;
	
}
}