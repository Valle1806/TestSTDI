//Padre de todos los controladores
package nEnLinea.ctrl;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;



public abstract class BaseCtrl extends GenericForwardComposer {

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		//Method to execute actions after to render a component
		super.doAfterCompose(comp);
	}

}