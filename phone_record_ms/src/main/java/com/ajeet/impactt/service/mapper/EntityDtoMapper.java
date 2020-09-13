
package com.ajeet.impactt.service.mapper;

import com.ajeet.impactt.persist.entity.CallData;
import com.ajeet.impactt.persist.entity.PhoneBook;
import com.ajeet.impactt.persist.entity.ReportData;
import com.ajeet.impactt.persist.entity.SyncData;
import com.ajeet.impactt.service.dto.*;
import com.ajeet.impactt.service.ifc.IEntityDtoMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class EntityDtoMapper implements IEntityDtoMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PhoneBookDto map(PhoneBook entity) {
        return modelMapper.map(entity, PhoneBookDto.class);
    }

    @Override
    public PhoneBook map(PhoneBookDto dto) {
        return modelMapper.map(dto, PhoneBook.class);
    }

    @Override
    public PhoneBook map(PhoneBookDto dto, PhoneBook entity) {
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public CallDataDto map(CallData entity) {
        return modelMapper.map(entity, CallDataDto.class);
    }

    @Override
    public CallData map(CallDataDto dto) {
        return modelMapper.map(dto, CallData.class);
    }

    @Override
    public CallData map(CallDataDto dto, CallData entity) {
        modelMapper.map(dto, entity);
        return entity;
    }


    @Override
    public SyncDataDto map(SyncData entity) {
        return modelMapper.map(entity, SyncDataDto.class);
    }

    @Override
    public SyncData map(SyncDataDto dto) {
        return modelMapper.map(dto, SyncData.class);
    }

    @Override
    public SyncData map(SyncDataDto dto, SyncData entity) {
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public ReportDataDto map(ReportData entity) {
        return modelMapper.map(entity, ReportDataDto.class);
    }

    @Override
    public ReportData map(ReportDataDto dto) {
        return modelMapper.map(dto, ReportData.class);
    }

    @Override
    public ReportData map(ReportDataDto dto, ReportData entity) {
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public ReportDto map(List<ReportData> entity, SyncData sync) {
        List<ReportDataDto> reportsData = entity.stream().map(this::map).collect(Collectors.toList());
        ReportDto report = new ReportDto();
        report.setCdrReport(reportsData);
        report.setSyncDate(sync.getSyncDate());
        return report;
    }

}
