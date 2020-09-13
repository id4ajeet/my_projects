package com.ajeet.impactt.service.ifc;

import com.ajeet.impactt.persist.entity.CallData;
import com.ajeet.impactt.persist.entity.PhoneBook;
import com.ajeet.impactt.persist.entity.ReportData;
import com.ajeet.impactt.persist.entity.SyncData;
import com.ajeet.impactt.service.dto.*;

import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public interface IEntityDtoMapper {
    PhoneBookDto map(PhoneBook entity);

    PhoneBook map(PhoneBookDto dto);

    PhoneBook map(PhoneBookDto dto, PhoneBook entity);

    CallDataDto map(CallData entity);

    CallData map(CallDataDto dto);

    CallData map(CallDataDto dto, CallData entity);

    SyncDataDto map(SyncData entity);

    SyncData map(SyncDataDto dto);

    SyncData map(SyncDataDto dto, SyncData entity);

    ReportDataDto map(ReportData entity);

    ReportData map(ReportDataDto dto);

    ReportData map(ReportDataDto dto, ReportData entity);

    ReportDto map(List<ReportData> entity, SyncData sync);
}
