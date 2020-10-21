package expression.binaryExpression.analyses;

import org.eclipse.cdt.core.dom.ast.IASTBinaryExpression;
import project.metamodel.entity.XCBinaryExpression;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCBinaryExpression, HListEmpty>{
	
	@Override
	public Void performAction(XCBinaryExpression arg0, HListEmpty arg1) {	
		IASTBinaryExpression c= arg0.getUnderlyingObject();
		showInEditor(c);
			
		return null;	
	}
	
}