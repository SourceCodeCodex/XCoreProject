package expression.expressionList.analyses;

import org.eclipse.cdt.core.dom.ast.IASTExpressionList;

import project.metamodel.entity.XCExpressionList;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCExpressionList, HListEmpty>{
	
	@Override
	public Void performAction(XCExpressionList arg0, HListEmpty arg1) {	
		
		IASTExpressionList c = arg0.getUnderlyingObject();
		showInEditor(c);
		return null;
	}
}

