import java.util.Scanner;
public class Espelho {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String texto = sc.nextLine();
		
		while(sc.hasNextLine()) {
			String valores[] = texto.split(" ");
			int n1 = Integer.parseInt(valores[0]);
			int n2 = Integer.parseInt(valores[1]);
			
			for(int i=n1; i<=n2; i++) {
				System.out.print(i);
			}
			for(int i=n2; i>=n1; i--) {
                                System.out.print(i);
                        }
			System.out.print("\n");
			texto = sc.nextLine();
		}	
	}
}
