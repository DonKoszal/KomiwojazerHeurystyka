package com.AleksanderKubistaPiotrKoszal;

import java.util.Random;

/**
 * Created by wilek on 2015-10-18.
 */
public class Graph
{
    int verticesNum, edgeNum;
    int gMatrix[][];
    public Graph(int v) {
        verticesNum = v;
        edgeNum = (v - 1) * v / 2;  // ze wzoru na liczbe krawdzei w grafie pelnym
        gMatrix = new int[v][v];
        Random r = new Random();
        // tworzenie tablicy
        for(int i = 0; i < v; i++ ){
            for(int l = v-1; l >= i; l--){
                if( i == l){
                    gMatrix[i][l] = 0;
                }else {
                    gMatrix[i][l] =    gMatrix[l][i] =  r.nextInt(edgeNum + 1)+1;
                }
            }
        }
    // czytanie tablicy
        for(int i = 0; i < v; i++ ){
            for(int l = 0; l < v; l++){
               System.out.print(gMatrix[i][l] + " ");
            }
            System.out.println();
        }
    }

}
