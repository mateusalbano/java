package aplicacao;

import java.util.Scanner;

import backend.Forca;
import backend.PalavrasJSON;

public class Main {
	
	private Forca forca;
	private PalavrasJSON palavrasJSON;
	private String[] temas;
	private String temasString;
	private Scanner scanner;
	
	public Main() {
		scanner = new Scanner(System.in);
		json();
		String resposta = "";
		do {
			jogo();
			
			System.out.println(", deseja jogar novamente? (s/n)");
			resposta = scanner.next().toUpperCase();
			while (!(resposta.equals("S") || resposta.equals("N"))) {
				System.out.println("Deseja jogar novamente? (s/n)");
				resposta = scanner.next().toUpperCase();
			}

			System.out.println();
		} while (resposta.equals("S"));
		System.out.println("__FIM__");
		scanner.close();
	}
	
	private void jogo() {
		System.out.println(temasString);

		System.out.print("Escolha um tema: ");
		int opcao = scanner.nextInt();
		
		while (opcao < 0 || opcao > temas.length - 1) {
			System.out.print("Opção inválida, tente novamente: ");
			opcao = scanner.nextInt();
		}
		if (forca != null) {
			forca.reiniciar(palavrasJSON.getPalavra(temas[opcao]));
		} else {
			forca = new Forca(palavrasJSON.getPalavra(temas[opcao]));
		}
		int jogo = 0;
		
		do {
			System.out.print(forca.getPalavraForca());
			System.out.print(" Erros: " + forca.getErros() + "\n");
			System.out.print("Letra/resposta: ");
			
			String resposta = scanner.next();
			if (resposta.length() == 1) {
				jogo = forca.jogar(resposta.charAt(0));
			} else {
				jogo = forca.jogar(resposta);
			}
			
			System.out.println();
			
			switch (jogo) {
			case Forca.CHAR_NOT_VALID: System.out.println("Letra inválida.");
				break;
			case Forca.CHAR_ALREADY_INPUTED: System.out.println("Letra já enviada.");
				break;
			case Forca.VICTORY:	System.out.println(forca.getPalavraForca());
					System.out.print("VITÓRIA");
				break;
			case Forca.DEFEAT: System.out.println(forca.getPalavraForca());
					System.out.print("DERROTA");
				break;
			}
			
		} while (jogo != Forca.VICTORY && jogo != Forca.DEFEAT);
		
		System.out.print(", a palavra era \"" + forca.getPalavra() + "\"");
	}
	
	private void json() {
		palavrasJSON = new PalavrasJSON("Palavras.json");
		temas = palavrasJSON.getTemas();
		temasString = "";
		for (int i = 0; i < temas.length; i++) {
			temasString += i + " - " + temas[i] + "\n";
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}
