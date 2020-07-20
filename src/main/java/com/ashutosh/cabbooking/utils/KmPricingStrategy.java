package com.ashutosh.cabbooking.utils;

import org.springframework.stereotype.Service;

import com.ashutosh.cabbooking.model.Location;

@Service
public class KmPricingStrategy implements PricingStrategy {

  public static final Double PER_KM_RATE = 10.0;

  @Override
  public Double findPrice(Location fromPoint, Location toPoint) {
    return fromPoint.distance(toPoint) * PER_KM_RATE;
  }
}
