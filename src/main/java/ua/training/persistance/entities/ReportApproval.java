package ua.training.persistance.entities;


import java.io.Serializable;
import java.sql.Timestamp;

public class ReportApproval implements Serializable {
    private Long id;
    private Timestamp timestamp;
    private String refusalCause;

    private StateApproval stateApproval;
    private Report report;
    private User user;
    private User inspector;

    public ReportApproval(Long id) {
        this.id = id;
    }

    public ReportApproval(Timestamp timestamp, StateApproval stateApproval, User user, Report report) {
        this.timestamp = timestamp;
        this.stateApproval = stateApproval;
        this.report = report;
        this.user = user;
    }

    public ReportApproval(Long id, Timestamp timestamp, String refusalCause, StateApproval stateApproval, Report report, User user, User inspector) {
        this.id = id;
        this.timestamp = timestamp;
        this.refusalCause = refusalCause;
        this.stateApproval = stateApproval;
        this.report = report;
        this.user = user;
        this.inspector = inspector;
    }

    public StateApproval getStateApproval() {
        return stateApproval;
    }

    public void setStateApproval(StateApproval stateApproval) {
        this.stateApproval = stateApproval;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getInspector() {
        return inspector;
    }

    public void setInspector(User inspector) {
        this.inspector = inspector;
    }


    //    private Long stateApprovalId;
//    private Long reportId;
//    private Long userId;
//    private Long inspectorId;

//    public ReportApproval(Long id, Timestamp timestamp, String refusalCause, Long stateApprovalId, Long reportId, Long userId, Long inspectorId) {
//        this.id = id;
//        this.timestamp = timestamp;
//        this.refusalCause = refusalCause;
//        this.stateApprovalId = stateApprovalId;
//        this.reportId = reportId;
//        this.userId = userId;
//        this.inspectorId = inspectorId;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public Long getInspectorId() {
//        return inspectorId;
//    }
//
//    public void setInspectorId(Long inspectorId) {
//        this.inspectorId = inspectorId;
//    }
//
//    public Long getStateApprovalId() {
//        return stateApprovalId;
//    }
//
//    public void setStateApprovalId(Long stateApprovalId) {
//        this.stateApprovalId = stateApprovalId;
//    }
//
//    public Long getReportId() {
//        return reportId;
//    }
//
//    public void setReportId(Long reportId) {
//        this.reportId = reportId;
//    }
    public ReportApproval() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getRefusalCause() {
        return refusalCause;
    }

    public void setRefusalCause(String refusalCause) {
        this.refusalCause = refusalCause;
    }


    @Override
    public String toString() {
        return "ReportApproval{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", refusalCause='" + refusalCause + '\'' +
//                ", reportStateId=" + reportStateId +
//                ", reportId=" + reportId +
                '}';
    }
}
