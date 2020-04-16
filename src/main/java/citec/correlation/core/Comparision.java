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
import citec.correlation.core.yaml.TripleElement;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import static org.apache.coyote.http11.Constants.a;

/**
 *
 * @author elahi
 */
public class Comparision {

    public Comparision(String dictYamlFile, String qaldJsonFile) throws IOException, Exception {
        JsonInput jsonInput = new JsonInput(qaldJsonFile);
        YamlInput yamlInput = new YamlInput(dictYamlFile);
        this.comparision(jsonInput.getQaldDataUnits(), yamlInput.getDictYaml());

    }

    private void comparision(Map<String, List<DataUnit>> qaldDataUnits, Map<String, KB_TYPE> dictYaml) {
        Set<String> commonSet = Sets.intersection(qaldDataUnits.keySet(), dictYaml.keySet());

        for (String adjectiveJson : commonSet) {
            List<DataUnit> dataUnits = qaldDataUnits.get(adjectiveJson);
            for (DataUnit dataUnit : dataUnits) {
               // System.out.println(dataUnit);
                if (dictYaml.containsKey(adjectiveJson)) {
                    Set<String> yamlUris = new HashSet<String>();
                    KB_TYPE kb_type = dictYaml.get(adjectiveJson);
                    yamlUris = getYamlUris(kb_type, yamlUris);
                    Set<String> jsonUris = this.getJsonSet(dataUnit.getMissingTPs());
                    System.out.println(adjectiveJson);
                    System.out.println(yamlUris);
                    System.out.println(jsonUris);
                    Set<String> commonUrls = Sets.intersection(yamlUris, jsonUris);
                    System.out.println(commonUrls);

                }

            }
           
        }
    }

    private Set<String> getYamlUris(KB_TYPE kb_type, Set<String> jsonUris) {
        for (GP gp : kb_type.getGps()) {
            List<ObjPredSubj> objPredSubs = gp.getObjPredSubj();
            for (ObjPredSubj objPredSubj : objPredSubs) {
                TripleElement subject = objPredSubj.getSubject();
                TripleElement object = objPredSubj.getObject();
                String type = null;
                TripleElement predicate = objPredSubj.getPredicate();
                if (object.getFirstValue().getValue0().contains("type")) {
                    type = this.getLastSegment(object.getSecondValue().getValue1());
                    jsonUris.add(type);
                }
                if (predicate.getFirstValue().getValue0().contains("type")) {
                    type = this.getLastSegment(predicate.getSecondValue().getValue1());
                    jsonUris.add(type);
                }
                if (subject.getFirstValue().getValue0().contains("type")) {
                    type = this.getLastSegment(subject.getSecondValue().getValue1());
                    jsonUris.add(type);
                }
            }

        }
        return jsonUris;
    }

    private String comparision(Set<String> yamlUris, Set<String> jsonUris) {
        System.out.println("yaml:" + yamlUris);
        System.out.println("json:" + jsonUris);
        for (String uri : jsonUris) {
            if (yamlUris.contains(uri)) {
                return uri;
            }
        }
        return null;
    }

    private String getLastSegment(String uri) {
        Path path = Paths.get(uri);
        return path.getFileName().toString().replace(">", "").toLowerCase();
    }

    private Set<String> getJsonSet(List<String> missingTPs) {
        Set<String> yamlUris = new HashSet<String>();
        for (String uri : missingTPs) {
            yamlUris.add(getLastSegment(uri));
        }
        return yamlUris;
    }
}
