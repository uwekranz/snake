package model;

import static model.Axis.HORIZONTAL_AXIS;
import static model.Axis.VERTICAL_AXIS;

import java.util.ArrayList;
import java.util.List;

import applicationBoundary.SnakeGameLogger;

public class SnakeLocation {
	private List<Location> bodyPartLocations;

	public SnakeLocation(int bodyLength) {
		bodyPartLocations = new ArrayList<Location>();
		bodyPartLocations.add(new Location(0, 0));
		bodyPartLocations.add(new Location(bodyLength, 0));
	}

	public void setCoordinates(Location bodyPartLocation, int horizontalCoordinate, int verticalCoordinate) {
		bodyPartLocation.setCoordinates(horizontalCoordinate, verticalCoordinate);
	}

	public void update(Location bodyPartLocation, int stepDistance, Axis coordinateAxis) {
		int newValue = bodyPartLocation.getCoordinate(coordinateAxis) + stepDistance;

		if (coordinateAxis.equals(VERTICAL_AXIS))
			updateValueOfVerticalCoordinate(bodyPartLocation, newValue);
		else
			updateValueOfHorizontalCoordinate(bodyPartLocation, newValue);
	}

	private void updateValueOfHorizontalCoordinate(Location bodyPartLocation, int newValue) {
		setCoordinates(bodyPartLocation, newValue, bodyPartLocation.getCoordinate(VERTICAL_AXIS));
	}

	private void updateValueOfVerticalCoordinate(Location bodyPartLocation, int newValue) {
		setCoordinates(bodyPartLocation, bodyPartLocation.getCoordinate(HORIZONTAL_AXIS), newValue);
	}

	@Override
	public int hashCode() {
		return ((bodyPartLocations == null) ? 0 : bodyPartLocations.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SnakeLocation other = (SnakeLocation) obj;
		if (!bodyPartLocations.equals(other.bodyPartLocations))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return bodyPartLocations.toString();
	}

	public Location getTailLocation() {
		return bodyPartLocations.get(0);
	}

	public Location getHeadLocation() {
		return bodyPartLocations.get(1);
	}

	public void updateAccordingToMovement(Direction direction, int stepDistance) {
		for (Location bodyPartLocation : bodyPartLocations) {
			switch (direction) {
			case DOWN:
				update(bodyPartLocation, stepDistance, VERTICAL_AXIS);
				break;
			case LEFT:
				update(bodyPartLocation, -stepDistance, HORIZONTAL_AXIS);
				break;
			case RIGHT:
				update(bodyPartLocation, stepDistance, HORIZONTAL_AXIS);
				break;
			case UP:
				update(bodyPartLocation, -stepDistance, VERTICAL_AXIS);
				break;
			}
		}

		SnakeGameLogger.log("The snakes location is: " + this);
	}

	public int getCoordinate(BodyPart bodyPart, Axis axis) {
		return bodyPartLocations.get(bodyPart.ordinal()).getCoordinate(axis);
	}

}
