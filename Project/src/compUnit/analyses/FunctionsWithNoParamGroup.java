package compUnit.analyses;


import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.IFunction;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCFunction;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * group of functions with no parameters
 */

@RelationBuilder
public class FunctionsWithNoParamGroup implements IRelationBuilder<XCFunction, XCCompUnit>{
	
	@Override
	public Group<XCFunction> buildGroup(XCCompUnit arg0) {
	
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		ASTVisitor v;
		Group<XCFunction> res = new Group<>();
	
		try {
			m = (ITranslationUnit)arg0.getUnderlyingObject();
			a = m.getAST();
		}
		catch(CoreException e)
		{
			e.printStackTrace();
		}
		v=new ASTVisitor() {
	
			public int visit(IASTDeclarator c) {

				if(c instanceof  IASTFunctionDeclarator) {
					IASTNode children[] = c.getChildren();
					
					if(children.length == 1)
					{  
						XCFunction p = Factory.getInstance().createXCFunction(c);
						res.add(p);
					}
				}
				
		
				return 3;
			}
	

		};
		v.shouldVisitDeclarators = true;
		a.accept(v);
		return res;

	}
}




