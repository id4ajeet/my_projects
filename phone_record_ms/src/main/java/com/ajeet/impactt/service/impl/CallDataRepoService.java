
package com.ajeet.impactt.service.impl;

import com.ajeet.impactt.persist.entity.CallData;
import com.ajeet.impactt.persist.repository.CallDataRepository;
import com.ajeet.impactt.service.dto.CallDataDto;
import com.ajeet.impactt.service.ifc.ICDRFilter;
import com.ajeet.impactt.service.ifc.ICallDataRepoService;
import com.ajeet.impactt.service.ifc.IEntityDtoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class CallDataRepoService implements ICallDataRepoService {

    @Autowired
    private CallDataRepository callDataRepository;

    @Autowired
    private IEntityDtoMapper entityDtoMapper;

    @Autowired
    private ICDRFilter filter;

    @Override
    @Transactional
    public List<CallDataDto> findAll() {
        List<CallDataDto> calls = new ArrayList<>();
        callDataRepository.findAll().forEach(ent -> {
            CallDataDto dto = entityDtoMapper.map(ent);
            calls.add(dto);
        });

        return calls;
    }

    @Override
    @Transactional
    public CallDataDto find(int id) {
        Optional<CallData> dto = callDataRepository.findById(id);
        if (dto.isPresent()) {
            return entityDtoMapper.map(dto.get());
        }
        return null;
    }

    @Override
    @Transactional
    public boolean save(CallDataDto dto) {
        List<CallDataDto> filteredList = filter.run(Arrays.asList(dto));
        if (filteredList.isEmpty()) {
            return false;
        }
        CallData data = entityDtoMapper.map(dto);
        CallData save = callDataRepository.save(data);
        return save != null;
    }

    @Override
    @Transactional
    public boolean saveAll(List<CallDataDto> dtos) {
        List<CallDataDto> filteredDtos = filter.run(dtos);
        List<CallData> data = filteredDtos.stream().map(entityDtoMapper::map).collect(Collectors.toList());
        Iterable<CallData> callData = callDataRepository.saveAll(data);
        return callData != null;
    }

    @Override
    @Transactional
    public void delete(int id) {
        callDataRepository.deleteById(id);
    }
}
