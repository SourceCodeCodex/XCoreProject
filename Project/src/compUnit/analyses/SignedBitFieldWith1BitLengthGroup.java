package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFieldDeclarator;
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
				
				if(c instanceof IASTFieldDeclarator && c.isPartOfTranslationUnitFile()) {
					IASTNode children[],p,f;
					children = c.getChildren();
					if( children[1].getRawSignature().equals("1") && !children[0].getRawSignature().equals("") )
					{
						p = c.getParent();
						f = p.getChildren()[0];
						String type = f.getRawSignature();
					
						if(type.indexOf("unsigned") == -1)
						{
							XCDeclaration d = Factory.getInstance().createXCDeclaration((IASTSimpleDeclaration)p);
							res.add(d);
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
