package backend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteSortear {

	private int sortear(int min, int max) {

		return (int) (min + Math.round(Math.random() * max - min));
	}
	
	@Test
	void testeSortear() {
		boolean[] vetor = new boolean[401];
		int n = 0;
		int q = 1;
		while (q < 10000) {
			n = sortear(0, 400);
			vetor[n] = true;
			q++;
		}
		for (int i = 0; i < vetor.length; i++) {
			assertEquals(true, vetor[i]);
		}
	}
	

}
