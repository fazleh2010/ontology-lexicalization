
import citec.correlation.core.json.DataUnit;
import citec.correlation.core.json.JsonInput;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elahi
 */
public class JsonInputTest {

    private String resourceDir = "src/test/resources/";
    private String qaldJsonFile = resourceDir + "adjectiveAll-bafa.json";

    public JsonInputTest() throws IOException, Exception {

    }

    @Test
    public void parseJson() throws IOException, Exception {
        InputStream inputStream = new FileInputStream(qaldJsonFile);
        JsonInput jsonInput=new JsonInput();
        List<DataUnit> dataUnits = jsonInput.parseJson(inputStream);
        for (DataUnit dataUnit : dataUnits) {
            System.out.println(dataUnit);
            String foundAdjective=dataUnit.getAdjectives().iterator().next();
            String questions=dataUnit.getQuestions();
            assertEquals(foundAdjective, "German"); 
            assertEquals(questions, "Which German cities have more than 250000 inhabitants?");
        }
    }

}
