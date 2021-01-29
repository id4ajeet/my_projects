package com.ajeet.impactt.persist.repository;

import com.ajeet.impactt.persist.entity.SyncData;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
public class SyncDataRepositoryTest {

    @Autowired
    private SyncDataRepository syncDataRepository;

    @Test
    public void testSyncDate() {
        SyncData data = syncDataRepository.findTopByOrderByIdDesc();
        assertNotNull(data);
    }
}
