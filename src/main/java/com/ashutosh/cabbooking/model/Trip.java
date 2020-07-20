package com.ashutosh.cabbooking.model;

import static com.ashutosh.cabbooking.model.TripStatus.FINISHED;
import static com.ashutosh.cabbooking.model.TripStatus.IN_PROGRESS;

enum TripStatus {
	IN_PROGRESS, FINISHED
}

public class Trip {
	private Rider rider;
	private TripStatus status;
	private Double price;
	private Location fromPoint;
	private Location toPoint;

	public Trip(final Rider rider, final Cab cab, final Double price, final Location fromPoint,
			final Location toPoint) {
		this.rider = rider;
		this.price = price;
		this.fromPoint = fromPoint;
		this.toPoint = toPoint;
		this.status = IN_PROGRESS;
	}

	public void endTrip() {
		this.status = FINISHED;
	}

	public Rider getRider() {
		return rider;
	}

	public void setRider(Rider rider) {
		this.rider = rider;
	}



	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Location getFromPoint() {
		return fromPoint;
	}

	public void setFromPoint(Location fromPoint) {
		this.fromPoint = fromPoint;
	}

	public Location getToPoint() {
		return toPoint;
	}

	public void setToPoint(Location toPoint) {
		this.toPoint = toPoint;
	}

}
