/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceinvaders;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 *
 * @author Guilherme Chiarotto de Moraes
 */
public class Inimigo extends Individuos{
    private Tiro tiro;
    private boolean controle = true;
    private int pontuacao;
    
    public Inimigo(ImageView InimigoPic, double x, double y, double tamanho, int pontuacao){
        super(1, InimigoPic, x, y, tamanho);
        this.pontuacao = pontuacao;
    }
    
    public int retornaPontuacao(){
        return pontuacao;
    }
    
    public boolean moveInimigo(){
        if(controle){
            if(retornaImg().getX() < 1250){
                   retornaImg().setX(retornaImg().getX() + 50);
                   }
            else{
                controle = false;
                if(retornaImg().getY() < 720 - 50){
                   return true;
                }
            }                   
        }
        else{
            if(retornaImg().getX() >= 50){
                retornaImg().setX(retornaImg().getX() - 50);
            }
            else{
                controle = true;
                if(retornaImg().getY() < 720 - 50){
                   return true;
                }
            }              
        }
        return false;
    }
    
    public boolean retornaControle(){
        return controle;
    }
    
    public void setControle(boolean rControle){
        controle = rControle;
    }
    
    public boolean verificaMovimento(){
        if(retornaImg().getX() < 1250 && controle == true){
            return true;
        }
        else if(retornaImg().getX() >= 50 && controle == false){
            return true;
        }
        return false;
    }
    
    public void atirar(ImageView TiroPic){
        if(!TiroPic.isVisible()){
            if(tiro == null){
            tiro = new Tiro(TiroPic, IndividuoPic.getX() + 10, IndividuoPic.getY() + 25, 30);
            movimentaTiroAlienThread.start();
            }
            else{
                if(!tiro.atira){
                    tiro = new Tiro(TiroPic, IndividuoPic.getX() + 10, IndividuoPic.getY() + 25, 30);
                    movimentaTiroAlienThread.start();
                }
           }
    }
}
    public Tiro retornaTiroInimigo(){
        return tiro;
    }

public AnimationTimer movimentaTiroAlienThread = new AnimationTimer(){
    long time = 0;
    @Override
    public void handle(long timestamp){
        if(timestamp - time >=50_000_000){
            if(tiro.atira == true)
                tiro.moveTiro(720);
            time = timestamp;
        }
    }
};
}