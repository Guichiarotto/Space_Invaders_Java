/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guilherme Chiarotto de Moraes
 */
public class GameOverController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button BContinuar;
    
    @FXML
    private Label LPontos;
    
    @FXML
    private AnchorPane PainelFim;
    
    @FXML
    private void ReiniciaJogo(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) PainelFim.getScene().getWindow();
        stage.setScene(scene);
        stage.show();         
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pontuacaoFinal.start();
    }   
    
    public void mudaLabel() throws Exception{
        Stage stage = (Stage) PainelFim.getScene().getWindow();
        LPontos.setText(Integer.toString((int)stage.getUserData()));
    }
    
    public AnimationTimer pontuacaoFinal = new AnimationTimer(){
        @Override
        public void handle(long timestamp){
            try{
                mudaLabel();
                pontuacaoFinal.stop();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
    };     
    
}
