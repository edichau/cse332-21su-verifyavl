import org.json.simple.*;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class VerifyAvlTests {

    public static void main(String[] args) throws IOException, ParseException {
        Object data = new JSONParser().parse(new FileReader("tests.json"));
        JSONArray jsonObject = (JSONArray) data;
        boolean passed = true;
        for (int i = 0; i < 3; i++)
        {
            JSONObject test = (JSONObject) jsonObject.get(i);
            boolean result = (boolean) test.get("answer");
            JSONArray tree = (JSONArray) test.get("tree");
            AVLNode root = makeAvl(tree);
            boolean student = verifyAvl.verifyAVL(root);
            if (result != student)
            {
                passed = false;
                System.out.println("Failed at " + tree);
                break;
            }
        }
        if (passed)
        {
            System.out.println("Passed");
        }
    }

    public static AVLNode makeAvl(JSONArray data)
    {
        if (data == null)
        {
            return null;
        }
        AVLNode left = makeAvl((JSONArray) data.get(2));
        AVLNode right = makeAvl((JSONArray) data.get(3));
        int key = (int) (long) data.get(0);
        int height = (int) (long) data.get(1);
        return new AVLNode(key, height, left, right);
    }

}
