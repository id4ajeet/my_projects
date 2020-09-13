package com.ajeet.impactt.service.ifc;

import com.ajeet.impactt.service.dto.ReportDto;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public interface ICdrReportService {
    ReportDto generateAll();

    ReportDto generate(String uuid);
}
