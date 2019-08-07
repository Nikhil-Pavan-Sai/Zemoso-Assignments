package com.nikhil.Cities.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Cities")
@EntityListeners(AuditingEntityListener.class)
public class City
{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        private String name;

        @NotNull
        private Long population;

        public City() {
        }

        public City(Long id, String name, Long population) {
            this.id = id;
            this.name = name;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getPopulation() {
            return population;
        }

        public void setPopulation(Long population) {
            this.population = population;
        }

}
