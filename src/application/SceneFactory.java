package application;

import Controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class SceneFactory{
	
	public static Scene getScene(SceneEnum s_num, Controller controller) {
		if (s_num == SceneEnum.LOGIN) {
			return new LoginScene(controller, new GridPane());
		} else if (s_num == SceneEnum.SIGNUP) {
			return new SignupScene(controller, new GridPane());
		} else if (s_num == SceneEnum.MAIN) {
			return new MainScene(controller, new GridPane());
		} else if (s_num == SceneEnum.ADD) {
			return new AddScene(controller, new GridPane());
		} else if (s_num == SceneEnum.EDIT) {
			return new EditScene(controller, new GridPane());
		}  else if (s_num == SceneEnum.USER) {
			return new UserScene(controller, new GridPane());
		} else if (s_num == SceneEnum.UPDATE) {
			return new UpdateScene(controller, new GridPane());
		}
		
		return null;
	}
}
