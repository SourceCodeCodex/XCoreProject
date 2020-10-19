package compUnit.analyses;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import project.metamodel.entity.XCCompUnit;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowEditor implements IActionPerformer<Void, XCCompUnit, HListEmpty>{
	
	@Override
	public Void performAction(XCCompUnit arg0, HListEmpty arg1) {	
		
		try 
		{
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			ITranslationUnit c = arg0.getUnderlyingObject();
			IPath path = c.getPath();
			IFile file = FileBuffers.getWorkspaceFileAtLocation(path);
		    IDE.openEditor(page, file);
		}catch( PartInitException e)
		{
			e.printStackTrace();	
		}
	
		return null;
	}
}

