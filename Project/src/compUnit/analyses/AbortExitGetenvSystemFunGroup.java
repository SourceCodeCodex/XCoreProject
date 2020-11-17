package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTFunctionCallExpression;
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
 * Rule20_11
 *  functions abort, exit, getenv and system from library <stdlib.h> 
 */

@RelationBuilder
public class AbortExitGetenvSystemFunGroup implements IRelationBuilder<XCExpression, XCCompUnit>{
	
	@Override
	public Group<XCExpression> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		Group<XCExpression> res = new Group<>();
		
		try {
			m = arg0.getUnderlyingObject();
			a = m.getAST();
			
		}catch(CoreException e) 
		{
			e.printStackTrace();
		}
		
		ASTVisitor v = new ASTVisitor() {			
			public int visit(IASTExpression c) {
				if(c instanceof IASTFunctionCallExpression && c.isPartOfTranslationUnitFile())
				{ 
					IASTExpression expr = ((IASTFunctionCallExpression) c).getFunctionNameExpression();
					String name = expr.getRawSignature();
                    
					if(name.equals("abort") || name.equals("exit") || name.equals("getenv") || name.equals("system"))
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
