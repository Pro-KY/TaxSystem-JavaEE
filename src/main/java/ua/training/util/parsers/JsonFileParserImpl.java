package ua.training.util.parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.persistence.entities.Report;
import ua.training.util.exceptions.FileParsingException;

/**
 * Implementation of {@link FileParser} for parsing JSON file content
 */
public class JsonFileParserImpl implements FileParser<Report> {
    private static final Logger log = LogManager.getLogger(JsonFileParserImpl.class);

    @Override
    public Report parseFile(String jsonContent) throws FileParsingException {
        Report report = null;

        try {
            if (jsonContent != null && !jsonContent.isEmpty()) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                report = gson.fromJson(jsonContent, Report.class);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new FileParsingException("error occur during json file parsing");
        }

        return report;
    }
}
