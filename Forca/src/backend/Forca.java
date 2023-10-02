package backend;

import java.text.Normalizer;
import java.util.ArrayList;

public class Forca {
	private int erros;
	private int maxErros;
	private String palavra;
	private ArrayList<Character> letrasEnviadas;
	private char[] letrasCorretas;
	private boolean fim;
	
	public static final int MIN_LENGTH = 2;
	public static final int MAX_LENGTH = 50;
	
	public static final int GAME_OVER = 0;
	public static final int CHAR_NOT_VALID = 1;
	public static final int CHAR_ALREADY_INPUTED = 2;
	public static final int CORRECT_MOVE = 3;
	public static final int VICTORY = 4;
	public static final int WRONG_MOVE = 5;
	public static final int DEFEAT = 6;
	
	public boolean isFim() {
		return fim;
	}

	public Forca(String palavra) {
		iniciarJogo(palavra, 6);
	}
	
	public Forca(String palavra, int maxErros) {
		iniciarJogo(palavra, maxErros);
	}
	
	private void iniciarJogo(String palavra, int maxErros) {
		fim = false;
		erros = 0;
		int length = palavra.length();
		if(length < MIN_LENGTH || length > MAX_LENGTH) {
			throw new IllegalArgumentException("Wrong length: " + length);
		}
		palavraPermitida(palavra);
		
		this.palavra = palavra.toUpperCase();
		letrasEnviadas = new ArrayList<>();
		this.letrasCorretas = new char[length];
		
		for (int i = 0; i < letrasCorretas.length; i++) {
			char c = palavra.charAt(i);
			if (c == ' ' || c == '-' || c == '\'') {
				this.letrasCorretas[i] = c;
			} else {
				this.letrasCorretas[i] = Character.MIN_VALUE;
			}
		}
		this.maxErros = maxErros;
	}
	
	
	public void reiniciar(String palavra) {
		iniciarJogo(palavra, maxErros);
	}
	
	public void reiniciar() {
		iniciarJogo(palavra, maxErros);
	}
	
	public String getPalavraForca() {
		String palavraForca = "";
		if(letrasCorretas[0] == Character.MIN_VALUE) {
			palavraForca += "__";
		} else {
			palavraForca += letrasCorretas[0];
		}
		for (int i = 1; i < letrasCorretas.length; i++) {
			char c = letrasCorretas[i];
			if(c == Character.MIN_VALUE) {
				palavraForca += " __";
			} else {
				palavraForca += " " + c;
			}
		}
		return palavraForca;
	}
	
	public int getErros() {
		return erros;
	}

	public String getPalavra() {
		return palavra;
	}

	public ArrayList<Character> getLetrasEnviadas() {
		return letrasEnviadas;
	}

	public char[] getLetrasCorretas() {
		return letrasCorretas;
	}
	
	private boolean saoIguais(char c1, char c2) {
		String s1 = String.valueOf(c1);
		String s2 = String.valueOf(c2);
		s1 = Normalizer.normalize(s1, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		return s1.equals(s2);
	}
	
	private boolean letraPermitida(char letra) {
		return letra > 64 && letra < 91;
	}
	
	private void palavraPermitida(String palavra) {
		int letterCount = 0;
		for (int i = 0; i < palavra.length(); i++) {
			char c = palavra.charAt(i);
			if (!Character.isLetter(c)) {
				if (c != ' ' && c != '-' && c != '\'') {	
					throw new IllegalArgumentException("Palavra \"" + palavra + "\" possui caractere(s) inválido(s)");
				}
			} else {
				letterCount++;	
			}
		}
		if (letterCount < 1) {
			throw new IllegalArgumentException("Palavra \"" + palavra + "\" deve possuir pelo menos uma letra");
		}
	}

	private boolean palavraTemLetra(char letra) {
		boolean temLetra = false;
		for (int i = 0; i < palavra.length(); i++) {
			char letraPalavra = palavra.charAt(i);
			if (saoIguais(letraPalavra, letra)) {
				temLetra = true;
				letrasCorretas[i] = letraPalavra;
			}
		}
		return temLetra;
	}
	
	private boolean vitoria() {
		for (int i = 0; i < letrasCorretas.length; i++) {
			if (letrasCorretas[i] == Character.MIN_VALUE) {
				return false;
			}
		}
		return true;
	}
	
	private boolean letraJaEnviada(char letra) {
		for (char c : letrasEnviadas) {
			if (letra == c) {
				return true;
			}
		}
		letrasEnviadas.add(letra);
		return false;
	}
	
	/*
	 *If 0 returned, the game is already finished, it needs to restart for method activity.
	 *If 1 returned, the char inputed is not valid.
	 *If 2 returned, the char is already inputed.
	 *If 3 returned, the chosen word has this letter, but the player did not achieve victory.
	 *If 4 returned, the player achieved victory.
	 *If 5 returned, a mistake was committed but the match is not over.
	 *If 6 returned, a mistake was committed and the match is over.
	 */
	public int jogar(char letra) {
		if (fim) {
			return GAME_OVER;
		}
		letra = Character.toUpperCase(letra);
		if (!letraPermitida(letra)) {
			return CHAR_NOT_VALID;
		}
		
		if(letraJaEnviada(letra)) {
			return CHAR_ALREADY_INPUTED;
		}
		
		if (palavraTemLetra(letra)) {
			if (!vitoria()) {
				return CORRECT_MOVE;
			}
			fim = true;
			return VICTORY;
		}
		erros++;
		if (erros < maxErros) {
			return WRONG_MOVE;
		}
		fim = true;
		return DEFEAT;
	}
	
	public int jogar(String palavra) {
		if (fim) {
			return 0;
		}
		String s1 = Normalizer.normalize(palavra, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		String s2 = Normalizer.normalize(this.palavra, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		s1 = s1.replace(" ", "");
		s2 = s2.replace(" ", "");
		if (s1.equalsIgnoreCase(s2)) {
			fim = true;
			return VICTORY;
		}
		erros++;
		if (erros < maxErros) {
			return WRONG_MOVE;
		}
		fim = true;
		return DEFEAT;
	}
}