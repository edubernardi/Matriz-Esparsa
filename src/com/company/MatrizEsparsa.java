package com.company;

import java.io.*;

public class MatrizEsparsa {
    private Celula[] mat;
    private int totalLinhas, totalColunas, naoZeros;

    public MatrizEsparsa(int maxLin, int maxCol) {
        int alocacao = (int) ((int) (maxCol * maxLin) * 0.01);
        if (alocacao < 10) alocacao = 10;

        mat = new Celula[alocacao];
        naoZeros = 0;
        totalLinhas = maxLin;
        totalColunas = maxCol;
    }

    public double get(int numLinha, int numColuna) {
        for (int i = 0; i < naoZeros; i++) {
            if (mat[i].getLinha() == numLinha && mat[i].getColuna() == numColuna) {
                return mat[i].getValor();
            }
        }
        return 0;
    }

    public void set(double valor, int numLinha, int numColuna) {
        boolean found = false;
        int index = 0;
        try {
            for (int i = 0; i < naoZeros; i++) {
                if (mat[i].getLinha() == numLinha && mat[i].getColuna() == numColuna) {
                    found = true;
                    index = i;
                }
            }
            if (valor == 0 && found) {
                // se o valor for zero e ja tiver uma celula nessa coordenada
                if (index == naoZeros) {
                    // se a celula for a ultima do array, ela eh anulada
                    mat[index] = null;
                } else {
                    // caso contrario, ela troca recebe os dados da ultima celula do array, e esta eh anulada
                    // isso consome menos tempo do que mover todos os elementos uma vez para a esquerda
                    mat[index] = mat[naoZeros];
                    mat[naoZeros] = null;
                    naoZeros--;
                }
            } else {
                if (found) {
                    mat[index] = new Celula(valor, numLinha, numColuna);
                } else {
                    mat[naoZeros] = new Celula(valor, numLinha, numColuna);
                    naoZeros++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

    }

    public double soma() {
        double soma = 0;
        for (int i = 0; i < naoZeros; i++) {
            soma += mat[i].getValor();
        }
        return soma;
    }

    public double min() {
        try {
            double min = mat[0].getValor();
            for (int i = 1; i < naoZeros; i++) {
                if (mat[i].getValor() < min) {
                    min = mat[i].getValor();
                }
            }
            return min;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    public double max() {
        double max = 0;
        for (int i = 0; i < naoZeros; i++) {
            if (max < mat[i].getValor()) {
                max = mat[i].getValor();
            }
        }
        return max;
    }

    public boolean exist(double valor) {
        for (int i = 0; i < naoZeros; i++) {
            if (mat[i].getValor() == valor) {
                return true;
            }
        }
        return false;
    }

    public double coluna(int numCol) {
        double soma = 0;
        double contagem = 0;
        for (int i = 0; i < naoZeros; i++) {
            if (numCol == mat[i].getColuna()) {
                soma += mat[i].getValor();
                contagem++;
            }
        }
        return soma / contagem;
    }

    public double linha(int numLinha) {
        double soma = 0;
        double contagem = 0;
        for (int i = 0; i < naoZeros; i++) {
            if (numLinha == mat[i].getLinha()) {
                soma += mat[i].getValor();
                contagem++;
            }
        }
        return soma / contagem;
    }

    public double[][] copia(int primLin, int primCol, int quantasLinhas, int quantasColunas) {
        try {
            double[][] matrix = new double[quantasLinhas][quantasColunas];
            int i = 0;
            int j = 0;
            for (int linha = primLin; linha < primLin + quantasLinhas; linha++) {
                j = 0;
                for (int coluna = primCol; coluna < primCol + quantasColunas; coluna++) {
                    matrix[i][j] = get(linha, coluna);
                    j++;
                }
                i++;
            }
            return matrix;
        } catch (RuntimeException e) {
            return null;
        }
    }

    public MatrizEsparsa transposta(MatrizEsparsa m) {
        if (m != null) {
            MatrizEsparsa transposta = new MatrizEsparsa(m.getTotalLinhas(), m.getTotalColunas());
            for (int i = 0; i < naoZeros; i++) {
                transposta.set(mat[i].getValor(), mat[i].getColuna(), mat[i].getLinha());
            }
            return transposta;
        }
        return null;
    }

    public void armazena(String nomeArquivo) {
        try (PrintWriter out = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (int i = 0; i < naoZeros; i++) {
                out.println(mat[i].getValor() + "," + mat[i].getLinha() + "," + mat[i].getColuna());
            }
        } catch (IOException e) {
        }
    }

    public void carrega(String nomeArquivo) {
        try (BufferedReader in = new BufferedReader(new FileReader(nomeArquivo))) {
            for (int i = 0; i < mat.length; i++) {
                String dados = in.readLine();
                String[] valores = {"", "", ""};
                int index = 0;
                if (dados != null) {
                    for (int j = 0; j < dados.length(); j++) {
                        if (dados.charAt(j) == ',') {
                            index++;
                        } else {
                            valores[index] += dados.charAt(j);
                        }
                    }
                    set(Double.parseDouble(valores[0]), Integer.parseInt(valores[1]), Integer.parseInt(valores[2]));
                }
            }
        } catch (IOException e) {
        }
    }

    public int getTotalLinhas() {
        return totalLinhas;
    }

    public int getTotalColunas() {
        return totalColunas;
    }

    public int getNaoZeros() {
        return naoZeros;
    }
}
