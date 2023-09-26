/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceinvaders;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 *
 * @author Guilherme Chiarotto de Moraes
 */
public class Tiro extends Individuos{
    boolean atira;
  
    public Tiro(ImageView TiroPic, double x, double y, double tamanho){
        super(1, TiroPic, x, y, tamanho);
        IndividuoPic.setVisible(true);
        atira = true;
    }

    public void moveTiro(double destino){
         double posicao = IndividuoPic.getY();
         if(posicao > destino){
             if(posicao - 16 >= 0)
                IndividuoPic.setY(posicao - 16);
             else{
                 IndividuoPic.setVisible(false);
                 atira = false;
             }
         }
         else{
             if(posicao + 16 <= 720)
                 IndividuoPic.setY(posicao + 16);
             else{
                 IndividuoPic.setVisible(false);
                 atira = false;
             }
             
         }
    }
}