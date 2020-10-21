package statements.gotoStatement.analyses;

import org.eclipse.cdt.core.dom.ast.IASTGotoStatement;

import project.metamodel.entity.XCGotoStatement;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCGotoStatement, HListEmpty>{
	
	@Override
	public Void performAction(XCGotoStatement arg0, HListEmpty arg1) {	
		
		IASTGotoStatement c = arg0.getUnderlyingObject();
		showInEditor(c);
		return null;
	}
}
