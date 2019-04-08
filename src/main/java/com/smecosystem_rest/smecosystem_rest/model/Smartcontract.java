package com.smecosystem_rest.smecosystem_rest.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "smartcontract")
@EntityListeners(AuditingEntityListener.class)
public class Smartcontract {

    private long id;
    private String blockAdderss;
    private String content;
    private Double discount;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "block_address")
    public String getBlockAdderss() {
        return blockAdderss;
    }

    public void setBlockAdderss(String blockAdderss) {
        this.blockAdderss = blockAdderss;
    }

    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "discount")
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
