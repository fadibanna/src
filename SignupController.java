package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignupController implements Initializable {

	@FXML
	private ImageView progress;
	@FXML
	private Label suc;
	@FXML
	private TextField name;
	@FXML
	private RadioButton female;

	@FXML
	private ToggleGroup gender;

	@FXML
	private TextField location;

	@FXML
	private Button login;

	@FXML
	private RadioButton male;

	@FXML
	private AnchorPane parentPane;

	@FXML
	private TextField password;

	@FXML
	private Button signup;
	private Connection connection;
	private DBHandler handler;
	private PreparedStatement pst;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		progress.setVisible(false);
		name.setStyle("-fx-text-inner-color: #1C273A;");
		password.setStyle("-fx-text-inner-color:  #1C273A;");
		location.setStyle("-fx-text-inner-color:  #1C273A;");
		handler = new DBHandler();
	}

	@FXML
	public void signUP(ActionEvent ael) {
		progress.setVisible(true);
		PauseTransition pt = new PauseTransition();
		pt.setDuration(Duration.seconds(3));
		pt.setOnFinished(e -> {

			progress.setVisible(false);
			suc.setText("SignUp Successfully");

		});
		pt.play();
		String insert = "INSERT INTO louay(names,password,gender,location)" + "VALUES (?,?,?,?)";
		connection = handler.getConnection();
		try {
			pst = connection.prepareStatement(insert);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pst.setString(1, name.getText());
			pst.setString(2, password.getText());
			pst.setString(3, getGender());
			pst.setString(4, location.getText());
			pst.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@FXML
	public void loginAction(ActionEvent ae2) throws IOException {
		signup.getScene().getWindow().hide();
		Stage login = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("LoginMain.fxml"));
		Scene scene = new Scene(root);
		Image icon = new Image("IconMain.png");
		login.getIcons().add(icon);
		login.setTitle("");
		login.setScene(scene);
		login.show();
		login.setResizable(false);
	}

	public String getGender() {
		String gen = "";
		if (male.isSelected()) {
			gen = "Male";
		} else if (female.isSelected()) {
			gen = "Female";

		}
		return gen;

	}

}
