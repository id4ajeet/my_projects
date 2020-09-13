package com.ajeet.impactt.service.ifc;

import com.ajeet.impactt.service.dto.SyncDataDto;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public interface ISyncDataRepoService {
    @Transactional
    boolean save(SyncDataDto dto);
}
