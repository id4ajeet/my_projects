
package com.ajeet.impactt.persist.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@Entity
@Table(name = "sync_data")
public class SyncData {
    @Id
    @SequenceGenerator(name = "sync_data_id_seq", sequenceName = "sync_data_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "sync_data_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "sync_date")
    private Date syncDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }
}
