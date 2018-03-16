/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.httpserver;

import java.io.*;
import java.net.*;
import java.nio.file.*;

/**
 *
 * @author diana
 */
public class thread extends Thread {
    
    public Socket client;

    public thread(Socket client) {
        this.client= client;
    }
    
    @Override
    public void run() {
        String address = "";
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));
            String inputLine;
            String[] prueba = {};
            while ((inputLine = in.readLine()) != null) {
                prueba = inputLine.split(" ");
                if (prueba[0].equals("GET")) {
                    address = prueba[1];
                }
                if (!in.ready()) {
                    break;
                }
            }
        } catch (Exception e) {
        }
        File fi;
        byte[] fileContent;
        fi = new File("resources/" + address.substring(1));
        String abs = fi.getAbsolutePath();

        try {
            fileContent = Files.readAllBytes(Paths.get(abs));
            client.getOutputStream().write(fileContent);
            in.close();
            client.close();
        } catch (Exception e) {
            fi = new File("resources/index.html");
            abs = fi.getAbsolutePath();
            try {
                fileContent = Files.readAllBytes(Paths.get(abs));
                client.getOutputStream().write(fileContent);
                in.close();
                client.close();
            } catch (IOException ex) {
            }
        }
    }
    
}
