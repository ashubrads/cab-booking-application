package com.ashutosh.cabbooking.utils;

import java.util.List;

import com.ashutosh.cabbooking.model.Cab;
import com.ashutosh.cabbooking.model.Location;
import com.ashutosh.cabbooking.model.Rider;

public interface CabMatchingStrategy {

  Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint);
}
