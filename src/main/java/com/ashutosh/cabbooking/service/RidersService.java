package com.ashutosh.cabbooking.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ashutosh.cabbooking.exceptions.RiderAlreadyExistsException;
import com.ashutosh.cabbooking.exceptions.RiderNotFoundException;
import com.ashutosh.cabbooking.model.Rider;

import lombok.NonNull;

@Service
public class RidersService {
  Map<String, Rider> riders = new HashMap<>();

  public Rider createRider(@NonNull final Rider newRider) {
    if (riders.containsKey(newRider.getId())) {
      throw new RiderAlreadyExistsException();
    }

    riders.put(newRider.getId(), newRider);
    return newRider;
    
  }

  public Rider getRider(@NonNull final String riderId) {
    if (!riders.containsKey(riderId)) {
      throw new RiderNotFoundException();
    }
    return riders.get(riderId);
  }
}
