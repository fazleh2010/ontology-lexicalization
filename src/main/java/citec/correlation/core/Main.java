/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.correlation.core;

import java.io.IOException;

/**
 *
 * @author elahi
 */
public class Main {

    private String resourceDir = "src/main/resources/";
    private String dictYamlFile = resourceDir + "yamlFiles/" + "dictionary.yml";
    private String qaldJsonFileName = resourceDir + "jsonFiles/" + "adjectiveAll-bafa.json";

    public static void main(String[] args) throws IOException, Exception {
        Main main=new Main();
        Comparision comparision = new Comparision(main.dictYamlFile,main.qaldJsonFileName);
    }

}
