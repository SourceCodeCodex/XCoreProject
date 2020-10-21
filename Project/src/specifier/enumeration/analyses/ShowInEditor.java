package specifier.enumeration.analyses;

import org.eclipse.cdt.core.dom.ast.IASTEnumerationSpecifier;

import project.metamodel.entity.XCEnumeration;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCEnumeration, HListEmpty>{
	
	@Override
	public Void performAction(XCEnumeration arg0, HListEmpty arg1) {	
		
		IASTEnumerationSpecifier c = arg0.getUnderlyingObject();
		showInEditor(c);
		return null;
	}
}