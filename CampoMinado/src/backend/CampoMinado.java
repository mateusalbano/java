package backend;

public class CampoMinado {
	
	private int campo[][];
	private boolean casaAberta[][];
	private int n1;
	private int n2;
	private int jogadas;
	private int bombas;
	private double bombRate;
	private boolean vitoria;
	private boolean derrota;
	
	public CampoMinado(int n1, int n2) {
		this.n1 = n1;
		this.n2 = n2;
		bombRate = 0.15;
		campo = new int[n1][n2];
		casaAberta = new boolean[n1][n2];
	}
	public CampoMinado(int n1, int n2, double bombRate) {
		this.n1 = n1;
		this.n2 = n2;
		setBombRate(bombRate);
		campo = new int[n1][n2];
		casaAberta = new boolean[n1][n2];
	}
	
	public void reiniciar() {
		campo = new int[n1][n2];
		casaAberta = new boolean[n1][n2];
		vitoria = false;
		derrota = false;
		jogadas = 0;
		bombas = 0;
	}
	
	public void clear() {
		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < n2; j++) {
				casaAberta[i][j] = true;
			}
		}
	}
	
	public void setBombRate(double bombRate) {
		if(bombRate <= 1 && bombRate >= 0) {
			this.bombRate = bombRate;
		} else {
			throw new IllegalArgumentException("bombRate precisa estar entre 0 - 1");
		}
	}
	
	public void exibir() {
		
		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < n2; j++) {
				if(casaAberta[i][j]) {
					System.out.print("|" + campo[i][j] + "|");
				} else {
					System.out.print("| |");
				}
				if (j == n2 - 1) System.out.println();
			}
		}
	}

	private boolean verificaVitoria() {
		
		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < n2; j++) {
				if(campo[i][j] >= 0 && !casaAberta[i][j]) return false;
			}
		}
		return vitoria = true;
	}
	
	public boolean jogar(int i, int j) {
		if (vitoria || derrota) {
			return false;
		}
		if (jogadas == 0) {
			setCampoMinado(i, j);
			jogadas++;
			verificaVitoria();
			return true;
		}
		jogadas++;
		casaAberta[i][j] = true;
		if (campo[i][j] == - 1) {
			derrota = true;
			return true;
		}
		abreCasas(i, j);
		verificaVitoria();
		return true;
	}
	
	private boolean casaVazia(int i, int j) {
		if (i < 0 ||i >= n1 || j < 0 || j >= n2) {
			return false;
		}
		if (campo[i][j] == 0 && casaAberta[i][j]) {
			return true;
		}
		return false;
	}
	
	private boolean casaTemBomba(int i, int j) {
		if (i < 0 ||i >= n1 || j < 0 || j >= n2) {
			return false;
		}
		if (campo[i][j] == -1) {
			return true;
		}
		return false;
	}
	
	private boolean verificaVizinhos(int posI, int posJ) {
		
		for (int i = posI - 1; i <= posI + 1; i++) {
			for (int j = posJ - 1; j <= posJ + 1; j++) {
				if(casaVazia(i, j) &&!(i == posI && j == posJ)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void abreCasas(int posI, int posJ) {
		casaAberta[posI][posJ] = true;
		boolean change = false;
		do {
			change = false;
			for (int i = 0; i < n1; i++) {
				for (int j = 0; j < n2; j++) {
					if(verificaVizinhos(i, j) && !casaAberta[i][j]) {
						casaAberta[i][j] = true;
						change = true;
					}
				}
			}
		} while (change);
	}
	
	private void setCampoMinado(int posI, int posJ) {
		
		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < n2; j++) {
				if (Math.random() <= bombRate && !(posI == i && posJ == j)) {
					campo[i][j] = - 1;
					bombas++;
				}
			}
		}
		
		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < n2; j++) {
				if (campo[i][j] != - 1) {
					checaBomba(i, j);	
				}
			}
		}		
		abreCasas(posI, posJ);
	}
	
	private void checaBomba(int posI, int posJ) {
		
		for (int i = posI - 1; i <= posI + 1; i++) {
			for (int j = posJ - 1; j <= posJ + 1; j++) {
				if(casaTemBomba(i, j) && !(i == posI && j == posJ)) {
					campo[posI][posJ]++;
				}
			}
		}
	}
	
	public int getJogadas() {
		return jogadas;
	}
	
	public int getBombas() {
		return bombas;
	}
	
	public int[][] getCampo() {
		return campo;
	}
	
	public boolean[][] getCasaAberta() {
		return casaAberta;
	}
	
	public boolean isVitoria() {
		return vitoria;
	}

	public boolean isDerrota() {
		return derrota;
	}
}