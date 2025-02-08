package com.chintan.appversion.appversion.Model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "tbappversion")
public class AppVersion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appversionidp")
    private Long id;

    @Column(name = "version")
    private String version;

    @Column(name = "environment")
    private String environment;

    @Column(name = "updatedat")
    private Date updatedAt;
}
