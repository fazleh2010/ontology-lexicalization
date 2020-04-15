
import citec.correlation.core.yaml.GP;
import citec.correlation.core.yaml.KB_TYPE;
import citec.correlation.core.yaml.ObjPredSubj;
import citec.correlation.core.yaml.ParseYaml;
import citec.correlation.core.yaml.TripleElement;
import citec.correlation.core.YamlInput;
import java.io.IOException;
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
public class YamlInputTest {

    private String dir = "src/test/resources/";
    private String fileName = dir + "dictionary.yml";
    private TripleElement subject = null;
    private TripleElement object = null;
    private TripleElement predicate = null;

    public YamlInputTest() throws IOException {
        parseYaml();

    }

    private void parseYaml() throws IOException {
        ParseYaml parseYaml = new ParseYaml(fileName);
        for (String key : parseYaml.getTypeGpls().keySet()) {
            if (key.contains("American")) {
                System.out.println(key);
                assertEquals(key, "American");
                KB_TYPE kb_type = parseYaml.getTypeGpls().get(key);
                List<GP> gpls = kb_type.getGps();
                for (GP gp : gpls) {
                    List<ObjPredSubj> objPredSubs = gp.getObjPredSubj();
                    for (ObjPredSubj objPredSubj : objPredSubs) {
                        subject = objPredSubj.getSubject();
                        object = objPredSubj.getObject();
                        predicate = objPredSubj.getPredicate();
                        break;
                    }
                    break;
                }
                break;
            }

        }
    }

    @Test
    public void testYamlInput_WhenSubject() throws IOException {
        assertEquals(subject.getFirstValue().getValue1(), "?v0");
        assertEquals(subject.getSecondValue().getValue1(), "variable");
    }

    @Test
    public void testYamlInput_WhenObject() throws IOException {
        assertEquals(object.getFirstValue().getValue1(), "?v1");
        assertEquals(object.getSecondValue().getValue1(), "variable");
    }

    @Test
    public void testYamlInput_WhenPredicate() throws IOException {
        assertEquals(predicate.getFirstValue().getValue1(), "uri");
        assertEquals(predicate.getSecondValue().getValue1(), "<http://xmlns.com/foaf/0.1/name>");
    }

}
