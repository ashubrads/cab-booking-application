package com.ashutosh.cabbooking.utils;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ashutosh.cabbooking.model.Cab;
import com.ashutosh.cabbooking.model.Location;
import com.ashutosh.cabbooking.model.Rider;

import lombok.NonNull;

@Service
public class AvailabilityCabMatchingStrategy implements CabMatchingStrategy {

  @Override
  public Cab matchCabToRider(
      @NonNull final Rider rider,
      @NonNull final List<Cab> candidateCabs,
      @NonNull final Location fromPoint,
      @NonNull final Location toPoint) {
    if (candidateCabs.isEmpty()) {
      return null;
    }
    return candidateCabs.get(0);
  }
}
