/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guilherme Chiarotto de Moraes
 */
public class TelaJogoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView CanhaoPic;
    
    @FXML
    private ImageView TiroPic;
    
    @FXML
    private ImageView TiroAlienPic;
    
    @FXML
    private AnchorPane Painel;
    
    @FXML
    private Label LVida;
    
    @FXML
    private Label LPontuacao;    
    
    private Canhao canhao;
    private Tiro tiro;
    private Inimigo[][] alien = new Inimigo[11][5];
    private Barreira[] barreira = new Barreira[5];
    private int pontuacao = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        canhao = new Canhao(CanhaoPic);
        LVida.setText(Integer.toString(canhao.getVida()));
        LPontuacao.setText(Integer.toString(pontuacao));
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 5; j++){
                ImageView AlienPic = null;
                int pontuacao;
                if(j == 0){
                    AlienPic = new ImageView("/spaceinvaders/Imagens/Alien3.png");
                    pontuacao = 30;
                }
                else if(j == 1 || j == 2){
                    AlienPic = new ImageView("/spaceinvaders/Imagens/Alien1.png");
                    pontuacao = 20;
                }
                else{
                   AlienPic = new ImageView("/spaceinvaders/Imagens/Alien2.png");
                   pontuacao = 10;
                }
                alien[i][j] = new Inimigo(AlienPic, i*50, j*50, 50, pontuacao);
                Painel.getChildren().add(alien[i][j].retornaImg()); 
            }
        }
        for(int i = 0; i < 5; i++){
            ImageView BarreiraPic = new ImageView("/spaceinvaders/Imagens/barreira.png");
            barreira[i] = new Barreira(BarreiraPic, i*275 + 50, 545, 50);
            Painel.getChildren().add(barreira[i].retornaImg());
        }
        
        Colisao.start();
        movimentaInimigo.start(); 
        GameOverThread.start();
    }

    public void moveD(){
        canhao.moveDir();
    }
    
    public void moveE(){
        canhao.moveEsq();
    }
    
    public void atiraCanhao(){
        canhao.atirar(TiroPic);
    }
    
    public AnimationTimer movimentaInimigo = new AnimationTimer(){
        long time = 0;
        @Override
        public void handle(long timestamp){
            if(timestamp - time >= 1000000000 - pontuacao*700000){
                if(alien[10][4].verificaMovimento() && alien[0][0].verificaMovimento()){
                    for(int i = 0; i < 11; i++){
                        for(int j = 0; j < 5; j++){
                            alien[i][j].moveInimigo();
                        }   
                    }  
                }
                else{
                    for(int i = 0; i < 11; i++){
                        for(int j = 0; j < 5; j++){
                            alien[i][j].setControle(!alien[i][j].retornaControle());
                            alien[i][j].IndividuoPic.setY(alien[i][j].IndividuoPic.getY() + 50);
                        }
                    }
                }
                int i;
                int j;
                do{
                    i = (int)(Math.random()*10);
                    j = (int)(Math.random()*4);
                }while(!alien[i][j].IndividuoPic.isVisible());

                alien[i][j].atirar(TiroAlienPic);
                time = timestamp;
            }
        }
    };
    
    private void GameOver() throws Exception{
        Colisao.stop();
        movimentaInimigo.stop();
        GameOverThread.stop();
        Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) Painel.getScene().getWindow();
        stage.setTitle("Space Invaders");
        stage.setUserData(pontuacao);
        stage.setScene(scene);
        stage.show(); 
    }
    
    public AnimationTimer GameOverThread = new AnimationTimer(){
        @Override
        public void handle(long timestamp){
            try{
                if(canhao.getVida() == 0){
                    GameOver();
                }
                boolean todosInvisiveis = true;
                for(int i = 0; i < 11; i++){
                    for(int j = 0; j < 5; j++){
                        if(alien[i][j].IndividuoPic.isVisible()){
                            todosInvisiveis = false;
                            if(alien[i][j].IndividuoPic.getY() >= 525){
                                GameOver();
                            }
                        }
                    }
                }
                if(todosInvisiveis){
                    GameOver();
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());        
            }
        }
    };    
    
    
    
       
    public AnimationTimer Colisao = new AnimationTimer(){

        @Override
        public void handle(long timestamp){
            for(int i = 0; i < 11; i++){
                for(int j = 0; j < 5; j++){
                    if(alien[i][j].IndividuoPic.isVisible()){
                        if(canhao.retornaTiroCanhao() != null && canhao.retornaTiroCanhao().IndividuoPic.isVisible()){
                            if(canhao.retornaTiroCanhao().IndividuoPic.getBoundsInParent().intersects(alien[i][j].IndividuoPic.getBoundsInParent())){
                                alien[i][j].diminuiVida();
                                canhao.retornaTiroCanhao().diminuiVida();
                                canhao.retornaTiroCanhao().atira = false;
                                pontuacao += alien[i][j].retornaPontuacao();
                            }
                        }
                    }
                    if(canhao.IndividuoPic.isVisible()){
                        if(alien[i][j].retornaTiroInimigo() != null && alien[i][j].retornaTiroInimigo().IndividuoPic.isVisible()){
                            if(alien[i][j].retornaTiroInimigo().IndividuoPic.getBoundsInParent().intersects(canhao.IndividuoPic.getBoundsInParent())){
                                canhao.diminuiVida(); 
                                LVida.setText(Integer.toString(canhao.getVida()));
                                alien[i][j].retornaTiroInimigo().diminuiVida();
                                alien[i][j].retornaTiroInimigo().atira = false;                          
                            }
                        }
                    }
                        if(alien[i][j].retornaTiroInimigo() != null && canhao.retornaTiroCanhao() != null){
                            if(canhao.retornaTiroCanhao().IndividuoPic.isVisible() && alien[i][j].retornaTiroInimigo().IndividuoPic.isVisible()){
                                if(canhao.retornaTiroCanhao().IndividuoPic.getBoundsInParent().intersects(alien[i][j].retornaTiroInimigo().IndividuoPic.getBoundsInParent())){
                                    canhao.retornaTiroCanhao().diminuiVida();
                                    alien[i][j].retornaTiroInimigo().diminuiVida();
                                    canhao.retornaTiroCanhao().atira = false;
                                    alien[i][j].retornaTiroInimigo().atira = false;
                                }
                            }
                        }
                        for(int k = 0; k < 5; k++){
                            if(barreira[k].IndividuoPic.isVisible()){
                                if(alien[i][j].retornaTiroInimigo() != null && alien[i][j].retornaTiroInimigo().IndividuoPic.isVisible()){
                                        if(alien[i][j].retornaTiroInimigo().IndividuoPic.getBoundsInParent().intersects(barreira[k].IndividuoPic.getBoundsInParent())){
                                            barreira[k].diminuiVida();
                                            alien[i][j].retornaTiroInimigo().diminuiVida();
                                            alien[i][j].retornaTiroInimigo().atira = false;
                                        }
                                }
                                if(canhao.retornaTiroCanhao() != null && canhao.retornaTiroCanhao().IndividuoPic.isVisible()){
                                       if(canhao.retornaTiroCanhao().IndividuoPic.getBoundsInParent().intersects(barreira[k].IndividuoPic.getBoundsInParent())){
                                            barreira[k].diminuiVida();
                                            canhao.retornaTiroCanhao().diminuiVida();
                                            canhao.retornaTiroCanhao().atira = false;
                                        }
                                }
                            } 
                        }


                }
            }
            LPontuacao.setText(Integer.toString(pontuacao));
        }
    }; 
}
