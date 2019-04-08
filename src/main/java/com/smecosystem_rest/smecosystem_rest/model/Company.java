package com.smecosystem_rest.smecosystem_rest.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "company")
@EntityListeners(AuditingEntityListener.class)
public class Company {

    private long id;
    private String kvkNummer;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "kvk_nummer", nullable = false)
    public String getKvkNummer() {
        return kvkNummer;
    }

    public void setKvkNummer(String kvkNummer) {
        this.kvkNummer = kvkNummer;
    }
}
