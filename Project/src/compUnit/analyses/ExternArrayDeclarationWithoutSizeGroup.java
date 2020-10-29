package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTArrayDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTEqualsInitializer;
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
 * Rule8_12
 */

	@RelationBuilder
	public class ExternArrayDeclarationWithoutSizeGroup implements IRelationBuilder<XCDeclaration, XCCompUnit>{
		
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
					
					if(c instanceof IASTArrayDeclarator && c.isPartOfTranslationUnitFile()) 
					{
						IASTNode p,b;
						int ok = 0;
						p = c.getParent();
						b = p.getChildren()[0];
						String type = b.getRawSignature();
						
						if(type.indexOf("extern") != -1)
						{
							IASTNode children[] = c.getChildren();
							if(children.length > 2) 
							{
								if(children[2] instanceof IASTEqualsInitializer)
								ok = 1;
							}
							if(children[1].getChildren().length == 0 && ok == 0 )
							{ 
							
								XCDeclaration d=Factory.getInstance().createXCDeclaration((IASTSimpleDeclaration)p);
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