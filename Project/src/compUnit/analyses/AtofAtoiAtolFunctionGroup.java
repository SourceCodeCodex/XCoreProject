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

/**Rule20_10
 *  functions atof, atoi and atol from library <stdlib.h> 
 */

@RelationBuilder
public class AtofAtoiAtolFunctionGroup implements IRelationBuilder<XCExpression, XCCompUnit>{
	
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
				if(c instanceof IASTFunctionCallExpression)
				{ 
					String s = c.getRawSignature();
					int n = s.indexOf('(');
					if(n != -1) 
					{
						s = s.substring(0, n);
					
						if(s.equals("atof") || s.equals("atol") || s.equals("atoi") )
						{
							if(c.isPartOfTranslationUnitFile())
							{	XCExpression expr = Factory.getInstance().createXCExpression(c);
								res.add(expr);
							}
						}
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