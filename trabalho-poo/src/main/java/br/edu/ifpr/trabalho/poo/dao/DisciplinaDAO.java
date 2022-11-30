package br.edu.ifpr.trabalho.poo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.edu.ifpr.trabalho.poo.conexao.Conexao;
import br.edu.ifpr.trabalho.poo.modelo.Disciplina;
import br.edu.ifpr.trabalho.poo.modelo.Professor;
import br.edu.ifpr.trabalho.poo.modelo.Turma;

public class DisciplinaDAO {

	public Disciplina lerDadosDisciplina() {
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Digite o nome do disciplina:");
		String nome = teclado.nextLine();
		
		System.out.println("Digite o id de professor");
		int idProfessor = teclado.nextInt();
		
		Professor professor = new Professor();
		professor.setIdPessoa(idProfessor);
		
		
		System.out.println("Digite o id da turma");
		int idTurma = teclado.nextInt();
		
		Turma turma = new Turma();
		turma.setIdTurma(idTurma);
		
		Disciplina disciplina = new Disciplina(0, nome, professor, turma);
		return disciplina;
	}
	
	public static Disciplina lerDadosDisciplina(Turma turma, Professor professor) {
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Digite o nome do disciplina:");
		String nome = teclado.nextLine();
		
		Disciplina disciplina = new Disciplina(0, nome, professor, turma);
		return disciplina;
	}
	
	
	public ArrayList<Disciplina> buscarDisciplinas() {
		ArrayList<Disciplina> listaDeDisciplinas = new ArrayList<Disciplina>();
		String SQL = "SELECT * FROM tb_disciplina";
		try {
			PreparedStatement SQLPreparada = Conexao.getConexao().prepareStatement(SQL);
			ResultSet resultado = SQLPreparada.executeQuery();
			while (resultado.next()) {
				
				Disciplina disciplina = new Disciplina();
				disciplina.setIdDisciplina(resultado.getInt("id_disciplina"));
				disciplina.setNome(resultado.getString("nome"));
				
				listaDeDisciplinas.add(disciplina);
				
			}
		} catch (Exception excecao) {
			excecao.printStackTrace();
		}
		return listaDeDisciplinas;
	}
	
	public void salvarDisciplina(Disciplina disciplina) {
		String SQL = "INSERT INTO tb_disciplina (nome, fk_professor, fk_turma) VALUES (?, ?, ?)";
		try {
			PreparedStatement preparacaoDaInstrucao = Conexao.getConexao().prepareStatement(SQL);
			
			preparacaoDaInstrucao.setString(1, disciplina.getNome());
			preparacaoDaInstrucao.setInt(2, disciplina.getProfessor().getIdPessoa());
			preparacaoDaInstrucao.setInt(3, disciplina.getTurma().getIdTurma());
			preparacaoDaInstrucao.executeUpdate();
		} catch (SQLException excecao) {
			excecao.printStackTrace();
		}
	}

	// Transformacao
	public Disciplina transformarResultSetEmObjeto(ResultSet resultSet) throws SQLException {
		Disciplina disciplina = new Disciplina();
		try {
			disciplina.setIdDisciplina(resultSet.getInt("id_disciplina"));
			disciplina.setNome(resultSet.getString("nome"));
			return disciplina;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao transformar");
		}

	}


}
