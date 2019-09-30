package ua.training.dto;

import ua.training.persistance.entities.ReportApproval;
import ua.training.persistance.entities.StateApproval;
import ua.training.persistance.entities.User;

import java.sql.Timestamp;

public class DtoMapper {
    private static DtoMapper instance;

    private DtoMapper() {}

    public static DtoMapper getInstance() {
        if (instance == null) {
            instance = new DtoMapper();
        }
        return instance;
    }

    public SentReportsDto mapToSentReportsDto(ReportApproval reportApproval) {
        final StateApproval stateApproval = reportApproval.getStateApproval();
        final long id = reportApproval.getReport().getId();
        final String state = stateApproval.getState();
        final Timestamp timestamp = reportApproval.getTimestamp();
        final User inspector = reportApproval.getInspector();
        final String  inspectorName = inspector.getFirstName() + " " +inspector.getLastName();
        return new SentReportsDto(id, state, inspectorName, timestamp);
    }
}