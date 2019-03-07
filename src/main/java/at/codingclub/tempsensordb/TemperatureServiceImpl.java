package at.codingclub.tempsensordb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class TemperatureServiceImpl implements TemperaturService{


    @Autowired
    TemperatureRepository temperatureRepository;


    @Override
    public void saveToDb(CoordinatesTemp coordinatesTemp) {

        temperatureRepository.save(coordinatesTemp);
    }

    @Override
    public List<CoordinatesTemp> loadFromDB() {
        List<CoordinatesTemp> list = new ArrayList<>();
        temperatureRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
