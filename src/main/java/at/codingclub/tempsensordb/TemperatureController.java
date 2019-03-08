package at.codingclub.tempsensordb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class TemperatureController {

    private static final double TOLERANCE = 0.0000001;
	@Autowired TemperaturService temperaturService;

    @GetMapping("/api/temperature")
    public Integer getTemperature(@RequestParam double latitude, @RequestParam double longitude){

        List<CoordinatesTemp> coordinatesTemps = temperaturService.loadFromDB();
        CoordinatesTemp foundCoordinate = null;

        for (CoordinatesTemp coordinateTemp: coordinatesTemps){
        	if (isInRange(coordinateTemp, latitude, longitude)) {
        		foundCoordinate = coordinateTemp;
        		break;
        	}
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

	private boolean isInRange(CoordinatesTemp coordinateTemp, double latitude, double longitude) {
		return Math.abs(coordinateTemp.getLatitude() - latitude) < TOLERANCE &&
				Math.abs(coordinateTemp.getLongitude() - longitude) < TOLERANCE;
	}






}