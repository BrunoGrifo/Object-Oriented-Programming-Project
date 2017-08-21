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
//abstract muda isto
abstract class Pessoa implements Serializable{

    protected String nome;
    protected String email;

    Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    
    public String getNome(){
        return nome;
    }

    @Override
    public String toString() {
        return "Nome:" + nome + " |email:" + email;
    }
}
