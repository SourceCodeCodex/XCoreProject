package expression.unaryExpression.analyses;

import org.eclipse.cdt.core.dom.ast.IASTImageLocation;
import org.eclipse.cdt.core.dom.ast.IASTUnaryExpression;
import org.eclipse.cdt.internal.core.dom.parser.ASTNode;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditor;

import project.metamodel.entity.XCUnaryExpression;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, XCUnaryExpression, HListEmpty>{
	
	@Override
	public Void performAction(XCUnaryExpression arg0, HListEmpty arg1) {	
		
		try 
		{
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IASTUnaryExpression c = arg0.getUnderlyingObject();
			IPath path = new Path(c.getContainingFilename());
			IFile file = FileBuffers.getWorkspaceFileAtLocation(path);
			ITextEditor editor = (ITextEditor) IDE.openEditor(page, file);
			IASTImageLocation l = ((ASTNode) c).getImageLocation();
			editor.selectAndReveal(l.getNodeOffset(), ((ASTNode) c).getLength());
		}
		catch( PartInitException e)
		{
			e.printStackTrace();	
		}
	
		return null;
	}
}