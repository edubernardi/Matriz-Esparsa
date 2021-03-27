package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	    MatrizEsparsa me = new MatrizEsparsa(10,10);

	    int sLin, sCol;
	    double sVal;
        Random r = new Random();
	    for(int x = 0; x < 10; x++){
	        sLin = r.nextInt(10);
	        sCol = r.nextInt(10);
	        sVal = (double) r.nextInt(100000);
	        me.set(sVal, sLin, sCol);
        }

	    //testando set
	    me.set(1,0,0);
	    me.set(0,0,0);

	    //testando armazena
	    me.armazena("teste.txt");

	    //testando copia
	    double[][] matrix = me.copia(0,0,10,10);
	    for (int i =0 ; i< 10; i++){
	    	for (int j= 0; j < 10; j++){
	    		System.out.print(matrix[i][j] + " ");
			}
	    	System.out.print("\n");
		}

	    //testando transposta
	    MatrizEsparsa t = me.transposta(me);
		System.out.println("======================================");

		//imprimindo transposta pra ver se funciona mesmo
		matrix = t.copia(0,0,10,10);
		for (int i =0 ; i< 10; i++){
			for (int j= 0; j < 10; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}

	    t.armazena("teste2.txt");
		System.out.println("======================================");
		me.carrega("teste.txt");
		matrix = me.copia(0,0,10,10);
		for (int i =0 ; i< 10; i++){
			for (int j= 0; j < 10; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
    }
}

