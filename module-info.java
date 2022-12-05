module Jennifer_Airline {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires com.jfoenix;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
}
