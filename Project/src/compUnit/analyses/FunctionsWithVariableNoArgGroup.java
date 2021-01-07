package compUnit.analyses;


import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.internal.core.dom.parser.c.CASTFunctionDeclarator;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCDeclaration;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule16_1
 * group of functions with a variable number of arguments
 */

@RelationBuilder
public class FunctionsWithVariableNoArgGroup implements IRelationBuilder<XCDeclaration, XCCompUnit>{
	
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
       
				if(c instanceof  CASTFunctionDeclarator && c.isPartOfTranslationUnitFile())
				{
				
					//IASTNode parent = c.getParent();
					boolean varArg = ((CASTFunctionDeclarator) c).takesVarArgs();
					
					if(varArg && c.getRoleForName(c.getName())== IASTFunctionDeclarator.r_definition)
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




