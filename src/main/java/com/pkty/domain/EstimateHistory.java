package com.pkty.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * This class represents the Estimate History entity for the application.
 *
 * @autor yperea
 */
@Entity(name = "EstimateHistory")
@Table(name = "estimates_history")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class EstimateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "candy_per_children")
    private String candyPerChild;

    @Column(name = "children_population")
    private String childrenPopulation;

    @NonNull
    @Column(name = "address")
    private String address;

    @NonNull
    @Column(name = "country")
    private String country;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date createdDate;
}
