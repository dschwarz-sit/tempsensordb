package at.codingclub.tempsensordb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class TemperatureController {

    @Autowired TemperaturService temperaturService;

    @GetMapping("/api/temperature")
    public Integer getTemperature(@RequestParam double latitude, @RequestParam double longitude){

        List<CoordinatesTemp> coordinatesTemps = temperaturService.loadFromDB();
        CoordinatesTemp foundCoordinate = null;

        for (CoordinatesTemp coordinateTemp: coordinatesTemps){
            foundCoordinate = coordinateTemp;
            break;
        }
        if(foundCoordinate == null){
            CoordinatesTemp newCoord = new CoordinatesTemp();
            newCoord.setLatitude(latitude);
            newCoord.setLongitude(longitude);
            Random rand = new Random();
            newCoord.setTemperature(rand.nextInt() % 50);
            temperaturService.saveToDb(newCoord);
            foundCoordinate = newCoord;
        }

        return foundCoordinate.getTemperature();
    }






}