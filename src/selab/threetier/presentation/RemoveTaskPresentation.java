package selab.threetier.presentation;

import org.json.JSONObject;
import selab.threetier.logic.Entity;
import selab.threetier.logic.Task;
import selab.threetier.storage.EntityStorage;
import selab.threetier.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RemoveTaskPresentation extends JSONPresentation {

    @Override
    public JSONObject getData(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        JSONObject request = new JSONObject(new BufferedReader(new InputStreamReader(body)).lines().collect(Collectors.joining("\n")));

        int id = request.getInt("id");
//        System.out.println(id);
        System.out.println(Task.getAll());
        ArrayList<Task> tasks =  Task.getAll();
        for (Task tt: tasks
             ) {
            if (tt.getId() == id){
                tt.delete();
                break;
            }

        }
        System.out.println(Task.getAll());
        // TODO: Add codes here to delete a task with the id
        Map<String, String> result = new HashMap<>();
        result.put("success", "true");
        return new JSONObject(result);
    }
}
