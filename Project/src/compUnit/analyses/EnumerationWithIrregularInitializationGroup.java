package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTEnumerationSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCSpecifier;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;


/**
 * Rule9_3
 */

@RelationBuilder
public class EnumerationWithIrregularInitializationGroup implements IRelationBuilder<XCSpecifier, XCCompUnit>{
	
	@Override
	public Group<XCSpecifier> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		Group<XCSpecifier> res = new Group<>();
		
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
				if(c instanceof IASTEnumerationSpecifier && c.isPartOfTranslationUnitFile())
				{   int k1 = 0, k2 = 0;
					IASTEnumerationSpecifier.IASTEnumerator[] enumerators = ((IASTEnumerationSpecifier) c).getEnumerators();
					
					if(enumerators[0].getValue() != null)
					{
					   k1 = 1;	
					}
					for(int i=1; i<enumerators.length; i++)
					{
						IASTExpression e = enumerators[i].getValue();
						if(e != null)
						{
							k2++;
						}
					}
						
					if(k1 == 0 && k2 != 0)
					{
						XCSpecifier e = Factory.getInstance().createXCSpecifier(c);
						res.add(e);
					}
					else
					if(k1 == 1 && (k2 != enumerators.length-1 && k2 != 0))
					{	
						 XCSpecifier e = Factory.getInstance().createXCSpecifier(c);
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
	
	



	

