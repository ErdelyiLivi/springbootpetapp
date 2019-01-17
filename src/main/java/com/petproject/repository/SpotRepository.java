package com.petproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.petproject.api.Spot;

import java.util.List;

/**
 * Created by ler on 2017-04-02.
 */
public interface SpotRepository extends JpaRepository<Spot, Long> {

}
