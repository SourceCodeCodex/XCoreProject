package expression.functionCallExpression.analyses;

import org.eclipse.cdt.core.dom.ast.IASTFunctionCallExpression;

import project.metamodel.entity.XCFunctionCallExpression;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCFunctionCallExpression, HListEmpty>{
	
	@Override
	public Void performAction(XCFunctionCallExpression arg0, HListEmpty arg1) {	
		
		IASTFunctionCallExpression c = arg0.getUnderlyingObject();
		showInEditor(c);
		return null;
	}
}
