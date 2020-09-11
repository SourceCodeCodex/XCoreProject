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
import project.metamodel.entity.XCFunction;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class FunctionDeclarationAtBlockScopeGroup implements IRelationBuilder<XCFunction, XCCompUnit>{
	
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
    
		v = new ASTVisitor() {
	
			public int visit(IASTDeclarator c) {
       
				if(c instanceof  IASTFunctionDeclarator) {
				IASTNode p1 = c.getParent();
				IASTNode p2 = p1.getParent();
				if(p1 instanceof IASTSimpleDeclaration && p2 instanceof IASTDeclarationStatement)
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
