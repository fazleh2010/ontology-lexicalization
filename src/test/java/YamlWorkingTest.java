
import citec.correlation.core.ParseYaml;
import citec.correlation.core.YamlWorking;
import java.io.IOException;
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
public class YamlWorkingTest {

    private String dir = "src/main/resources/yamlFiles/";
    private String fileName = dir + "dictionary.yml";
    

    @Test
    public void testYaml() throws IOException {
        YamlWorking yamlWorking = new YamlWorking();
         ParseYaml parseYaml = new ParseYaml(fileName);

    }

}
