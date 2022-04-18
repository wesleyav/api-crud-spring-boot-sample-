package com.github.wesleyav.apicrudspringbootsample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.wesleyav.apicrudspringbootsample.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
