package com.ashutosh.cabbooking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ashutosh.cabbooking.model.Cab;
import com.ashutosh.cabbooking.model.Location;
import com.ashutosh.cabbooking.model.Trip;
import com.ashutosh.cabbooking.service.CabsService;
import com.ashutosh.cabbooking.service.RidersService;
import com.ashutosh.cabbooking.service.TripsService;

@RestController
public class TripsController {
  
  @Autowired	
  private RidersService ridersManager;
  
  @Autowired
  private TripsService tripsManager;
  
  @Autowired
  private CabsService cabsManager;

  @RequestMapping(value = "/book", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<Cab> book(
      final String riderId,
      final Double sourceX,
      final Double sourceY,
      final Double destX,
      final Double destY) {

    Cab cab = tripsManager.createTrip(
        ridersManager.getRider(riderId),
        new Location(sourceX, sourceY),
        new Location(destX, destY));

    return new ResponseEntity<Cab>(cab, HttpStatus.OK);
  }

  
  
  @RequestMapping(value = "/update/trip/end", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<Cab> endTrip(final String cabId) {
    Cab updatedCab = tripsManager.endTrip(cabsManager.getCab(cabId));
    return new ResponseEntity<Cab>(updatedCab,HttpStatus.OK);
  }
}
