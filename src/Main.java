import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import input.Input;
import pages.Monitor;
import utils.Database;
import utils.Helper;

import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Input inputData = objectMapper.readValue(new File(args[0]),
                Input.class);

        //initialize users
        Database.getDataBase().initializeUserHashMap(inputData);
        //initialize movies
        Database.getDataBase().initializeMovies(inputData);

        Monitor.getMonitor().startAplication();
        ArrayNode output = objectMapper.createArrayNode();

        Database.getDataBase().setOutput(output);
        Database.getDataBase().setObjectMapper(objectMapper);
        Helper.executeAction(inputData);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }

}
