package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
    	int linhas = 30;
    	int colunas = 3;
	    MatrizEsparsa me = new MatrizEsparsa(linhas,colunas);

	    int sLin, sCol;
	    double sVal;
        Random r = new Random();
	    for(int x = 0; x < (linhas * colunas * 0.01); x++){
	        sLin = r.nextInt(linhas);
	        sCol = r.nextInt(colunas);
	        sVal = (double) r.nextInt(100000);
	        me.set(sVal, sLin, sCol);
        }

	    //testando set
	    me.set(1,1,1);

	    //testando armazena
	    me.armazena("teste.txt");

	    //testando copia
		System.out.println("Copiada:");
	    double[][] matrix = me.copia(0,0, linhas, colunas);
	    for (int i =0 ; i< matrix.length; i++){
	    	for (int j= 0; j < matrix[i].length; j++){
	    		System.out.print(matrix[i][j] + " ");
			}
	    	System.out.print("\n");
		}

	    //testando transposta
	    MatrizEsparsa t = me.transposta(me);
		System.out.println("======================================");

		//imprimindo transposta pra ver se funciona mesmo
		matrix = t.copia(0,0,colunas,linhas);
		System.out.println("Transposta:");
		for (int i =0 ; i< matrix.length; i++){
			for (int j= 0; j < matrix[i].length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}

	    t.armazena("teste2.txt");
		System.out.println("======================================");
		//testando carrega
		System.out.println("Carregada:");
		me.carrega("teste.txt");
		matrix = me.copia(0,0,linhas,colunas);
		for (int i =0 ; i< matrix.length; i++){
			for (int j= 0; j < matrix[i].length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}

		System.out.println("======================================");
		System.out.println("Transposta carregada:");
		me.carrega("teste2.txt");
		matrix = me.copia(0,0, colunas, linhas);
		for (int i =0 ; i< matrix.length; i++){
			for (int j= 0; j < matrix[i].length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
    }
}

