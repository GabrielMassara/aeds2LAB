class BinarySort {
    public static int total = 256;
    
    //vetor
    static int[] lista = new int[total];
    
    //funcao para preencher o vetor
    public static void preencherVetor(int[] vetor, int total) {
        for (int i = 1; i <= total; i++) {
            vetor[i - 1] = i;
        }
    }
    
    public static int binarySearch(int[] vetor, int n) {
        
        int baixo = 0;
        int alto = (vetor.length-1);
        int palpite;
        
        int contador = 0;
        
        //Enquanto nao chegar em um unico elemento
        while(baixo <= alto) {
            
            //Contador para ver as repeticoes do algoritmo O(lg N)
            contador++;
            System.out.println("Repeticao: "+contador);
            
            //Variavel que pega o meio
            int meio = (baixo + alto) / 2;
            
            //Variavel que faz o palpite com base na metade
            palpite = vetor[meio];
            
            //Se achar o valor
            if(vetor[meio] == n)
                return meio;
                
            //Se o valor for menor que o palpite
            //O "-1" serve para nao pegar o valor do meio que ja foi conferido
            else if(palpite > n)
                alto = meio - 1;
                
            //Se o valor for maior que o palpite
            //O "+1" serve para nao pegar o valor do meio que ja foi conferido
            else if(palpite < n)
                baixo = meio + 1;
        }
        //Retorna -1 em caso de não encontrar o elemento
        return -1;
    }
    
    public static void main(String[] args) {
        preencherVetor(lista, total);
        
        int n = 111;
        int resultado = binarySearch(lista, n);
        
        System.out.println(resultado!=-1?resultado:"Nao existe");
    }
}
