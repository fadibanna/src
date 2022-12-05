package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.*;

public class LoginController implements Initializable {
	@FXML
	private Button forgotpassword;

	@FXML
	private Button login;

	@FXML
	private PasswordField password;

	@FXML
	private ImageView progress;

	@FXML
	private CheckBox remember;

	@FXML
	private Button signup;

	@FXML
	private TextField username;
	private Connection connection;
	private DBHandler handler;
	private PreparedStatement pst;
	private static LoginController instance;
	 public LoginController()
	    {
	    	instance = this;
	    }
	    
	    public static LoginController getInstance()
	    {
	    	return instance;
	    }
	    
	    
	    public String username()
	    {
	    	return username.getText();
	    }
	    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		progress.setVisible(false);
		username.setStyle("-fx-text-inner-color: #1C273A;");
		password.setStyle("-fx-text-inner-color: #1C273A;");
		handler = new DBHandler();

	}

	@FXML
	public void loginAction(ActionEvent e)
	{ 
		
		
		//loading bar
		progress.setVisible(true);
		PauseTransition pt = new PauseTransition();
		pt.setDuration(Duration.seconds(3));
		pt.setOnFinished(ev -> {
		
			 //Retrieve Data from Database
			
			connection = handler.getConnection();
			String q1 = "SELECT * from louay where names=? and password=?";
			
			try {
				pst = connection.prepareStatement(q1);
			    pst.setString(1, username.getText());
			    pst.setString(2, password.getText());
			    ResultSet rs = pst.executeQuery();
			    
			    int count=0;
			     
			    while(rs.next())
			    {
			    	count=count+1;
			    }
			    
			    if(count==1)
			    {
			    	login.getScene().getWindow().hide();
			    	
			    	Stage home = new Stage();
			    	try {
			    		
						Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
						
					    Scene scene = new Scene(root);
						Image icon = new Image("IconMain.png");
						home.getIcons().add(icon);
						home.setTitle("");
					    home.setScene(scene);
					    home.show();
						home.setResizable(false);

			    	
			    	} catch (IOException e1) {
					
						e1.printStackTrace();
					}
			    	
			
			    }
			    else
			    {
			    	Alert alert = new Alert(Alert.AlertType.ERROR);
			    	alert.setHeaderText(null);
			    	alert.setContentText("Please check your username and password.");
			    	alert.show();
			    	progress.setVisible(false);
			    }
			    

			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			finally
			{
				try {
					connection.close();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
				
			
			
       });
		
		pt.play();
		
	
	}

	@FXML
	public void signup(ActionEvent e1) throws IOException {
		login.getScene().getWindow().hide();
		Stage signup = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		Scene scene = new Scene(root);
		Image icon = new Image("IconMain.png");
		signup.getIcons().add(icon);
		signup.setTitle("");
		signup.setScene(scene);
		signup.show();
		signup.setResizable(false);
	}

}