package com.theironyard.services;

import com.theironyard.entities.Beer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by stevenburris on 11/10/16.
 */
public interface BeerRepository extends CrudRepository<Beer, Integer> {
}
