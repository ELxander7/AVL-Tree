package ru.itis.aisd304.avltree;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try {
            TestGenerator.testGenerate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AVLTree avlTree = new AVLTree();
        Random random = new Random();

        long totalInsertTime = 0;
        long totalDeleteTime = 0;
        long totalSearchTime = 0;
        int totalInsertOperations = 0;
        int totalDeleteOperations = 0;
        int totalSearchOperations = 0;

        for (int i = 0; i < 10000; i++) {
            long startTime = System.nanoTime();
            avlTree.node = avlTree.insertNode(avlTree.node, i);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            totalInsertTime += elapsedTime;
            totalInsertOperations += avlTree.insertOperations;
            System.out.println("Insertion of " + i + ": Time = " + elapsedTime + " ns, Operations = " + avlTree.insertOperations);

        }
        double averageInsertTime = (double) totalInsertTime / 10000;
        double averageInsertOperations = (double) totalInsertOperations / 10000;


        for (int i = 0; i < 1000; i++) {
            int num = random.nextInt(10000);
            long startTime = System.nanoTime();
            avlTree.node = avlTree.deleteNode(avlTree.node, num);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            System.out.println("Deletion of " + num + ": Time = " + elapsedTime + " ns, Operations = " + avlTree.deleteOperations);
            totalDeleteTime += elapsedTime;
            totalDeleteOperations += avlTree.deleteOperations;
        }
        double averageDeleteTime = (double) totalDeleteTime / 1000;
        double averageDeleteOperations = (double) totalDeleteOperations / 1000;


        for (int i = 0; i < 100; i++) {
            int num = random.nextInt(10000);
            long startTime = System.nanoTime();
            avlTree.searchNode(avlTree.node, num);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            totalSearchTime += elapsedTime;
            totalSearchOperations += avlTree.searchOperations;
            System.out.println("Search for " + num + ": Time = " + elapsedTime + " ns, Operations = " + avlTree.searchOperations);
        }
        double averageSearchTime = (double) totalSearchTime / 100;
        double averageSearchOperations = (double) totalSearchOperations / 100;

        System.out.println("Average Insertion Time: " + averageInsertTime + " ns, Operations: " + averageInsertOperations);
        System.out.println("Average Deletion Time: " + averageDeleteTime + " ns, Operations: " + averageDeleteOperations);
        System.out.println("Average Search Time: " + averageSearchTime + " ns, Operations: " + averageSearchOperations);
    }
}
