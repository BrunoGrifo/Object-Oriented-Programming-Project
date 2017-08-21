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
public class Docente extends Funcionario implements Serializable {

    private String area_investigacao;

    Docente(String nome, String email, int numero_mecanografico, String categoria, String area_investigacao) {
        super(nome, email, numero_mecanografico, categoria);
        this.area_investigacao = area_investigacao;
    }

    @Override
    public String toString() {
        return super.toString() + " |Area investigacao:" + area_investigacao;
    }
}
