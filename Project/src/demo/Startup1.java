package demo;


import org.eclipse.ui.IStartup;

import ro.lrg.insider.view.ToolRegistration;


public class Startup1 implements IStartup {

	@Override
	public void earlyStartup() {
		ToolRegistration.getInstance().registerXEntityConverter(
				new MyXEntityConverter()
				);
	}
}
