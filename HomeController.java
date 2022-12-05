package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomeController implements Initializable{
	
	@FXML
	private AnchorPane homeAnchor;
	
	@FXML
	private JFXButton R1;
	@FXML
	private JFXButton R2;
	@FXML
	private JFXButton R3;
	@FXML
	private JFXButton R4;
	HomePageController homePage;


   public void initialize(URL arg0, ResourceBundle arg1){
	
   }
//   @FXML
//	public void Action1 ( ActionEvent e) {
//		HomePageController.getInstance().createPage(homeAnchor, "R1.fxml");
//		
//	}
//	
//	@FXML
//	public void Action2 ( ActionEvent e) {
//		HomePageController.getInstance().createPage(homeAnchor, "R2.fxml");
//	}
//	
//	@FXML
//	public void Action3 ( ActionEvent e) {
//		HomePageController.getInstance().createPage(homeAnchor, "R3.fxml");
//	}
//	
//	@FXML
//	public void  Action4 ( ActionEvent e) {
//		HomePageController.getInstance().createPage(homeAnchor, "R4.fxml");
//	}
//

   @FXML
   public void R1Action(ActionEvent e){
	try{
		loadFXML("R1.fxml","R1");
	} catch (IOException e1) {
		e1.printStackTrace();
	}

   }

    public void R2Action(ActionEvent e){
	try{
		loadFXML("R2.fxml","R2");
	} catch (IOException e1) {
		e1.printStackTrace();
	}

   }
     public void R3Action(ActionEvent e){
	try{
		loadFXML("R3.fxml","R3");
	} catch (IOException e1) {
		e1.printStackTrace();
	}

   }
     public void R4Action(ActionEvent e){
	try{
		loadFXML("R4.fxml","R4");
	} catch (IOException e1) {
		e1.printStackTrace();
	}

   }

   public void loadFXML(String loc, String title) throws IOException {
	Parent root = FXMLLoader.load(getClass().getResource(loc));
	Stage stage = new Stage(StageStyle.UNDECORATED);
	stage.setScene(new Scene(root));
	stage.setTitle(title);
	stage.setX(510);
	stage.setY(367);
	stage.show(); 
}


}