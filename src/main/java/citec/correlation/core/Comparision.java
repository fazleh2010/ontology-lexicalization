/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.correlation.core;

import citec.correlation.core.json.DataUnit;
import citec.correlation.core.yaml.GP;
import citec.correlation.core.yaml.KB_TYPE;
import citec.correlation.core.yaml.ObjPredSubj;
import citec.correlation.core.yaml.ParseYaml;
import citec.correlation.core.yaml.TripleElement;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author elahi
 */
public class Comparision {

    public Comparision(String dictYamlFile, String qaldJsonFile) throws IOException, Exception {
        YamlInput yamlInput = new YamlInput(dictYamlFile);
        JsonInput jsonInput = new JsonInput(qaldJsonFile);
        this.comparision(jsonInput.getQaldDataUnits(),yamlInput.getDictYaml());

    }

    private void comparision(Map<String,DataUnit> qaldDataUnits,Map<String, KB_TYPE> dictYaml) {
         for (String adjectiveJson : qaldDataUnits.keySet()) {
              if(dictYaml.containsKey(adjectiveJson)){
                  DataUnit dataUnit=qaldDataUnits.get(adjectiveJson);
                  System.out.println(dataUnit.getMissingTPs());
                  KB_TYPE kb_type=dictYaml.get(adjectiveJson);
                  for(GP gp:kb_type.getGps()){
                      List<ObjPredSubj> objPredSubs = gp.getObjPredSubj();
                    for (ObjPredSubj objPredSubj : objPredSubs) {
                        TripleElement subject = objPredSubj.getSubject();
                        TripleElement object = objPredSubj.getObject();
                        TripleElement predicate = objPredSubj.getPredicate();
                        //System.out.println(object);
                       
                        if(object.getSecondValue().getValue0().contains("uri")){
                            System.out.println("object:"+object);
                        }
                        /*if(predicate.getSecondValue().getValue0().contains("uri")){
                            System.out.println("predicate:"+object);
                        }*/
                        
                        //TripleElement object = objPredSubj.getObject();
                        //TripleElement predicate = objPredSubj.getPredicate();
                        
                    }
                      
                  }
                 
                  break;
              }
        }
    }
}
