package com.ashutosh.cabbooking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ashutosh.cabbooking.exceptions.CabAlreadyExistsException;
import com.ashutosh.cabbooking.exceptions.CabNotFoundException;
import com.ashutosh.cabbooking.model.Cab;
import com.ashutosh.cabbooking.model.Location;

import lombok.NonNull;

@Service
public class CabsService {

	Map<String, Cab> cabs = new HashMap<>();

	public Cab createCab(@NonNull final Cab newCab) {
		if (cabs.containsKey(newCab.getId())) {
			throw new CabAlreadyExistsException();
		}

		cabs.put(newCab.getId(), newCab);
		return newCab;
	}

	public Cab getCab(@NonNull final String cabId) {
		if (!cabs.containsKey(cabId)) {
			throw new CabNotFoundException();
		}
		return cabs.get(cabId);
	}

	public Cab updateCabLocation(@NonNull final String cabId, @NonNull final Location newLocation) {
		if (!cabs.containsKey(cabId)) {
			throw new CabNotFoundException();
		}
		cabs.get(cabId).setCurrentLocation(newLocation);
		return cabs.get(cabId);
	}

	public Cab updateCabAvailability(@NonNull final String cabId, @NonNull final Boolean newAvailability) {
		if (!cabs.containsKey(cabId)) {
			throw new CabNotFoundException();
		}
		cabs.get(cabId).setIsAvailable(newAvailability);
		return cabs.get(cabId);
	}

	public List<Cab> getCabs(@NonNull final Location fromPoint, @NonNull final Double distance) {
		List<Cab> result = new ArrayList<>();
		for (Cab cab : cabs.values()) {
			// TODO: Use epsilon comparison because of double
			if (cab.getIsAvailable() && cab.getCurrentLocation().distance(fromPoint) <= distance) {
//				cab.setCurrentTrip(null);
				result.add(cab);
			}
		}
		return result;
	}

	public List<Cab> getAllCabs() {
		List<Cab> result = new ArrayList<>();
		for (Cab cab : cabs.values()) {
			result.add(cab);
		}
		return result;
	}

}
