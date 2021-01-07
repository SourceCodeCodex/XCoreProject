package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTFunctionCallExpression;
import org.eclipse.cdt.core.dom.ast.IASTIdExpression;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;
import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpression;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 20.7 (required): The setjmp macro and the longjmp function shall not be used.
 */
@RelationBuilder
public class SetjmpLongjmpGroup implements IRelationBuilder<XCExpression, XCCompUnit>{
	
	@Override
	public Group<XCExpression> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		Group<XCExpression> res = new Group<>();
		
		try {
			m = arg0.getUnderlyingObject();
			a = m.getAST();
		} catch(CoreException e) {
			e.printStackTrace();
		}
		ASTVisitor v = new ASTVisitor() {			
			public int visit(IASTExpression c) {
				if(c instanceof IASTFunctionCallExpression && c.isPartOfTranslationUnitFile())
				{   String name = "";
				    IASTNode children[] = c.getChildren();
				    for(IASTNode l:children) 
				    {
				    	if(l instanceof IASTIdExpression)
				    	{
				    		name = ((IASTIdExpression) l).getName().toString();
				    		break;
				    	}
				    	
				    }
			    	
					if(name.equals("_setjmp") || name.equals("longjmp"))
					{
						XCExpression fCall = Factory.getInstance().createXCExpression(c);
						res.add(fCall);
					}					
				}
				
				return PROCESS_CONTINUE;
			}
		};
		v.shouldVisitExpressions = true;
		a.accept(v);
		
		return res;
		
	}
}
