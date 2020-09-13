
package com.ajeet.impactt.service.impl;

import com.ajeet.impactt.persist.entity.ReportData;
import com.ajeet.impactt.persist.entity.SyncData;
import com.ajeet.impactt.persist.repository.ReportDataRepository;
import com.ajeet.impactt.persist.repository.SyncDataRepository;
import com.ajeet.impactt.service.dto.ReportDto;
import com.ajeet.impactt.service.ifc.ICdrReportService;
import com.ajeet.impactt.service.ifc.IEntityDtoMapper;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class CdrReportService implements ICdrReportService {

    @Autowired
    private ReportDataRepository reportDataRepository;

    @Autowired
    private SyncDataRepository syncDataRepository;

    @Autowired
    private IEntityDtoMapper mapper;

    @Override
    public ReportDto generateAll() {
        Iterable<ReportData> all = reportDataRepository.findAll();
        List<ReportData> result = new ArrayList<>();
        all.forEach(result::add);

        SyncData syncData = syncDataRepository.findTopByOrderByIdDesc();
        return mapper.map(result, syncData);
    }

    @Override
    public ReportDto generate(String uuid) {
        Optional<ReportData> data = reportDataRepository.findById(uuid);
        List<ReportData> result = new ArrayList<>();
        if (data.isPresent()) {
            result.add(data.get());
        }
        SyncData syncData = syncDataRepository.findTopByOrderByIdDesc();
        return mapper.map(result, syncData);
    }

}
