/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceinvaders;

import javafx.scene.image.ImageView;

/**
 *
 * @author Guilherme Chiarotto de Moraes
 */
public abstract class Individuos {
    protected int vida;
    protected ImageView IndividuoPic;
    
    public Individuos(int vida, ImageView IndividuoPic, double x, double y, double tamanho){
        this.vida = vida;
        this.IndividuoPic = IndividuoPic;
        this.IndividuoPic.setLayoutX(0);
        this.IndividuoPic.setLayoutY(0);
        this.IndividuoPic.setX(x);
        this.IndividuoPic.setY(y);
        this.IndividuoPic.setFitHeight(tamanho);
        this.IndividuoPic.setFitWidth(tamanho);
    }
    
    public int getVida(){
        return vida;
    }
    
    public ImageView retornaImg(){
        return IndividuoPic;
    }
    
    public void diminuiVida(){
        vida--;
        if(vida <= 0){
            IndividuoPic.setVisible(false);
        }
    }
}
