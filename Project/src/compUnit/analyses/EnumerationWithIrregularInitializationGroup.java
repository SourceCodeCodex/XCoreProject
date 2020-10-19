package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTEnumerationSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCEnumeration;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;


/**
 * Rule9_3
 */

@RelationBuilder
public class EnumerationWithIrregularInitializationGroup implements IRelationBuilder<XCEnumeration, XCCompUnit>{
	
	@Override
	public Group<XCEnumeration> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		Group<XCEnumeration> res = new Group<>();
		
		try {
			m = arg0.getUnderlyingObject();
			a = m.getAST();
		}
		catch(CoreException e) 
		{	
			e.printStackTrace();
		}
		
		ASTVisitor v = new ASTVisitor() {			
			public int visit(IASTDeclSpecifier c) {
				if(c instanceof IASTEnumerationSpecifier) 
				{
					IASTNode children[] = c.getChildren();
					int k1 = 0, k2 = 0;
					if(children[1].getChildren().length == 2)
					{
						k1=1;
					}
				
					for(int i=2; i<children.length; i++)
					{
						if(children[i].getChildren().length >= 2)
							{
							k2++;
							}
					
					}
				
					if(k1 == 0 && k2 != 0)
					{
						XCEnumeration e = Factory.getInstance().createXCEnumeration((IASTEnumerationSpecifier)c);
						res.add(e);
					}
					else
					if(k1 == 1 && (k2 != children.length-2 && k2!=0))
					{	
						XCEnumeration e = Factory.getInstance().createXCEnumeration((IASTEnumerationSpecifier)c);
						res.add(e);
					}
				
					
				}
				
				
				return PROCESS_CONTINUE;
			
			}
		};
		
		v.	shouldVisitDeclSpecifiers = true;
		a.accept(v);
		
		return res;
	
	}
}
	
	



	

