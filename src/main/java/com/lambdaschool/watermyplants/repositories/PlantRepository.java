package com.lambdaschool.watermyplants.repositories;

import com.lambdaschool.watermyplants.models.Plant;
import org.springframework.data.repository.CrudRepository;

public interface PlantRepository extends CrudRepository <Plant, Long> {
}
