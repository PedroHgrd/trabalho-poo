package br.edu.ifpr.trabalho.poo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.edu.ifpr.trabalho.poo.conexao.Conexao;
import br.edu.ifpr.trabalho.poo.modelo.Campus;
import br.edu.ifpr.trabalho.poo.modelo.Curso;

public class CursoDAO {

	public static Curso lerDadosCurso() {
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		System.out.println("Informe o nome do curso:");
		String nome = teclado.nextLine();
		System.out.println("Informe a duração do curso:");
		String duracao = teclado.nextLine();
		System.out.println("Informe a modalidade do curso:");
		String modalidade = teclado.nextLine();
		
		System.out.println("Informe o id do campus: ");
		int idCampus = teclado.nextInt();
		
		Campus campus = new Campus();
		campus.setIdCampus(idCampus);
		
		Curso curso = new Curso(nome, duracao, modalidade, campus);
		return curso;
	}
	
	public ArrayList<Curso> buscarCursos() {
		ArrayList<Curso> listaDeCursos = new ArrayList<Curso>();
		String SQL = "SELECT * FROM tb_curso";
		try {
			PreparedStatement SQLPreparada = Conexao.getConexao().prepareStatement(SQL);
			ResultSet resultado = SQLPreparada.executeQuery();
			while (resultado.next()) {
				
				Curso curso = new Curso();
				curso.setIdCurso(resultado.getInt("id_curso"));
				curso.setNome(resultado.getString("nome"));
				curso.setDuracao(resultado.getString("duracao"));
				curso.setModalidade(resultado.getString("modalidade"));
				listaDeCursos.add(curso);
				
			}
		} catch (Exception excecao) {
			excecao.printStackTrace();
		}
		return listaDeCursos;
	}
	
	public void salvarCurso(Curso curso) {
		String SQL = "INSERT INTO tb_curso (id_curso, nome, duracao, modalidade, fk_campus) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparacaoDaInstrucao = Conexao.getConexao().prepareStatement(SQL);
			preparacaoDaInstrucao.setInt(1, curso.getIdCurso());
			preparacaoDaInstrucao.setString(2, curso.getNome());
			preparacaoDaInstrucao.setString(3, curso.getDuracao());
			preparacaoDaInstrucao.setString(4, curso.getModalidade());
			preparacaoDaInstrucao.setInt(5, curso.getCampus().getIdCampus());
			
			preparacaoDaInstrucao.executeUpdate();
		} catch (SQLException excecao) {
			excecao.printStackTrace();
		}
	}

	// Transformacao
	public Curso transformarResultSetEmObjeto(ResultSet resultSet) throws SQLException {
		Curso curso = new Curso();
		try {
			curso.setIdCurso(resultSet.getInt("id_curso"));
			curso.setNome(resultSet.getString("nome"));
			curso.setDuracao(resultSet.getString("duracao"));
			curso.setModalidade(resultSet.getString("modalidade"));
			return curso;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao transformar");
		}

	}


}
