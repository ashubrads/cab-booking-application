package com.ashutosh.cabbooking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ashutosh.cabbooking.exceptions.NoCabsAvailableException;
import com.ashutosh.cabbooking.exceptions.TripNotFoundException;
import com.ashutosh.cabbooking.model.Cab;
import com.ashutosh.cabbooking.model.Location;
import com.ashutosh.cabbooking.model.Rider;
import com.ashutosh.cabbooking.model.Trip;
import com.ashutosh.cabbooking.utils.CabMatchingStrategy;
import com.ashutosh.cabbooking.utils.PricingStrategy;

import lombok.NonNull;

@Service
public class TripsService {

	public static final Double ALLOWED_DISTANCE = 10.0;
	private Map<String, List<Trip>> trips = new HashMap<>();

	private CabsService cabsManager;
	private RidersService ridersManager;
	private CabMatchingStrategy cabMatchingStrategy;
	private PricingStrategy pricingStrategy;

	public TripsService(CabsService cabsManager, RidersService ridersManager, CabMatchingStrategy cabMatchingStrategy,
			PricingStrategy pricingStrategy) {
		this.cabsManager = cabsManager;
		this.ridersManager = ridersManager;
		this.cabMatchingStrategy = cabMatchingStrategy;
		this.pricingStrategy = pricingStrategy;
	}

	public Cab createTrip(@NonNull final Rider rider, @NonNull final Location fromPoint,
			@NonNull final Location toPoint) {
		final List<Cab> closeByCabs = cabsManager.getCabs(fromPoint, ALLOWED_DISTANCE);
		final List<Cab> closeByAvailableCabs = closeByCabs.stream().filter(cab -> cab.getCurrentTrip() == null)
				.collect(Collectors.toList());

		final Cab selectedCab = cabMatchingStrategy.matchCabToRider(rider, closeByAvailableCabs, fromPoint, toPoint);
		if (selectedCab == null) {
			throw new NoCabsAvailableException();
		}

		final Double price = pricingStrategy.findPrice(fromPoint, toPoint);
		final Trip newTrip = new Trip(rider, selectedCab, price, fromPoint, toPoint);
		if (!trips.containsKey(rider.getId())) {
			trips.put(rider.getId(), new ArrayList<>());
		}
		trips.get(rider.getId()).add(newTrip);
		selectedCab.setCurrentTrip(newTrip);

		return selectedCab;

	}

	public List<Trip> tripHistory(@NonNull final Rider rider) {
		return trips.get(rider.getId());
	}

	public Cab endTrip(@NonNull final Cab cab) {
		if (cab.getCurrentTrip() == null) {
			throw new TripNotFoundException();
		}

		cab.getCurrentTrip().endTrip();
		cab.setCurrentTrip(null);
		return cab;
	}
}
