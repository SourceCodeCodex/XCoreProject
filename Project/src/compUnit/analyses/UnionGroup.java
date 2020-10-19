package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTCompositeTypeSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCUnion;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule18_4
 * union group
 */

@RelationBuilder
public class UnionGroup implements IRelationBuilder<XCUnion, XCCompUnit>{
	
	@Override
	public Group<XCUnion> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		Group<XCUnion> res = new Group<>();
		
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
				if(c instanceof IASTCompositeTypeSpecifier) 
				{
					if(((IASTCompositeTypeSpecifier) c).getKey( )== 2)
					{
						XCUnion u = Factory.getInstance().createXCUnion((IASTCompositeTypeSpecifier) c);
						res.add(u);
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
	
	


