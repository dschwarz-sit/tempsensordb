package at.codingclub.tempsensordb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface TemperatureRepository extends CrudRepository<CoordinatesTemp, Integer> {



}
