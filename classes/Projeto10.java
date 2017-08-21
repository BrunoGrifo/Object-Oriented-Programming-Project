/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.pkg1.pkg0;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.System.out;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Projeto10 {
    public static void escrever(GestorExames gestor) {
        try {
            FileOutputStream os = new FileOutputStream("Gestor.dat");
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(gestor);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a escrever para ficheiro de objecto");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Erro a aceder ao ficheiro");
            ex.printStackTrace();
        }
       
    }
    public static GestorExames ler(){
        GestorExames gestor= new GestorExames();
        try {
            FileInputStream is = new FileInputStream("Gestor.dat");
            ObjectInputStream ois = new ObjectInputStream(is);
            gestor = (GestorExames) ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a ler para o ficheiro1");
        } catch (IOException ex) {
            System.out.println("Erro a aceder ao ficheiro1");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a ler objecto1");
        }
        return gestor;
    }

    

    public static void main(String[] args) throws IOException {
        GestorExames gestorExames = ler();
        //GestorExames gestorExames = new GestorExame();
        /*Docente grifo = new Docente("grifo", "hotmail", 12345, "qwe", "qwe");
         NaoDocentes fabio = new NaoDocentes("fabio", "hotmail", "qwe", 45123, "qwe");
         Docente victor = new Docente("victor", "hotmail", 17823, "qwe", "qwe");
         Docente fernando = new Docente("fernando", "hotmail", 4527123, "qwe", "qwe");
         Docente makoa = new Docente("makoa", "hotmail", 453123, "qwe", "qwe");
         ArrayList<Disciplina> lista_disciplinas = new ArrayList<>();
         Disciplina programacao = new Disciplina("Programação", grifo);
         Disciplina desenho = new Disciplina("Desenho", fernando);
         Disciplina literatura = new Disciplina("Literatura", makoa);
         ArrayList<Disciplina> test = new ArrayList<>();
         lista_disciplinas.add(desenho);
         lista_disciplinas.add(programacao);
         test.add(literatura);
         Curso Informatica = new Curso("Informatica", 7, "licenciatura", lista_disciplinas);
         Curso Medicina = new Curso("Medicina", 7, "licenciatura", test);
         Aluno aluno1 = new Aluno("aluno1", "hotmail", 123, "Normal");
         Aluno aluno2 = new Aluno("aluno2", "hotmail", 123, "Erasmus");
         Aluno aluno3 = new Aluno("aluno3", "hotmail", 123, "trabalhador-estudante");
         Aluno aluno4 = new Aluno("aluno4", "hotmail", 123, "dirigente associativo");
         Aluno aluno5 = new Aluno("aluno5", "hotmail", 123, "atleta");
         gestorExames.addNaoDocente(fabio);
         gestorExames.addAluno(aluno1);
         gestorExames.addAluno(aluno2);
         gestorExames.addAluno(aluno3);
         gestorExames.addAluno(aluno4);
         gestorExames.addAluno(aluno5);
         gestorExames.addDiscipla(desenho);
         gestorExames.addDiscipla(programacao);
         gestorExames.addDiscipla(literatura);
         gestorExames.addCurso(Informatica);
         gestorExames.addCurso(Medicina);
         gestorExames.addDocente(victor);
         gestorExames.addDocente(grifo);
         gestorExames.addDocente(fernando);
         gestorExames.addDocente(makoa);*/
        int numero;
        //gestorExames.ler();
        while (true) {
            out.print("-------------MENU-------------\n"
                    + "1-Criar Salas\n"
                    + "2-Criar Pessoa\n"
                    + "3-Criar Disciplina\n"
                    + "4-Criar Curso\n"
                    + "5-Criar exame \n"
                    + "6-Configurar sala exame \n"
                    + "7-Convocar vigilantes e funcionarios para um exame\n"
                    + "8-Inscrever alunos em exame \n"
                    + "9-Alterar/Lançar notas de um exame\n"
                    + "10-Adicionar Docentes a uma Disciplina\n"
                    + "11-Adicionar Alunos a uma disciplina\n"
                    + "12-Adicionar Cursos a um aluno\n"
                    + "13-Adicionar disciplinas a um Curso\n"
                    + "14-Remover Pessoa\n"
                    + "15-Remover Curso\n"
                    + "16-Remover Curso de um Aluno\n"
                    + "17-Remover Disciplina de um Curso\n"
                    + "18-Remover Disciplina\n"
                    + "19-Remover Exame\n"
                    + "20-Remover Sala\n"
                    + "21-Remover Pessoa de Exame\n"
                    + "22-Remover Pessoa de Disciplina\n"
                    + "23-Listar exames\n"
                    + "24-Listar alunos inscritos num exame \n"
                    + "25-Listar exames de um aluno \n"
                    + "26-Listar docentes e funcionarios de um exame \n"
                    + "27-Listar Exames em que um docente/funcionario esta envolvido \n"
                    + "28-Listar notas de um exame \n"
                    + "29-Listar Disciplinas\n"
                    + "30-Listar Cursos\n"
                    + "31-Listar Pessoas\n"
                    + "32-Listar Salas\n"
                    + "33-Listar cursos de um aluno\n"
                    + "0-Sair");
            //gestorExame.escrever();
            escrever(gestorExames);
            numero = gestorExames.testaOpcao(0, 33);
            switch (numero) {
                case 1:
                    gestorExames.criaSalas();
                    break;
                case 2:
                    gestorExames.criaPessoa();
                    break;
                case 3:
                    gestorExames.criaDisciplina();
                    break;
                case 4:
                    gestorExames.criarCurso();
                    break;
                case 5:
                    gestorExames.adicionaExames();
                    break;
                case 6:
                    gestorExames.configurarSalaExame();
                    break;
                case 7:
                    gestorExames.convocaAjudantesExame();
                    break;
                case 8:
                    gestorExames.increveAlunosExame();
                    break;
                case 9:
                    gestorExames.lancaNotasExame();
                    break;
                case 10:
                    gestorExames.adicionaDocentesDisciplina();
                    break;
                case 11:
                    gestorExames.inscreveAlunoDisciplina();
                    break;
                case 12:
                    gestorExames.adicionaCursosAluno();
                    break;
                case 13:
                    gestorExames.adicionaDisciplinasCurso();
                    break;
                case 14:
                    gestorExames.eliminaPessoa();
                    break;
                case 15:
                    gestorExames.eliminarCurso();
                    break;
                case 16:
                    gestorExames.eliminaCursoAluno();
                    break;
                case 17:
                    gestorExames.removeDisciplinaCurso();
                    break;
                case 18:
                    gestorExames.selecionaDisciplinaEliminar();
                    break;
                case 19:
                    gestorExames.eliminaExame();
                    break;
                case 20:
                    gestorExames.eliminaSala();
                    break;
                case 21:
                    gestorExames.removePessoaExame();
                    break;
                case 22:
                    gestorExames.removePessoaDisciplina();
                    break;
                case 23:
                    gestorExames.listaExames();
                    break;
                case 24:
                    gestorExames.listarAlunosExame();
                    break;
                case 25:
                    gestorExames.listaExamesAluno();
                    break;
                case 26:
                    gestorExames.listaDocentesFuncionariosExame();
                    break;
                case 27:
                    gestorExames.listaExamesDocentesFuncionario();
                    break;
                case 28:
                    gestorExames.listaNotas();
                    break;
                case 29:
                    gestorExames.listaDisciplinas();
                    break;
                case 30:
                    gestorExames.listaCursos();
                    break;
                case 31:
                    gestorExames.listaPessoas();
                    break;
                case 32:
                    gestorExames.listaSalas();
                    break;
                case 33:
                    gestorExames.listaAlunoCursos();
                    break;
                case 0:
                    System.exit(0);
                default:
                    out.print("Opçao invalida\n");
            }
        }
    }
}
