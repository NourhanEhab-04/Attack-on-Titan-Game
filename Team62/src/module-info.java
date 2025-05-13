module GUI {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;

	opens game.gui to javafx.graphics, javafx.fxml;
}
