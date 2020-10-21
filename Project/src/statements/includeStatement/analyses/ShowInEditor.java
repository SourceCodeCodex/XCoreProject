package statements.includeStatement.analyses;


import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIncludeStatement;
import project.metamodel.entity.XCIncludeStatement;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCIncludeStatement, HListEmpty>{
	
	@Override
	public Void performAction(XCIncludeStatement arg0, HListEmpty arg1) {	
		
		IASTPreprocessorIncludeStatement c = arg0.getUnderlyingObject();
		showInEditor(c);
		return null;
	}
}

