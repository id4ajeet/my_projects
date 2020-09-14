
package com.ajeet.impactt.persist.repository;

import com.ajeet.impactt.persist.entity.CallData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@Repository
public interface CallDataRepository extends JpaRepository<CallData, Integer> {
    List<CallData> findCallDataByUuid(String uuid);

    void deleteByUuid(String uuid);
}
