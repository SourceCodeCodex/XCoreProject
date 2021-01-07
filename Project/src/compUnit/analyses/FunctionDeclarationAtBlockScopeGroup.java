package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclarationStatement;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;


import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCDeclaration;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule8_6
 */

@RelationBuilder
public class FunctionDeclarationAtBlockScopeGroup implements IRelationBuilder<XCDeclaration, XCCompUnit>{
	
	@Override
	public Group<XCDeclaration> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		ASTVisitor v;
		Group<XCDeclaration> res = new Group<>();
		
		try {
			m = arg0.getUnderlyingObject();
			a = m.getAST();
		}
		catch(CoreException e)
		{
		e.printStackTrace();
		}
    
		v = new ASTVisitor() {
	
			public int visit(IASTDeclarator c) {
       
				if(c instanceof  IASTFunctionDeclarator && c.isPartOfTranslationUnitFile()) {
				    IASTNode p1 = c.getParent();
					IASTNode p2 = p1.getParent();
					if(p1 instanceof IASTSimpleDeclaration && p2 instanceof IASTDeclarationStatement)
					{
						XCDeclaration p = Factory.getInstance().createXCDeclaration(c);
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
