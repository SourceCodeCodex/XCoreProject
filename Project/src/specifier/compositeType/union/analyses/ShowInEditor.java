package specifier.compositeType.union.analyses;

import org.eclipse.cdt.core.dom.ast.IASTCompositeTypeSpecifier;


import project.metamodel.entity.XCUnion;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCUnion, HListEmpty>{
	
	@Override
	public Void performAction(XCUnion arg0, HListEmpty arg1) {	
		
		IASTCompositeTypeSpecifier c = arg0.getUnderlyingObject();
		showInEditor(c);
		return null;
	}
}