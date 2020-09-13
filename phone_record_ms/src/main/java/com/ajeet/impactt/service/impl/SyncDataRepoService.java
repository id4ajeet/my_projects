
package com.ajeet.impactt.service.impl;

import com.ajeet.impactt.persist.entity.SyncData;
import com.ajeet.impactt.persist.repository.SyncDataRepository;
import com.ajeet.impactt.service.dto.SyncDataDto;
import com.ajeet.impactt.service.ifc.IEntityDtoMapper;
import com.ajeet.impactt.service.ifc.ISyncDataRepoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class SyncDataRepoService implements ISyncDataRepoService {
    @Autowired
    private SyncDataRepository syncDataRepository;

    @Autowired
    private IEntityDtoMapper entityDtoMapper;

    @Override
    @Transactional
    public boolean save(SyncDataDto dto) {
        SyncData data = entityDtoMapper.map(dto);
        SyncData save = syncDataRepository.save(data);
        return save != null;
    }
}
