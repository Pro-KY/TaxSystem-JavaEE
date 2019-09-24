package ua.training.command.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

public class CommandParamsExtractor {
//    public static <T> Optional<T>  extractParamsIntoBean(Function<HttpServletRequest, Optional<T>> function, HttpServletRequest request) {
//        return function.apply(request);
//    }

    public static <T> Optional<T> extractParamsIntoBean(HttpServletRequest request, Class<T> classOfT) {
        Optional<T> optional;
        JsonObject requestJson = new JsonObject();

        // iterate over all the paramters
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            // add every item as a property to the json
            requestJson.addProperty(entry.getKey(), entry.getValue()[0]);
        }

        try {
            T dto = new Gson().fromJson(requestJson, classOfT);
            optional = Optional.of(dto);
        } catch (JsonSyntaxException e) {
            optional = Optional.empty();
            e.printStackTrace();
        }

        return optional;
    }
//        return function.apply(request);
//    }
}