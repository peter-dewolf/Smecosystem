package com.smecosystem_rest.smecosystem_rest.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "smartcontract")
@EntityListeners(AuditingEntityListener.class)
public class Smartcontract {

    private long id;
    private String blockAdderss;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "block_address", nullable = false)
    public String getBlockAdderss() {
        return blockAdderss;
    }

    public void setBlockAdderss(String blockAdderss) {
        this.blockAdderss = blockAdderss;
    }
}
