package ua.training.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dto.DtoMapper;
import ua.training.dto.ReportDetailsDto;
import ua.training.persistence.dao.factory.MysqlDaoFactory;
import ua.training.persistence.entities.ReportApproval;
import ua.training.util.exceptions.ServiceException;
import ua.training.util.handler.properties.MessagePropertiesHandler;

import static ua.training.util.handler.properties.MessagePropertiesHandler.REPORT_DETAILS_ERROR_MSG;

public class ReportDetailsService {
    private static final Logger log = LogManager.getLogger(ReportDetailsService.class);
    private MysqlDaoFactory daoFactory;

    private static ReportDetailsService instance;

    public static ReportDetailsService getInstance() {
        if (instance == null) {
            instance = new ReportDetailsService();
        }
        return instance;
    }

    private ReportDetailsService() {
        this.daoFactory = MysqlDaoFactory.getInstance();
    }

    public ReportDetailsDto getReportDetails(Long reportApprovalId) {
        final ReportApproval reportApproval = daoFactory
                .getReportApprovalDao()
                .findById(reportApprovalId)
                .orElseThrow(() -> new ServiceException(MessagePropertiesHandler.getMessage(REPORT_DETAILS_ERROR_MSG)));

        return DtoMapper.getInstance().mapToReportDetailsDto(reportApproval);
    }
}
