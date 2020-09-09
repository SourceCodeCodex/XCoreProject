package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFieldDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTNode;
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
 * bit-field declared without appropriate type
 */

@RelationBuilder
public class BitFieldDeclaredWithoutAppropriateTypeGroup implements IRelationBuilder<XCDeclaration, XCCompUnit>{
	
	@Override
	public Group<XCDeclaration> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		Group<XCDeclaration> res = new Group<>();
		
		try {
			m = (ITranslationUnit)arg0.getUnderlyingObject();
			a = m.getAST();
		}
		catch(CoreException e) 
		{	
			e.printStackTrace();
		}
		
		ASTVisitor v = new ASTVisitor() {		
			
			public int visit(IASTDeclarator c) {
				
				if(c instanceof IASTFieldDeclarator) {
					IASTNode p,f;
					p = c.getParent();
					f = p.getChildren()[0];
					String type = f.getRawSignature();
					type = type.trim().replaceAll("[ ]{2,}", " ");
					if(!type.equals("unsigned int") && !type.equals("signed int"))
					{
						XCDeclaration d=Factory.getInstance().createXCDeclaration(p);
						res.add(d);
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