package statements.analyses;



import org.eclipse.cdt.core.dom.ast.IASTStatement;

import project.metamodel.entity.XCStatement;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCStatement, HListEmpty>{
	
	@Override
	public Void performAction(XCStatement arg0, HListEmpty arg1) {	
		
		IASTStatement c = arg0.getUnderlyingObject();
		showInEditor(c);
		return null;
	}
}
