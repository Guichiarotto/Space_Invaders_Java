/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guilherme Chiarotto de Moraes
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button BJogar;
    
    @FXML
    private Button BExit;    
    
    @FXML
    private void AbreJogo(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaJogo.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        TelaJogoController tela = loader.getController();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent KeyEvent) {
                switch (KeyEvent.getCode()){
                    case D:
                    case RIGHT:
                        tela.moveD();
                        break;
                        
                    case A:    
                    case LEFT:
                        tela.moveE();
                        break;
                        
                    case SPACE:
                        tela.atiraCanhao();
                        break;
                        
                    default:
                        break;
                }
            }
        });        
        Stage stage = (Stage) BJogar.getScene().getWindow();
        stage.setTitle("Space Invaders");
        stage.setScene(scene);
        stage.show();     
    }
    
    @FXML
    private void SairJogo(ActionEvent event) throws Exception {
        System.exit(0);
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
