package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTFieldDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclSpecifier;
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
 * Rule 6_5
 */


@RelationBuilder
public class SignedBitFieldWith1BitLengthGroup implements IRelationBuilder<XCDeclaration, XCCompUnit>{
	
	@Override
	public Group<XCDeclaration> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		Group<XCDeclaration> res = new Group<>();
		
		try {
			m = arg0.getUnderlyingObject();
			a = m.getAST();
		}
		catch(CoreException e) 
		{	
			e.printStackTrace();
		}
		
		ASTVisitor v = new ASTVisitor() {		
			
			public int visit(IASTDeclarator c) {
				
				if(c instanceof IASTFieldDeclarator && c.isPartOfTranslationUnitFile())
				{
			    	IASTExpression size = ((IASTFieldDeclarator) c).getBitFieldSize();
					IASTName name = c.getName();
					int v = Integer.parseInt(size.getRawSignature());
					
					if((v == 1 && !name.getRawSignature().equals("")) || v == 0 )
					{   
						IASTNode p = c.getParent();
						if(p instanceof IASTSimpleDeclaration)
						{
							IASTSimpleDeclSpecifier f = null;
							IASTDeclSpecifier decl = ((IASTSimpleDeclaration) p).getDeclSpecifier();
							
							if(decl instanceof IASTSimpleDeclSpecifier)
							{
								f = (IASTSimpleDeclSpecifier) decl;
							
			          
								if(f.isSigned())
								{
									XCDeclaration d = Factory.getInstance().createXCDeclaration(c);
									res.add(d);
								}
							}
						}
					}
				}
				
				return PROCESS_CONTINUE;
			
			}
		};
		
		v.	shouldVisitDeclarators = true;
		a.accept(v);
		
		return res;
	
	}
}
