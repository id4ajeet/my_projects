package com.ajeet.impactt.service.ifc;

import com.ajeet.impactt.service.dto.CallDataDto;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public interface ICallDataRepoService {
    @Transactional
    List<CallDataDto> findAll();

    @Transactional
    CallDataDto find(int id);

    @Transactional
    List<CallDataDto> findByUuid(String id);

    @Transactional
    boolean save(CallDataDto dto);

    @Transactional
    boolean saveAll(List<CallDataDto> dtos);

    @Transactional
    void deleteBuUuid(String uuid);
}
