package com.ashutosh.cabbooking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ashutosh.cabbooking.model.Location;
import com.ashutosh.cabbooking.model.Rider;
import com.ashutosh.cabbooking.model.Trip;
import com.ashutosh.cabbooking.service.RidersService;
import com.ashutosh.cabbooking.service.TripsService;

@RestController
public class RidersController {
  
  @Autowired	
  private RidersService ridersManager;
  
  @Autowired
  private TripsService tripsManager;
  

  @RequestMapping(value = "/register/rider", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<Rider> registerRider(final String riderId, final String riderName) {
    Rider rider = ridersManager.createRider(new Rider(riderId, riderName));
    return new ResponseEntity<Rider>(rider, HttpStatus.CREATED);
  }
  
  @RequestMapping(value = "/history",method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<List<Trip>> fetchHistory(final String riderId) {
    List<Trip> trips = tripsManager.tripHistory(ridersManager.getRider(riderId));
    return new ResponseEntity<List<Trip>>(trips, HttpStatus.OK);
  }

  
}
