package ua.training.persistence.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.persistence.dao.IReportApprovalDao;
import ua.training.persistence.dao.jdbc.JdbcTemplate;
import ua.training.persistence.dao.mappers.impl.*;
import ua.training.persistence.db.datasource.MysqlDataSource;
import ua.training.persistence.entities.ReportApproval;
import ua.training.persistence.entities.StateApproval;
import ua.training.persistence.entities.User;
import ua.training.util.properties.SqlProperties;

import java.util.List;
import java.util.Optional;

import static ua.training.util.properties.SqlProperties.*;

public class ReportApprovalDaoImpl implements IReportApprovalDao {
    private static ReportApprovalDaoImpl instance;
    private JdbcTemplate jdbcTemplate;
    private static final Logger log = LogManager.getLogger(ReportApprovalDaoImpl.class);

    public void setDataSource(MysqlDataSource dataSource) {
        jdbcTemplate.setDataSource(dataSource);
    }

    private ReportApprovalDaoImpl() {
        jdbcTemplate = JdbcTemplate.getInstance();
    }

    public static ReportApprovalDaoImpl getInstance() {
        if (instance == null) {
            instance = new ReportApprovalDaoImpl();
        }
        return instance;
    }

    @Override
    public Long save(ReportApproval entity) {
        String sql = SqlProperties.getSqlQuery(SAVE_REPORT_APPROVAL);
        Object[] params = {
                entity.getTimestamp(),
                entity.getStateApproval().getId(),
                entity.getUser().getId(),
                entity.getReport().getId()
        };

        return jdbcTemplate.saveOrUpdate(sql, params);
    }

    public long countAllbyUserId(Long userId) {
        return jdbcTemplate.countRows(SqlProperties.getSqlQuery(SqlProperties.REPORT_APPROVAL_COUNT_FOR_USER), userId);
    }

    @Override
    public long countAllByStateApproval(StateApproval stateApproval) {
        return jdbcTemplate.countRows(SqlProperties.getSqlQuery(SqlProperties.COUNT_ALL_REPORT_APPROVAL_BY_STATE_APPROVAL), stateApproval.getId());
    }

    @Override
    public long countAllByStateApprovalAndInspector(StateApproval stateApproval, User user) {
        final String sqlQuery = SqlProperties.getSqlQuery(SqlProperties.COUNT_ALL_REPORT_APPROVAL_BY_STATE_APPROVAL_AND_INSPECTOR_ID);
        return jdbcTemplate.countRows(sqlQuery, stateApproval.getId(), user.getId());
    }

    @Override
    public List<ReportApproval> getReportApprovalListByUserId(long pageSize, long offSet, long userId) {
        String sql = SqlProperties.getSqlQuery(SqlProperties.REPORT_APPROVAL_FOR_USER);

        final ReportApprovalMapper reportApprovalMapper = new ReportApprovalMapper(true);
        reportApprovalMapper.mapStateApprovalRelation(new StateApprovalMapperImpl(true));
        reportApprovalMapper.mapInspectorRelation(new UserMapperImpl(true, true));

        return jdbcTemplate.finAll(sql, reportApprovalMapper, userId, pageSize, offSet);
    }

    @Override
    public List<ReportApproval> getReportApprovalListByStateApproval(long pageSize, long offSet, StateApproval stateApproval) {
        String sql = SqlProperties.getSqlQuery(SqlProperties.REPORT_APPROVAL_BY_APPROVAL_STATE);

        final ReportApprovalMapper reportApprovalMapper = new ReportApprovalMapper(true);
        reportApprovalMapper.mapStateApprovalRelation(new StateApprovalMapperImpl(true));
        reportApprovalMapper.mapUserRelation(new UserMapperImpl(true, false));

        return jdbcTemplate.finAll(sql, reportApprovalMapper, stateApproval.getId(), pageSize, offSet);
    }

    @Override
    public List<ReportApproval> getReportApprovalListByStateAndInspector(long pageSize, long offSet, StateApproval stateApproval, User inspector) {
        String sql = SqlProperties.getSqlQuery(SqlProperties.REPORT_APPROVAL_BY_APPROVAL_STATE_AND_INSPECTOR_ID);

        final ReportApprovalMapper reportApprovalMapper = new ReportApprovalMapper(true);
        reportApprovalMapper.mapStateApprovalRelation(new StateApprovalMapperImpl(true));
        reportApprovalMapper.mapUserRelation(new UserMapperImpl(true, false));

        return jdbcTemplate.finAll(sql, reportApprovalMapper, stateApproval.getId(), inspector.getId(), pageSize, offSet);
    }

    @Override
    public Long update(ReportApproval entity) {
        String sql = SqlProperties.getSqlQuery(SqlProperties.UPDATE_REPORT_APPROVAL_BY_ID);
        Object[] params = {
                entity.getTimestamp(),
                entity.getRefusalCause(),
                entity.getStateApproval().getId(),
                entity.getReport().getId(),
                entity.getUser().getId(),
                entity.getInspector() != null && entity.getInspector().getId() > 0 ? entity.getInspector().getId() : null,
                entity.getId()
        };

        return jdbcTemplate.saveOrUpdate(sql, params);
    }

    @Override
    public boolean delete(ReportApproval entity) {
        String sql = SqlProperties.getSqlQuery(DELETE_REPORT_APPROVAL_BY_ID);
        return jdbcTemplate.delete(sql, entity.getId());
    }

    public Optional<ReportApproval> findByIdJoinReportJoinInspector(Long id) {
        String sql = SqlProperties.getSqlQuery(SqlProperties.REPORT_APPROVAL_JOIN_REPORT_JOIN_INSPECTOR);

        final ReportApprovalMapper reportApprovalMapper = new ReportApprovalMapper(true);

        final ReportMapperImpl reportMapper = new ReportMapperImpl(true);
        reportMapper.mapTaxTypeRelation(new TaxTypeMapperIml(true));
        reportApprovalMapper.mapReportRelation(reportMapper);
        reportApprovalMapper.mapStateApprovalRelation(new StateApprovalMapperImpl(true));
        reportApprovalMapper.mapInspectorRelation(new UserMapperImpl(true, true));
        return jdbcTemplate.findByQuery(sql, reportApprovalMapper, id);
    }

    public Optional<ReportApproval> findByIdJoinReportJoinUser(Long id) {
        String sql = SqlProperties.getSqlQuery(SqlProperties.REPORT_APPROVAL_JOIN_REPORT_JOIN_USER);

        final ReportApprovalMapper reportApprovalMapper = new ReportApprovalMapper(true);

        final ReportMapperImpl reportMapper = new ReportMapperImpl(true);
        reportMapper.mapTaxTypeRelation(new TaxTypeMapperIml(true));
        reportApprovalMapper.mapReportRelation(reportMapper);
        reportApprovalMapper.mapStateApprovalRelation(new StateApprovalMapperImpl(true));
        reportApprovalMapper.mapUserRelation(new UserMapperImpl(true, false));
        return jdbcTemplate.findByQuery(sql, reportApprovalMapper, id);

    }

    public Optional<ReportApproval> findByIdJoinUser(Long id) {
        String sql = SqlProperties.getSqlQuery(SqlProperties.REPORT_APPROVAL_JOIN_USER);
        final ReportApprovalMapper reportApprovalMapper = new ReportApprovalMapper(true);
        reportApprovalMapper.mapUserRelation(new UserMapperImpl(true, false));
        return jdbcTemplate.findByQuery(sql, reportApprovalMapper, id);
    }

    @Override
    public Optional<ReportApproval> findById(Long id) {
        String sql = SqlProperties.getSqlQuery(SqlProperties.FIND_REPORT_APPROVAL_BY_ID);
        final ReportApprovalMapper reportApprovalMapper = new ReportApprovalMapper(false);
        return jdbcTemplate.findByQuery(sql, reportApprovalMapper, id);
    }
}
