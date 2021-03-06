package ua.training.persistence.dao.factory;

import ua.training.persistence.dao.*;
import ua.training.persistence.dao.impl.*;
import ua.training.persistence.db.datasource.MysqlDataSource;
import ua.training.persistence.db.datasource.MysqlDataSourceImpl;

public class MysqlDaoFactory implements DaoFactory {
    private MysqlDataSource mysqlDataSource;
    private static MysqlDaoFactory instance;

    private MysqlDaoFactory() {
        this.mysqlDataSource = MysqlDataSourceImpl.getInstance();
    }

    @Override
    public void setDataSource(MysqlDataSource mysqlDataSource) {
        this.mysqlDataSource = mysqlDataSource;
    }

    public static MysqlDaoFactory getInstance() {
        if (instance == null) {
            instance = new MysqlDaoFactory();
        }
        return instance;
    }

    @Override
    public ITaxTypeDao getTaxTypeDao() {
        final TaxTypeDaoImpl taxTypeDao = TaxTypeDaoImpl.getInstance();
        taxTypeDao.setDataSource(mysqlDataSource);
        return taxTypeDao;
    }

    @Override
    public IUserDao getUserDao() {
        final UserDaoImpl userDao = UserDaoImpl.getInstance();
        userDao.setDataSource(mysqlDataSource);
        return userDao;
    }

    @Override
    public IUserTypeDao getUserTypeDao() {
        final UserTypeDaoImpl userTypeDao = UserTypeDaoImpl.getInstance();
        userTypeDao.setDataSource(mysqlDataSource);
        return userTypeDao;
    }

    @Override
    public IReportDao getReportDao() {
        final ReportDaoImpl reportDaoImpl = ReportDaoImpl.getInstance();
        reportDaoImpl.setDataSource(mysqlDataSource);
        return reportDaoImpl;
    }

    @Override
    public IStateApprovalDao getStateApprovalDao() {
        final StateApprovalDaoImpl iReportDaoIml = StateApprovalDaoImpl.getInstance();
        iReportDaoIml.setDataSource(mysqlDataSource);
        return iReportDaoIml;
    }

    @Override
    public IReportApprovalDao getReportApprovalDao() {
        final ReportApprovalDaoImpl sendReportEventDaoImpl = ReportApprovalDaoImpl.getInstance();
        sendReportEventDaoImpl.setDataSource(mysqlDataSource);
        return sendReportEventDaoImpl;
    }

    @Override
    public IInspectorChangingDao getInspectorChangingDao() {
        final InspectorChangingDaoImpl inspectorChangingDao = InspectorChangingDaoImpl.getInstance();
        inspectorChangingDao.setDataSource(mysqlDataSource);
        return inspectorChangingDao;
    }

}
