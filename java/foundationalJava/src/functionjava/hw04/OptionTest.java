package functionjava.hw04;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import static org.junit.Assert.*;

/**
 * Created by xqy on 18/1/24.
 */
public class OptionTest {
    public static void main(String[] args){
        List<Optional<String>> names = new ArrayList<>();
        names.add(Optional.of("Dean"));
        names.add(Optional.ofNullable(null));
        names.add(Optional.of("Wampler"));

        String[] expected = {"Dean", "Unknown!", "Wampler"};
        System.out.println("*** Using getOrElse:");
        for (int i = 0; i < names.size(); i++) {
            Optional<String> name = names.get(i);
            String value = name.orElseGet(()->"Unknown!");
            System.out.println(name + ": " + value);
//            assertEquals(expected[i], value);
        }
    }
}
