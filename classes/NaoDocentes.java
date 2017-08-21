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
public class NaoDocentes extends Funcionario implements Serializable{

    private String cargo;

    NaoDocentes(String nome, String email, String cargo, int numero_mecanografico, String categoria) {
        super(nome, email, numero_mecanografico, categoria);
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return super.toString() + " |Cargo:" + cargo;
    }

}
