package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Funcionario;

public class Main {

	public static void main(String[] args) throws IllegalArgumentException, InputMismatchException {
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		
		try {
			List<Funcionario> funcionarios = new ArrayList<>();
			
			System.out.print("Quantos funcionários serão registrados? ");
			int qtdFunc = input.nextInt();

			for (int i = 1; i <= qtdFunc; i++) {
				System.out.println("\nFuncionário nº" + i);

				System.out.print("ID: ");
				int id = input.nextInt();

				System.out.print("Nome: ");
				input.nextLine();
				String nome = input.nextLine();

				System.out.print("Salário: ");
				double salario = input.nextDouble();

				funcionarios.add(new Funcionario(id, nome, salario));
			}

			System.out.print("\nDigite o ID do funcionário que terá seu salário aumentado: ");
			int id = input.nextInt();
			Funcionario func = funcionarioExiste(funcionarios, id);
			
			if (func != null) {
				System.out.print("Insira a porcentagem de aumento: ");
				double porcentagem = input.nextDouble();
				func.aumentarSalario(porcentagem);
			} else {
				System.out.println("ID inserido não existe.");
			}

			System.out.println("\nLista de funcionários:");
			for (Funcionario funcionario : funcionarios) {
				System.out.println(funcionario);
			}
		}catch(RuntimeException e) {
			System.out.println("Você inseriu um tipo de dado inapropriado para a operação!");
			System.out.println("Reinicie o programa para tentar novamente.");
		}finally {
			input.close();
		}
	}
	
	public static Funcionario funcionarioExiste(List<Funcionario> funcionarios, int id) {
		Funcionario func = funcionarios.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return func;
	}
}
