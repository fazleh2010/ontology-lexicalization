/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.correlation.core;

import citec.correlation.core.yaml.TripleElement;
import citec.correlation.core.yaml.KB_TYPE;
import citec.correlation.core.yaml.ParseYaml;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author elahi
 */
public class YamlInput {

    private Map<String, KB_TYPE> dictYaml = new HashMap<String, KB_TYPE>();

    public YamlInput(String dictYamlFile) throws IOException {
        this.getInputYaml(dictYamlFile);
    }

    public void getInputYaml(String dictYamlFile) throws IOException {
        ParseYaml parseYaml = new ParseYaml(dictYamlFile);
        for (String key : parseYaml.getTypeGpls().keySet()) {
            //System.out.println(key);
            KB_TYPE kb_type = parseYaml.getTypeGpls().get(key);
            dictYaml.put(key, kb_type);
        }

        //yamlWithStringAndClass( dir + "customer_with_type.yaml");
    }

    private static void yamlWithString(String yamlString) {
        Yaml yaml = new Yaml();
        TripleElement obj = yaml.load(yamlString);
        System.out.println("Loaded object type: " + obj.getClass());
        System.out.println(obj);
    }

    /*Yaml yaml = new Yaml();
        try (InputStream inputStream = new FileInputStream(fileName)) {
            TripleElement obj = yaml.load(inputStream);
            LinkedHashMap<String, String> map = (LinkedHashMap) obj;
            Customer customer = yaml.load(inputStream);
                System.out.println(customer.toString());
            
        }*/
    public String getStringYmlData() {
        return "name: Joe\n"
                + "phone: 111-111-1111\n"
                + "address: Park Dr, Charlie Hill";
    }

    public Map<String, KB_TYPE> getDictYaml() {
        return dictYaml;
    }
}
