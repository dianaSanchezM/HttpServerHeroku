/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.httpserver;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

/**
 *
 * @author diana
 */
public class httpServer {
    public static void main(String args[]) {
        new httpServer().startServer();
    }

    public void startServer() {
        final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);
        try {
            ServerSocket serverSocket = new ServerSocket(new Integer(System.getenv("PORT")), 0, InetAddress.getByName("localhost"));
            System.out.println("Waiting for clients to connect...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientProcessingPool.submit(new thread(clientSocket));
            }

        } catch (IOException e) {
            System.err.println("Unable to process client request");
        }

    }
}
