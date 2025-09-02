import java.util.Scanner;

public class Sort {

	public static void swap(int menor, int j);

	public static void ordenar(int array[], int m) {
		for(int i =0; i<(array.length()-1); i++) {
			int menor = i;

			for(int j=(i+1); j<array.length; j++) {
				if((array[menor] % m) == (array[j] % m)) {
					//EMPATE
					if((array[menor] % 2) != (array[j] % 2)) {
						//UM PAR E OUTRO IMPAR
						if(array[j] % 2 != 0) {
							//SE O SEGUNDO FOR IMPAR
							menor = j;
						}
					} else if(array[menor] % 2 != 0) {
						//OS DOIS SAO IMPARES
						if(array[menor] < array[j]) {
							//O MAIOR IMPAR PRECEDE
							menor = j;
						}
					} else {
						//EMPATE DOIS PARES
						if(array[menor] > array[j]) {
							//O MENOR PAR PRECEDE
							menor = j;
						}
					}
				}
					
			}
			swap(menor, i);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		System.out.println(n + " " + m);
		int[] array = new int[n];
		while(n!=0 && m!=0) {
			for(int i=0; i<n-1; i++){
				array[i] = sc.nextInt();
			}
			ordenar(array, m);
			m = sc.nextInt();
		}
	}
}
