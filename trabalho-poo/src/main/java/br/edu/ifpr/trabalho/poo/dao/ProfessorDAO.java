package br.edu.ifpr.trabalho.poo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.edu.ifpr.trabalho.poo.conexao.Conexao;
import br.edu.ifpr.trabalho.poo.modelo.Professor;

public class ProfessorDAO {
	
	public Professor lerDadosProfessor() {
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Informe o nome do professor:");
		String nome = teclado.nextLine();
		System.out.println("Informe o cpf do professor:");
		String cpf = teclado.nextLine();
		System.out.println("Informe o telefone do professor:");
		String telefone = teclado.nextLine();
		System.out.println("Informe o endereco do professor:");
		String endereco = teclado.nextLine();
		System.out.println("Informe a data de nascimento do professor:");
		String dataNascimento = teclado.nextLine();
		System.out.println("Informe o siape do professor:");
		int siape = teclado.nextInt();
		
		
		
		Professor professor = new Professor(0, nome, cpf, telefone, endereco, dataNascimento, siape);
		return professor;
	}

	public ArrayList<Professor> buscarProfessores() {
		ArrayList<Professor> listaDeProfessor = new ArrayList<Professor>();
		String SQL = "SELECT * FROM tb_professor";
		try {
			PreparedStatement SQLPreparada = Conexao.getConexao().prepareStatement(SQL);
			ResultSet resultado = SQLPreparada.executeQuery();
			while (resultado.next()) {
				Professor professor = new Professor();
				professor.setIdPessoa(resultado.getInt("id_pessoa"));
				professor.setNome(resultado.getString("nome"));
				professor.setCpf(resultado.getString("cpf"));
				professor.setTelefone(resultado.getString("telefone"));
				professor.setEndereco(resultado.getString("endereco"));
				professor.setDataNascimento(resultado.getString("data_nascimento"));
				listaDeProfessor.add(professor);
			}
		} catch (Exception excecao) {
			excecao.printStackTrace();
		}
		return listaDeProfessor;
	}

	public void salvarProfessor(Professor professor) {
		String SQL = "INSERT INTO tb_professor (id_pessoa, nome, cpf, telefone, endereco, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparacaoDaInstrucao = Conexao.getConexao().prepareStatement(SQL);
			preparacaoDaInstrucao.setInt(1, professor.getIdPessoa());
			preparacaoDaInstrucao.setString(2, professor.getNome());
			preparacaoDaInstrucao.setString(3, professor.getCpf());
			preparacaoDaInstrucao.setString(4, professor.getTelefone());
			preparacaoDaInstrucao.setString(5, professor.getEndereco());
			preparacaoDaInstrucao.setString(6, professor.getDataNascimento());
			
			preparacaoDaInstrucao.executeUpdate();
		} catch (SQLException excecao) {
			excecao.printStackTrace();
		}
	}

	// Transformacao
	public Professor transformarResultSetEmObjeto(ResultSet resultSet) throws SQLException {
		Professor professor = new Professor();
		try {
			professor.setIdPessoa(resultSet.getInt("id_pessoa"));
			professor.setNome(resultSet.getString("nome"));
			professor.setCpf(resultSet.getString("cpf"));
			professor.setTelefone(resultSet.getString("telefone"));
			professor.setEndereco(resultSet.getString("endereco"));
			professor.setDataNascimento(resultSet.getString("data_nascimento"));
			return professor;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao transformar");
		}

	}

}
