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
public class Canhao extends Individuos{
    private Tiro tiro;
    
    public Canhao(ImageView CanhaoPic){
        super(3, CanhaoPic, 256, 635, 50);
    }

    public void moveDir(){
        if(IndividuoPic.getX() < 1250){
            IndividuoPic.setX(IndividuoPic.getX() + 16);
        }
    }

    public void moveEsq(){
        if(IndividuoPic.getX() > 0){
            IndividuoPic.setX(IndividuoPic.getX() - 16);
        }
    }
    
    public ImageView retornaImg(){
        return IndividuoPic;
    }
    
    public Tiro retornaTiroCanhao(){
        return tiro;
    }    
    
    public void atirar(ImageView TiroPic){
    if(tiro == null){
    tiro = new Tiro(TiroPic, IndividuoPic.getX() + 10, IndividuoPic.getY() - 25, 30);
    movimentaTiroThread.start();
    }
    else{
        if(!tiro.atira){
            tiro = new Tiro(TiroPic, IndividuoPic.getX() + 10, IndividuoPic.getY() - 25, 30);
            movimentaTiroThread.start();
        }
   }
}

public AnimationTimer movimentaTiroThread = new AnimationTimer(){
    long time = 0;
    @Override
    public void handle(long timestamp){
        if(timestamp - time >=50000000){
            if(tiro.atira == true)
                tiro.moveTiro(0);
            time = timestamp;
        }
    }
};
}
