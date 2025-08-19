#include <stdio.h>
#include <string.h>
int main() {
	char string1[100];
	char string2[100];
	char stringFinal[200];

	scanf("%s", string1);
	scanf("%s", string2);
	int contador = 0;
	for(int i=0; i<100; i++) {
		if(string1[i] != '\0') {
			stringFinal[contador] = string1[i];
			contador++;
		}
		if(string2[i] != '\0') {
			stringFinal[contador] = string2[i];
			contador++;
		}
	}
	printf("%s", stringFinal);
	return 0;
}
