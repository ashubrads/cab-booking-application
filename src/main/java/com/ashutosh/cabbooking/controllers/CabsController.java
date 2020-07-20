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
import com.ashutosh.cabbooking.service.CabsService;
import com.ashutosh.cabbooking.service.TripsService;

@RestController
public class CabsController {
  @Autowired
  private CabsService cabsManager;
  
  @Autowired
  private TripsService tripsManager;

  public CabsController(CabsService cabsManager, TripsService tripsManager) {
    this.cabsManager = cabsManager;
    this.tripsManager = tripsManager;
  }

  @RequestMapping(value = "/register/cab", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<Cab> regiserCab(final String cabId, final String driverName) {
	Cab newCab = cabsManager.createCab(new Cab(cabId, driverName));
    return new ResponseEntity<Cab>(newCab,HttpStatus.CREATED);
  }

  @RequestMapping(value = "/update/cab/location", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<Cab> updateCabLocation(
      final String cabId, final Double newX, final Double newY) {

    Cab updatedCab = cabsManager.updateCabLocation(cabId, new Location(newX, newY));
    return new ResponseEntity<Cab>(updatedCab,HttpStatus.OK);
  }

  @RequestMapping(value = "/update/cab/availability", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<Cab> updateCabAvailability(final String cabId, final Boolean newAvailability) {
    Cab updatedCab = cabsManager.updateCabAvailability(cabId, newAvailability);
    return new ResponseEntity<Cab>(updatedCab,HttpStatus.OK);
  }
  
  @RequestMapping(value = "/get-all/cab/", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<List<Cab>> getAll() {
    List<Cab> cabs = cabsManager.getAllCabs();
    return new ResponseEntity<List<Cab>>(cabs,HttpStatus.OK);
  }
  
  

}
