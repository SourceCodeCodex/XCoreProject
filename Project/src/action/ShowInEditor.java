package action;

import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTImageLocation;
import org.eclipse.cdt.core.dom.ast.IASTNode;
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

public abstract class ShowInEditor {
	
	public void showInEditor(IASTNode node) {
		
		try 
		{
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IPath path = new Path(node.getContainingFilename());
			IFile file = FileBuffers.getWorkspaceFileAtLocation(path);
			if(file != null) {
				ITextEditor editor = (ITextEditor) IDE.openEditor(page, file);
				IASTFileLocation l = node.getFileLocation();
				int lineNo = l.getNodeOffset();
				//editor.selectAndReveal(l.getNodeOffset(), ((ASTNode) node).getLength());
				editor.selectAndReveal(lineNo, l.getNodeLength());
			}
		}
		catch( PartInitException e)
		{
			e.printStackTrace();	
		}
		
	}

}
