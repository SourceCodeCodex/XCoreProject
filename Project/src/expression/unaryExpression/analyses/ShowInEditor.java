package expression.unaryExpression.analyses;
import org.eclipse.cdt.core.dom.ast.IASTUnaryExpression;

import project.metamodel.entity.XCUnaryExpression;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCUnaryExpression, HListEmpty>{
	
	@Override
	public Void performAction(XCUnaryExpression arg0, HListEmpty arg1) {	
		
		IASTUnaryExpression c = arg0.getUnderlyingObject();
		showInEditor(c);
		return null;
	}
}