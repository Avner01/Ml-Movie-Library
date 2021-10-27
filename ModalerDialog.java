package Main;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class ModalerDialog extends Dialog<ButtonType> {

	public ModalerDialog() {
		ButtonType ok = ButtonType.OK;
		ButtonType cancel = ButtonType.CANCEL;
		this.getDialogPane().getButtonTypes().addAll(ok, cancel);

	}

}
