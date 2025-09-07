import java.util.Scanner;

//CLASSE UTILIZADA PARA ORGANIZAR OS VALORES REFERENTES A DETERMINADA LETRA NO INPUT
class Parametros {
    private char letra;
    private int valor;

    public Parametros(char letra, int valor) {
        this.letra = letra;
        this.valor = valor;
    }

    public char getLetra() {
        return letra;
    }

    public int getValor() {
        return valor;
    }
}

public class Algebra {

    public static String removerEspacosAntesDepois(String texto) {
        Boolean status1 = false;
        Boolean status2 = false;
        String textoAntes = "";
        String textoFinal = "";

        //REMOVE OS ESPACOS ANTES DE COMECAR O TEXTO
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) != ' ' && !status1) {
                status1 = true;
                textoAntes += texto.charAt(i);
            } else if (status1) {
                //DEPOIS QUE ENCONTRA TEXTO ADICIONA ELE ADICIONA A STRING FINAL E PARA DE REMOVER ESPACOS
                textoAntes += texto.charAt(i);
            }
        }

        //REMOVE OS ESPACOES DO FINAL DO TEXTO
        //PERCORRE DE TRAS PARA FRENTE
        for (int i = textoAntes.length() - 1; i >= 0; i--) {
            if (textoAntes.charAt(i) != ' ' && !status2) {
                status2 = true;
                textoFinal = textoAntes.charAt(i) + textoFinal;
            } else if (status2) {
                //DEPOIS QUE ENCONTRA TEXTO ADICIONA ELE ADICIONA A STRING FINAL E PARA DE REMOVER ESPACOS
                textoFinal = textoAntes.charAt(i) + textoFinal;
            }
        }
        return textoFinal;
    }

    //FUNCAO PARA CORTAR STRING RECEBIDA A PARTIR DE UM INICIO INCLUSIVO E FIM EXCLUSIVO
    public static String cortarString(String texto, int inicio, int fim) {
        String resultado = "";
        for (int i = inicio; i < fim; i++) {
            resultado += texto.charAt(i);
        }
        return resultado;
    }

    //FUNCAO PARA CORTAR STRING RECEBIDA A PARTIR DE UM INICIO INCLUSIVO
    public static String cortarString(String texto, int inicio) {
        String resultado = "";
        for (int i = inicio; i < texto.length(); i++) {
            resultado += texto.charAt(i);
        }
        return resultado;
    }

    //COMPARAR IGUALDADE ENTRE DUAS STRINGS
    public static Boolean equals(String texto1, String texto2) {
        for (int i = 0; i < texto1.length(); i++) {
            if (texto1.charAt(i) != texto2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    //PEGA OS VALORES DOS PARAMETROS REFERENTE A LETRA
    public static int getValorParametro(Parametros[] parametros, char letra) {
        for (Parametros p : parametros) {
            if (p.getLetra() == letra) {
                return p.getValor();
            }
        }
        return -1;
    }

    public static String[] divideCondicional(String condicional) {
        int nivel = 0;

        // INDEX DA PRIMEIRA VIRGULA -1 E PADRÃO CASO NAO ENCONTRE
        int indexPrimeiraVirgula = -1;
        int indexSegundaVirgula = -1;
        int indexTerceiraVirgula = -1;

        // PERCORRE TODOS OS CARACTERES
        for (int i = 0; i < condicional.length(); i++) {
            if (condicional.charAt(i) == '(') {
                nivel++;
            } else if (condicional.charAt(i) == ')') {
                nivel--;
            }
            // QUANDO TODAS OS PARENTESES ABERTOS FOREM FECHADOS
            if (nivel == 0 && condicional.charAt(i) == ',') {
                if (indexPrimeiraVirgula == -1) {
                    indexPrimeiraVirgula = i;
                } else if (indexSegundaVirgula == -1) {
                    indexSegundaVirgula = i;
                } else if (indexTerceiraVirgula == -1) {
                    indexTerceiraVirgula = i;
                }
            }
        }
        // SE A SEGUNDA VIRGULA FOR -1 ISSO SIGNIFICA QUE SO TEM DOIS PARAMETROS
        if (indexSegundaVirgula == -1) {
            String p1 = removerEspacosAntesDepois(cortarString(condicional, 0, indexPrimeiraVirgula));
            String p2 = removerEspacosAntesDepois(cortarString(condicional, indexPrimeiraVirgula + 1));
            return new String[] { p1, p2 };
        } else if (indexTerceiraVirgula == -1) {
            // CASO CONTRARIO TEM 3 PARAMETROS
            String p1 = removerEspacosAntesDepois(cortarString(condicional, 0, indexPrimeiraVirgula));
            String p2 = removerEspacosAntesDepois(cortarString(condicional, indexPrimeiraVirgula + 1, indexSegundaVirgula));
            String p3 = removerEspacosAntesDepois(cortarString(condicional, indexSegundaVirgula + 1));
            return new String[] { p1, p2, p3 };
        } else {
            // CASO CONTRARIO TEM 4 PARAMETROS
            String p1 = removerEspacosAntesDepois(cortarString(condicional, 0, indexPrimeiraVirgula));
            String p2 = removerEspacosAntesDepois(cortarString(condicional, indexPrimeiraVirgula + 1, indexSegundaVirgula));
            String p3 = removerEspacosAntesDepois(cortarString(condicional, indexSegundaVirgula + 1, indexTerceiraVirgula));
            String p4 = removerEspacosAntesDepois(cortarString(condicional, indexTerceiraVirgula + 1));
            return new String[] { p1, p2, p3, p4 };
        }
    }

    // POS ABRE É A POSICAO DO CARACTERE ( QUE EU QUERO FECHAR
    private static int acharFecha(String condicional, int posAbre) {
        int nivel = 1;
        for (int i = posAbre + 1; i < condicional.length(); i++) {
            char caractere = condicional.charAt(i);
            if (caractere == '(')
                nivel++;
            else if (caractere == ')') {
                nivel--;
                if (nivel == 0)
                    return i;
            }
        }
        return -1; // CASO NÃO ENCONTRE
    }

    public static int processarCondicional(String condicional, Parametros[] parametros) {
        // LE CADA CARACTERE DA CONDICIONAL
        // for (int posCaracter = 0; posCaracter < condicional.length(); posCaracter++)
        // {
        Boolean statusParenteses = false;
        for (int posCaracter = 0; posCaracter < condicional.length() && !statusParenteses; posCaracter++) {
            // VERIFICA SE ABRIU UMA CONDICAO
            if (condicional.charAt(posCaracter) == '(') {
                statusParenteses = true;
                String operador = "";
                String[] parametroStrings = null;
                String textoComParametros = "";

                int lado1 = 0, lado2 = 0, lado3 = 0, lado4 = 0;

                // LE O OPERADOR
                for (int i = posCaracter - 1; condicional.charAt(i) != ' ' && condicional.charAt(i) != '('; i--) {
                    operador = condicional.charAt(i) + operador;
                }
                if (equals(operador, "and")) {
                    // REMOVE O OPERADOR ANTES EXEMPLO AND( E REMOVE O ULTIMO CARACTERE QUE É O )
                    int fecha = acharFecha(condicional, posCaracter);
                    textoComParametros = cortarString(condicional, posCaracter + 1, fecha);

                    // DIVIDE A CONDICIONAL, SEPARANDO OS DOIS PARAMETROS
                    parametroStrings = divideCondicional(textoComParametros);

                    // ESSES PRINTS ME AJUDARAM A VISUALIZAR MELHOR ENQUANTO DESENVOLVIA
                    // System.out.println("Parametro 1: " + parametroStrings[0]);
                    // System.out.println("Parametro 2: " + parametroStrings[1]);

                    if (parametroStrings[0].length() > 1) {
                        // ELE TEM CONDICOES DENTRO, CHAMA A FUNCAO PARA PROCESSAR A NOVA CONDICAO
                        lado1 = processarCondicional(" " + parametroStrings[0], parametros);
                    } else {
                        // MAPEIA O PARAMETRO
                        lado1 = getValorParametro(parametros, parametroStrings[0].charAt(0));
                    }

                    if (parametroStrings[1].length() > 1) {
                        // ELE TEM CONDICOES DENTRO, CHAMA A FUNCAO PARA PROCESSAR A NOVA CONDICAO
                        lado2 = processarCondicional(" " + parametroStrings[1], parametros);
                    } else {
                        // MAPEIA O PARAMETRO
                        lado2 = getValorParametro(parametros, parametroStrings[1].charAt(0));
                    }

                    // VERIFICA SE ELE TEM MAIS DE 2 PARAMETROS
                    if (parametroStrings.length > 2) {
                        // ELE COM CERTEZA TEM 3 PARAMETROS
                        if (parametroStrings[2].length() > 1) {
                            // ELE TEM CONDICOES DENTRO, CHAMA A FUNCAO PARA PROCESSAR A NOVA CONDICAO
                            lado3 = processarCondicional(" " + parametroStrings[2], parametros);
                        } else {
                            // MAPEIA O PARAMETRO
                            lado3 = getValorParametro(parametros, parametroStrings[2].charAt(0));
                        }

                        // SE TIVER 4 PARAMETROS
                        if (parametroStrings.length == 4) {
                            if (parametroStrings[3].length() > 1) {
                                // ELE TEM CONDICOES DENTRO, CHAMA A FUNCAO PARA PROCESSAR A NOVA CONDICAO
                                lado4 = processarCondicional(" " + parametroStrings[3], parametros);
                            } else {
                                // MAPEIA O PARAMETRO
                                lado4 = getValorParametro(parametros, parametroStrings[3].charAt(0));
                            }
                            return lado1 & lado2 & lado3 & lado4;
                        }
                        return lado1 & lado2 & lado3;

                    } else {
                        return lado1 & lado2;
                    }

                } else if (equals(operador, "or")) {
                    // REMOVE O OPERADOR ANTES EXEMPLO AND( E REMOVE O ULTIMO CARACTERE QUE É O )
                    int fecha = acharFecha(condicional, posCaracter);
                    textoComParametros = cortarString(condicional, posCaracter + 1, fecha);

                    // DIVIDE A CONDICIONAL, SEPARANDO OS DOIS PARAMETROS
                    parametroStrings = divideCondicional(textoComParametros);

                    // ESSES PRINTS ME AJUDARAM A VISUALIZAR MELHOR ENQUANTO DESENVOLVIA
                    // System.out.println("Parametro 1: " + parametroStrings[0]);
                    // System.out.println("Parametro 2: " + parametroStrings[1]);

                    if (parametroStrings[0].length() > 1) {
                        // ELE TEM CONDICOES DENTRO, CHAMA A FUNCAO PARA PROCESSAR A NOVA CONDICAO
                        lado1 = processarCondicional(" " + parametroStrings[0], parametros);
                    } else {
                        // MAPEIA O PARAMETRO
                        lado1 = getValorParametro(parametros, parametroStrings[0].charAt(0));
                    }

                    if (parametroStrings[1].length() > 1) {
                        lado2 = processarCondicional(" " + parametroStrings[1], parametros);
                    } else {
                        // MAPEIA O PARAMETRO
                        if (parametroStrings[1] != null && parametroStrings[1].length() > 0) {
                            lado2 = getValorParametro(parametros, parametroStrings[1].charAt(0));
                        }
                    }

                    // VERIFICA SE ELE TEM MAIS DE 2 PARAMETROS
                    if (parametroStrings.length > 2) {
                        // ELE COM CERTEZA TEM 3 PARAMETROS
                        if (parametroStrings[2].length() > 1) {
                            // ELE TEM CONDICOES DENTRO, CHAMA A FUNCAO PARA PROCESSAR A NOVA CONDICAO
                            lado3 = processarCondicional(" " + parametroStrings[2], parametros);
                        } else {
                            // MAPEIA O PARAMETRO
                            lado3 = getValorParametro(parametros, parametroStrings[2].charAt(0));
                        }

                        // SE TIVER 4 PARAMETROS
                        if (parametroStrings.length == 4) {
                            if (parametroStrings[3].length() > 1) {
                                // ELE TEM CONDICOES DENTRO, CHAMA A FUNCAO PARA PROCESSAR A NOVA CONDICAO
                                lado4 = processarCondicional(" " + parametroStrings[3], parametros);
                            } else {
                                // MAPEIA O PARAMETRO
                                lado4 = getValorParametro(parametros, parametroStrings[3].charAt(0));
                            }
                            return lado1 | lado2 | lado3 | lado4;
                        }
                        return lado1 | lado2 | lado3;

                    } else {
                        return lado1 | lado2;
                    }
                } else if (equals(operador, "not")) {
                    // REMOVE O OPERADOR ANTES EXEMPLO AND( E REMOVE O ULTIMO CARACTERE QUE É O )
                    int fecha = acharFecha(condicional, posCaracter);
                    textoComParametros = cortarString(condicional, posCaracter + 1, fecha);                   

                    parametroStrings = new String[] { textoComParametros };

                    // ESSES PRINTS ME AJUDARAM A VISUALIZAR MELHOR ENQUANTO DESENVOLVIA
                    // System.out.println("Parametro 1: " + parametroStrings[0]);
                    // System.out.println("Parametro 2: " + parametroStrings[1]);

                    if (parametroStrings[0].length() > 1) {
                        // ELE TEM CONDICOES DENTRO, CHAMA A FUNCAO PARA PROCESSAR A NOVA CONDICAO
                        lado1 = processarCondicional(" " + parametroStrings[0], parametros);
                    } else {
                        // MAPEIA O PARAMETRO
                        lado1 = getValorParametro(parametros, parametroStrings[0].charAt(0));
                    }

                    // REALIZA A OPERACAO NOT
                    if (lado1 == 1) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int qtdParametros = sc.nextInt();

        // CONDICAO PARA EXECUCAO DO PROGRAMA
        while (qtdParametros > 0) {
            // VETOR DE PARAMETROS
            Parametros[] parametros = new Parametros[qtdParametros];
            for (int i = 0; i < qtdParametros; i++) {
                int valor = sc.nextInt();
                parametros[i] = new Parametros((char) ('A' + i), valor);
            }

            // ARMAZENA A CONDICIONAL COMPLETA, EX: and(A , and(A, B))
            String condicional = sc.nextLine();

            // VALOR QUE RETORNA EM CASO DE ERRO
            if (processarCondicional(condicional, parametros) != -1) {
                System.out.println(processarCondicional(condicional, parametros));
            }

            qtdParametros = sc.nextInt();
        }

        sc.close();
    }
}