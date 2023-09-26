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
public class Barreira extends Individuos{

    public Barreira(ImageView BarreiraPic, double x, double y, double tamanho){
        super(5, BarreiraPic, x, y, tamanho);
        this.IndividuoPic.setFitWidth(100);
    }
}
