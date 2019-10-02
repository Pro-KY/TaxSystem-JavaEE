package ua.training.persistence.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.persistence.dao.IReportApprovalDao;
import ua.training.persistence.dao.jdbc.JdbcTemplate;
import ua.training.persistence.dao.mappers.impl.*;
import ua.training.persistence.db.datasource.MysqlDataSource;
import ua.training.persistence.entities.ReportApproval;
import ua.training.util.exceptions.DataAccessException;
import ua.training.util.exceptions.PersistenceException;
import ua.training.util.handler.properties.SqlPropertiesHandler;

import java.util.List;
import java.util.Optional;

import static ua.training.util.handler.properties.SqlPropertiesHandler.SAVE_REPORT_APPROVAL;

public class ReportApprovalDaoImpl implements IReportApprovalDao {
    private static ReportApprovalDaoImpl instance;
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LogManager.getLogger(ReportApprovalDaoImpl.class);

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
    public Long save(ReportApproval reportApproval) {
        String sql = SqlPropertiesHandler.getSqlQuery(SAVE_REPORT_APPROVAL);
//        final JdbcTemplate jdbcTemplate = JdbcTemplate.getIn/stance();
        Object[] params = {
                reportApproval.getTimestamp(),
                reportApproval.getStateApproval().getId(),
                reportApproval.getUser().getId(),
                reportApproval.getReport().getId()
        };

        try {
            return jdbcTemplate.saveOrUpdate(sql, params);
        } catch (DataAccessException e) {
            logger.debug("exp here_2");
            e.printStackTrace();
            // log
            throw new PersistenceException("", e);
        }
    }

    public long countAllRows() {
        return jdbcTemplate.countRows(SqlPropertiesHandler.getSqlQuery(SqlPropertiesHandler.REPORT_APPROVAL_COUNT));
    }

    @Override
    public List<ReportApproval> getPaginationList(long pageSize, long offSet, long userId) { // pageSize, offset
        String sql = SqlPropertiesHandler.getSqlQuery(SqlPropertiesHandler.REPORT_APPROVAL_PAGINATION);

        final ReportApprovalMapper reportApprovalMapper = new ReportApprovalMapper(true);
        reportApprovalMapper.mapStateApprovalRelation(new StateApprovalMapperImpl(true));
        reportApprovalMapper.mapUserRelation(new UserMapperImpl(true, true));

        return jdbcTemplate.finAll(sql, reportApprovalMapper, userId, pageSize, offSet);
    }

    @Override
    public Long update(ReportApproval entity) {
        return 0L;
    }

    @Override
    public boolean delete(ReportApproval entity) {
        return false;
    }

    @Override
    public Optional<ReportApproval> findById(Long id) {
        String sql = SqlPropertiesHandler.getSqlQuery(SqlPropertiesHandler.REPORT_APPROVAL_BY_ID);

        final ReportApprovalMapper reportApprovalMapper = new ReportApprovalMapper(true);

        final ReportMapperImpl reportMapper = new ReportMapperImpl(true);
        reportMapper.mapTaxTypeRelation(new TaxTypeMapperIml(true));
        reportApprovalMapper.mapReportRelation(reportMapper);
        reportApprovalMapper.mapStateApprovalRelation(new StateApprovalMapperImpl(true));
        reportApprovalMapper.mapInspectorRelation(new UserMapperImpl(true, true));

        return jdbcTemplate.findByQuery(sql, reportApprovalMapper, id);
    }
}
