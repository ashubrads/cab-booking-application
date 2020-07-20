package com.ashutosh.cabbooking.utils;

import com.ashutosh.cabbooking.model.Location;

public interface PricingStrategy {
  Double findPrice(Location fromPoint, Location toPoint);
}
