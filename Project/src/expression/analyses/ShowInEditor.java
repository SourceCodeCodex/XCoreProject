package expression.analyses;

import org.eclipse.cdt.core.dom.ast.IASTExpression;
import project.metamodel.entity.XCExpression;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCExpression, HListEmpty>{
	
	@Override
	public Void performAction(XCExpression arg0, HListEmpty arg1) {	
		IASTExpression c= arg0.getUnderlyingObject();
		showInEditor(c);
			
		return null;	
	}
	
}
