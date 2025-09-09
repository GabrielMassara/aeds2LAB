import java.util.Scanner;

public class Grid {
	public static int posicaoArray(int array[], int elemento) {
		for(int i=0; i<array.length; i++) {
			if(array[i] == elemento)
				return i;
		}
		return -1;
	}

	public static boolean diffArrays(int array1[], int array2[]) {
		for(int i = 0; i<array1.length; i++) {
			if(array1[i] != array2[i])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		while(sc.hasNext()) {
			n = sc.nextInt();
			int[] largada = new int[n];
			int[] chegada = new int[n];
			
			for(int i=0; i<n; i++) {
				largada[i] = sc.nextInt();
			}
			for(int i=0; i<n; i++) {
                                chegada[i] = sc.nextInt();
                        }

			//INSERT
			int contador = 0;
			while(!diffArrays(largada, chegada)) {
				for (int i = 0; i < n; i++) {
                        		int tmp = chegada[i];

					//se tiver subido de posicao
					int trocas = (posicaoArray(largada, tmp) - i);
					if(trocas > 0) {
						contador+=trocas;
						int index = 0;
						int[] arrayAux = new int[n];
						for(int j = i+1; j <= (i+trocas); j++){
							arrayAux[index] = chegada[j];
							index++;
						}
						arrayAux[index] = chegada[(i+trocas)];
						index++;
						for(int j = index; j < n; j++) {
							arrayAux[index] = chegada[j];
							index++;
						}
						chegada = arrayAux;
					}
                		}
			}
			System.out.println(contador);			
		}
	}
}
