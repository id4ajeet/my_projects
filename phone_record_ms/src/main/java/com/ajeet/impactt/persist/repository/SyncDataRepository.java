package com.ajeet.impactt.persist.repository;

import com.ajeet.impactt.persist.entity.SyncData;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public interface SyncDataRepository extends JpaRepository<SyncData, Integer> {
    SyncData findTopByOrderByIdDesc();
}
