/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.pkg1.pkg0;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class Sala implements Serializable{

    private String nome_sala;
    private int maximo;

    Sala(String nome_sala, int maximo) {
        this.nome_sala = nome_sala;
        this.maximo = maximo;
    }

    public String getNomeSala() {
        return nome_sala;
    }

    public int getMaximo() {
        return maximo;
    }

    @Override
    public String toString() {
        return "Nome sala:" + nome_sala + " |Lotacao maximo:" + maximo;
    }
}
