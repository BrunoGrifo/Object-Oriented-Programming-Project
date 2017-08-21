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
public class Exame implements Serializable{

    protected Disciplina disciplina;
    protected int duracao;
    protected Docente docente_responsavel;
    protected ArrayList<Docente> vigilantes;
    protected ArrayList<NaoDocentes> apoio;
    protected HashMap<Aluno, Integer> alunos;
    protected Date exame_inicio;
    protected Date exame_final;
    protected Sala sala;

    Exame(Disciplina disciplina, int duracao, Sala sala,
            Docente docente_responsavel, ArrayList<Docente> vigilantes, ArrayList<NaoDocentes> apoio,
            HashMap<Aluno, Integer> alunos, Date exame_inicio, Date exame_final) {
        this.disciplina = disciplina;
        this.duracao = duracao;
        this.sala = sala;
        this.docente_responsavel = docente_responsavel;
        this.vigilantes = vigilantes;
        this.apoio = apoio;
        this.alunos = alunos;
        this.exame_inicio = exame_inicio;
        this.exame_final = exame_final;
    }

    public void removeVigilantes(Docente docente) {
        vigilantes.remove(docente);
    }

    public void removeApoio(NaoDocentes vigilante_apoio) {
        apoio.remove(vigilante_apoio);
    }

    public void adicionaAluno(Aluno aluno, int nota) {
        alunos.put(aluno, nota);
    }

    public void removeAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public int getDuracao() {
        return duracao;
    }

    public Sala getSala() {
        return sala;
    }

    public Docente getDocenteResponsavel() {
        return docente_responsavel;
    }

    public ArrayList<Docente> getDocentes() {
        return vigilantes;
    }

    public ArrayList<NaoDocentes> getApoio() {
        return apoio;
    }

    public HashMap<Aluno, Integer> getAlunos() {
        return alunos;
    }

    public Date getDataInicio() {
        return exame_inicio;
    }

    public Date getDataFinal() {
        return exame_final;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setResponsavel(Docente docente_responsavel) { //para depois quando se eliminar uma disciplina ou um docente
        this.docente_responsavel = docente_responsavel;
    }

    @Override
    public String toString() {
        return "Disciplina:" + disciplina.getDisciplina() + " |Hora de inicio:" + exame_inicio + " |Hora de fim:" + exame_final + " |Duracao:" + duracao + " |Sala:" + sala + " |Docente Responsavel:" + docente_responsavel.nome;
    }

}
