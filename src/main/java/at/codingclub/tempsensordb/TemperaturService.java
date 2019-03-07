package at.codingclub.tempsensordb;

import java.util.List;

public interface TemperaturService {

    void saveToDb(CoordinatesTemp coordinatesTemp);
    List<CoordinatesTemp> loadFromDB();
}
