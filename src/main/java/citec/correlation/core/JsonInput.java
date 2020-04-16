/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.correlation.core;

import citec.correlation.core.json.DataSet;
import citec.correlation.core.json.DataSet;
import citec.correlation.core.json.DataUnit;
import citec.correlation.core.json.DataUnit;
import static citec.correlation.core.constants.Constants.UNICODE;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author elahi
 */
public class JsonInput {

    private Map<String, List<DataUnit>> qaldDataUnits = new HashMap<String, List<DataUnit>>();
    private Set<String> adjectives = new HashSet<String>();

    public JsonInput(String qaldJsonFile) throws Exception {
        getInputJson(qaldJsonFile);
    }

    public static void main(String[] args) throws IOException, Exception {
    }

    public void getInputJson(String qaldJsonFile) throws IOException, Exception {
        InputStream inputStream = new FileInputStream(qaldJsonFile);
        List<DataUnit> dataUnits = parseJson(inputStream);
        for (DataUnit dataUnit : dataUnits) {
            for(String adjective: dataUnit.getAdjectives()){
                if (qaldDataUnits.containsKey(adjective)) {
                    List<DataUnit> dataUnitsList = qaldDataUnits.get(adjective);
                    dataUnitsList.add(dataUnit);
                    qaldDataUnits.put(adjective, dataUnitsList);
                } else {
                    List<DataUnit> dataUnitsList = new ArrayList<DataUnit>();
                    dataUnitsList.add(dataUnit);
                    qaldDataUnits.put(adjective, dataUnitsList);
                }
                
            }
           
                //adjectives.add(adjective.toLowerCase());
               
           

        }

    }

    public List<DataUnit> parseJson(InputStream inputStream) {
        DataSet dataSet = null;
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString;
        try {
            jsonString = IOUtils.toString(inputStream, UNICODE);
            dataSet = objectMapper.readValue(jsonString, DataSet.class);
        } catch (IOException ex) {
            Logger.getLogger(JsonInput.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataSet.getQuestions();
    }

    public List<DataUnit> getQaldDataUnits(String adjective) {
        return qaldDataUnits.get(adjective);
    }

    public Map<String, List<DataUnit>> getQaldDataUnits() {
        return qaldDataUnits;
    }

}
