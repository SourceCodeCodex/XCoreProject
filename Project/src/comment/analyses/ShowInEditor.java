package comment.analyses;
import org.eclipse.cdt.core.dom.ast.IASTComment;
import project.metamodel.entity.XCComment;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor extends action.ShowInEditor implements IActionPerformer<Void, XCComment, HListEmpty>{
	
	@Override
	public Void performAction(XCComment arg0, HListEmpty arg1) {	
	
		IASTComment c= arg0.getUnderlyingObject();
		showInEditor(c);
		return null;
		
	}
}
