/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.pkg1.pkg0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class Normal extends Exame implements Serializable{

    Normal(Disciplina disciplina, int duracao, Sala sala, Docente docente_responavel,
            ArrayList<Docente> vigilantes, ArrayList<NaoDocentes> apoio, HashMap<Aluno, Integer> alunos, Date exame_inicio, Date exame_final) {
        super(disciplina, duracao, sala, docente_responavel, vigilantes, apoio, alunos, exame_inicio, exame_final);
    }

    @Override
    public String toString() {
        return super.toString() + "-Normal";
    }
}
