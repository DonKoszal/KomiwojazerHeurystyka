package com.AleksanderKubistaPiotrKoszal;

import javax.naming.spi.ResolveResult;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

                    // W=WEJSCIE W GITA
        // write your code here
        Graph myGraph = new Graph(4);
        Result bruteForceResult = BruteForce(myGraph);
        Result heuristicResult = MinEdgeValue(myGraph);
        System.out.println(" BRUTE FROCE wartosc trasy " + bruteForceResult.value + "   lista wieskow" + bruteForceResult.lista);
        System.out.println(" MIN EDGE VALUE wartosc trasy " + heuristicResult.value + "   lista wieskow" + heuristicResult.lista);
    }
    //<editor-fold desc="BRUTEFORCE">

    static Result BruteForce(Graph myGraph) {
        Stack<Result> listOfResults = new Stack<Result>();
        boolean visitedVertices[] = new boolean[myGraph.verticesNum];
        Result firstResult = new Result();
        BFW(myGraph, firstResult,0, 0, visitedVertices, listOfResults);
        return FindMinResult(listOfResults);
    }

    static void BFW(Graph myGraph, Result tmpResult,int prevNum, int startNumber, boolean tmpVertices[], Stack<Result> listOfResults) {
        Result myResult = new Result();
        myResult.lista.addAll(tmpResult.lista);
        myResult.value = tmpResult.value;
        myResult.lista.add(startNumber);
        boolean visitedVertices[] = new boolean[myGraph.verticesNum];
        for (int x = 0; x < myGraph.verticesNum; x++) {
            visitedVertices[x] = tmpVertices[x];
        }
        if(startNumber != prevNum) {
            myResult.value += myGraph.gMatrix[startNumber][prevNum];
        }
        visitedVertices[startNumber] = true;
        boolean didVisted = false;
        for (int i = 0; i < myGraph.verticesNum; i++) {
            if (visitedVertices[i] == false) {
                BFW(myGraph, myResult,startNumber, i, visitedVertices, listOfResults);
                didVisted = true;
            }
        }

        if (!didVisted) {
            //   if(myResult.lista.size() == myGraph.verticesNum) {u
            // znalezlismy cykl
            System.out.println( " start number " + startNumber  + " full value " + myResult.value);
            myResult.value += myGraph.gMatrix[0][startNumber];
            myResult.lista.add(0);
            // dodajemy przez refenrecje do glownego Stacka resultatow
            listOfResults.add(myResult);

            System.out.println( " start number " + startNumber  + " full value " + myResult.value);
            //   }else {
            // nie powinno byc takiej opcji ;)
            //  }
        } else {
        }
    }

    static Result FindMinResult(Stack<Result> myList) {
        Result minResult = myList.pop();
        int i = 0;
        System.out.print(" wartosc trasy " + i + " " + minResult.value + "   lista wieskow" + minResult.lista + "\n");
        for (Result tmpResult : myList) {
            i++;
            System.out.print(" wartosc trasy " + i + " " + tmpResult.value + "   lista wieskow" + tmpResult.lista + "\n");
            if (tmpResult.value < minResult.value) {
                minResult = tmpResult;
            }
        }
        return minResult;
    }
    //</editor-fold>

    // HEURYSTICS
    static Result MinEdgeValue(Graph myGraph){
        Result myResult = new Result();
        boolean visitedVertices[] = new boolean[myGraph.verticesNum];
        int startNumber = 0; // wieszhcolek z ktorego zaczynamy szukanie
        int tmpVertice = 0;
        int tmpMinEdgeValue;
        myResult.lista.add(startNumber);
        myResult.value = 0;
        while(myResult.lista.size() != myGraph.verticesNum){
            tmpMinEdgeValue = myGraph.edgeNum + 2; // krawdedzie nigdy nei beda mialy wiekszej wartosci poniewaz tak je zdefiniowalismy
            visitedVertices[startNumber] = true;
            for (int i = 0; i < myGraph.verticesNum; i++) {
                if (visitedVertices[i] == false) {
                    if( myGraph.gMatrix[startNumber][i] < tmpMinEdgeValue){
                        tmpMinEdgeValue = myGraph.gMatrix[startNumber][i];
                        tmpVertice = i;
                      //  System.out.println("tmpMin " +tmpMinEdgeValue + "start number " + startNumber + " i " + i);
                    }
                }
            }
         //   System.out.println("min "  +tmpVertice );
            startNumber = tmpVertice;
            visitedVertices[tmpVertice] = true;
            myResult.lista.add(tmpVertice);
            myResult.value += tmpMinEdgeValue;
        }
        myResult.lista.add(0);
        myResult.value += myGraph.gMatrix[0][tmpVertice];
        return myResult;
    }
}
