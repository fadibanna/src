package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXToolbar;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.jfoenix.controls.JFXButton;

public class HomePageController implements Initializable {

	@FXML
	private JFXToolbar toolbar;
	@FXML
	private HBox toolBarRight;
	@FXML
	private Label lblMenu;
	@FXML
	private VBox overflowContainer;
	@FXML
	private JFXButton logOut;
	@FXML
	private JFXButton Exit;

	@FXML
	AnchorPane holderPane;
	AnchorPane home;
	AnchorPane anchor;
	@FXML
	private Text welcome;
	private static HomePageController instance;

	public HomePageController() {
		instance = this;
	}

	public static HomePageController getInstance() {
		return instance;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		JFXRippler rippler = new JFXRippler(lblMenu);
		rippler.setMaskType(JFXRippler.RipplerMask.RECT);
		toolBarRight.getChildren().add(rippler);

		openMenu();
		createPage(home, "Home.fxml");
		setUsername(LoginController.getInstance().username());
	
	}


	public void setUsername(String user) {
		this.welcome.setText("Welcome, " + user);
	}

	private void openMenu() {

		JFXPopup pop = new JFXPopup();
		pop.setPopupContent(overflowContainer);
		pop.setPopupContent(anchor);
//		pop.set(lblMenu);

		lblMenu.setOnMouseClicked(e -> {

			pop.show(lblMenu, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -1, 42);

		});
	}

	private void setNode(Node node) {

		holderPane.getChildren().clear();
		holderPane.getChildren().add((Node) node);

		FadeTransition ft = new FadeTransition(Duration.millis(1500));
		ft.setNode(node);
		ft.setFromValue(0.1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}

	public void createPage(AnchorPane home2, String string) {

		try {
			home2 = FXMLLoader.load(getClass().getResource("Home.fxml"));
			setNode(home2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void HomeBtn(ActionEvent he) {
		Platform.exit();
	}

	@FXML
	public void logOut(ActionEvent event) {
		logOut.getScene().getWindow().hide();
		Stage login = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("LoginMain.fxml"));

			Scene scene = new Scene(root);
			Image icon = new Image("IconMain.png");
			login.getIcons().add(icon);
			login.setTitle("");
			login.setScene(scene);
			login.show();
			login.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
