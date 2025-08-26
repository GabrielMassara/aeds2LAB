import java.util.Scanner;
public class Espelho {

	public static String[] reverse(String valores[]) {
		String vetorFinal[];
		contador = 0;
		for (String s : valores) {
			String aux;
			for(int i=s.length()-1; i>=0; i--) {
				aux+=s.charAt(i);
			}
			vetorFinal[contador] = aux;
			contador ++;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		do {
			String texto = sc.nextLine();
			String valores[] = texto.split(" ");
			int n1 = Integer.parseInt(valores[0]);
			int n2 = Integer.parseInt(valores[1]);
			System.out.println(n1);
			System.out.println(n2);
			for(int i=n1; i<=n2; i++) {
				System.out.print(i);
			}
			for(int i=n2; i>=n1; i--) {
                                System.out.print(i);
                        }
			System.out.print("\n");
		} while(sc.hasNextLine());
	}
}
