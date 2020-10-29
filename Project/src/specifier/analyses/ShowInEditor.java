package specifier.analyses;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;

import project.metamodel.entity.XCSpecifier;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCSpecifier, HListEmpty>{
	
	@Override
	public Void performAction(XCSpecifier arg0, HListEmpty arg1) {	
		
		IASTDeclSpecifier c = arg0.getUnderlyingObject();
		showInEditor(c);
		return null;
	}
}