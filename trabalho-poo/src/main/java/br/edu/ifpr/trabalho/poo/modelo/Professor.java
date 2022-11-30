package br.edu.ifpr.trabalho.poo.modelo;

public class Professor extends Pessoa {


	private int idProfessor;
	private int siape;
	
	
	public Professor() {
		
	}
	
	public Professor(String nome, String cpf, String telefone, String endereco, String dataNascimento, int siape) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
		this.siape = siape;
	}
	
	public Professor(int idPessoa, String nome, String cpf, String telefone, String endereco, String dataNascimento, int siape) {
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
		this.siape = siape;
	}
	

	public int getSiape() {
		return siape;
	}

	public void setSiape(int siape) {
		this.siape = siape;
	}


	public void setIdProfessor(int int1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void imprimirDados() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	

}
