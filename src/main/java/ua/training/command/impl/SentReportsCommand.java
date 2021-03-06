package ua.training.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.command.ICommand;
import ua.training.util.command.CommandUtil;
import ua.training.dto.PaginationDto;
import ua.training.persistence.entities.User;
import ua.training.service.ReportApprovalService;
import ua.training.util.constans.Attributes;
import ua.training.util.constans.Parameters;
import ua.training.util.properties.ViewProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.training.util.properties.ViewProperties.FRAGMENT_PATH_SENT_REPORTS;
import static ua.training.util.properties.ViewProperties.PATH_MAIN;

public class SentReportsCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SentReportsCommand.class);
    private ReportApprovalService reportApprovalService;

    public SentReportsCommand() {
        this.reportApprovalService = ReportApprovalService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("in SentReportsCommand");
        request.setAttribute(Attributes.FRAGMENT_PATH, ViewProperties.getViewPath(FRAGMENT_PATH_SENT_REPORTS));
        final HttpSession session = request.getSession();
        final PaginationDto currentPaginationDto = CommandUtil.getInstance().getCurrentPaginationDto(session);
        currentPaginationDto.setPaginationRequestContent(request);
        final User user = (User) session.getAttribute(Attributes.USER);

        PaginationDto updatedPaginationDto = reportApprovalService.getReportsApprovalForUser(currentPaginationDto, user.getId());
        log.info("newPaginationDto session: {}", currentPaginationDto.toString());

        session.setAttribute(Attributes.PAGINATION_INFO, updatedPaginationDto);
        log.info("sidebarIndex {}", request.getParameter("sideBarIndex"));

        final String sideBarIndex = request.getParameter(Parameters.SIDEBAR_ACTIVE_INDEX);
        if (sideBarIndex != null) {
            session.setAttribute(Attributes.SIDEBAR_ACTIVE_INDEX, request.getParameter(Parameters.SIDEBAR_ACTIVE_INDEX));
        }

        return ViewProperties.getViewPath(PATH_MAIN);
    }
}
