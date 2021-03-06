package ua.training.persistence.entities;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportApproval that = (ReportApproval) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(refusalCause, that.refusalCause) &&
                Objects.equals(stateApproval, that.stateApproval) &&
                Objects.equals(report, that.report) &&
                Objects.equals(user, that.user) &&
                Objects.equals(inspector, that.inspector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, refusalCause, stateApproval, report, user, inspector);
    }

    @Override
    public String toString() {
        return "ReportApproval{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", refusalCause='" + refusalCause + '\'' +
                ", stateApproval=" + (stateApproval != null ?  stateApproval : "") +
                ", report=" + (report != null ?  report : "") +
                ", user=" + (user != null ?  user : "") +
                ", inspector=" + (inspector != null ?  inspector : "") +
                '}';
    }
}
