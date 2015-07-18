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

	public void updateHeadLocation(SnakeMovement snakeMovement) {
		Location head = getHead();
		updateLocationOfBodyPart(snakeMovement.STEP_DISTANCE, head, snakeMovement.direction);
		if (headHasMetBody(snakeMovement.direction)) {
			SnakeGameLogger.log("Head has met body");
			throw new RuntimeException();
		}
	}

	public void updateTailLocation(SnakeMovement snakeMovement) {
		List<Location> bodyParts = getBodyParts();
		Location tail = bodyParts.get(0);
		Location tailsNextBodyPart = bodyParts.get(1);
		Direction directionToMoveTailIn = snakeMovement.determineDirectionToNextBodyPart(tail, tailsNextBodyPart);
		updateLocationOfBodyPart(snakeMovement.STEP_DISTANCE, tail, directionToMoveTailIn);
	}

	private void updateLocationOfBodyPart(int stepDistance, Location bodyPartLocation, Direction directionOfMovementForBodyPartLocation) {
		switch (directionOfMovementForBodyPartLocation) {
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
		case NULL:
			throw new RuntimeException();
		}
	}

	public void addJointBeforeHead() {
		int indexOfHead = bodyPartLocations.size() - 1;
		Location headLocation = bodyPartLocations.get(indexOfHead);
		Location jointLocation = new Location();
		jointLocation.setCoordinates(headLocation.getCoordinate(HORIZONTAL_AXIS), headLocation.getCoordinate(VERTICAL_AXIS));
		bodyPartLocations.add(indexOfHead, jointLocation);
	}

	public void removeJointAfterTail() {
		bodyPartLocations.remove(1);
	}

	private boolean headHasMetBody(Direction direction) {
		for (int index = 0; index < this.bodyPartLocations.size() - 2; index++) {
			Axis axisOfMovement = direction.getAxis();
			if (jointAndSuccessorHaveSameCoordinateOnAxisAsHead(index, axisOfMovement) //
					&& headCrossesConnectionBetweenJointAndItsSuccessor(index, axisOfMovement)) {
				SnakeGameLogger.log("Head has met body \n Checked axis: " + axisOfMovement);
				throw new RuntimeException();
			}

		}
		return false;
	}

	private boolean headCrossesConnectionBetweenJointAndItsSuccessor(int index, Axis axisOfMovement) {
		Location headLocation = getHeadLocation();
		Axis axisOrthogonalToMovement = axisOfMovement.getOrhogonalAxis();
		int jointCoordinate = bodyPartLocations.get(index).getCoordinate(axisOrthogonalToMovement);
		int successorCoordinate = bodyPartLocations.get(index + 1).getCoordinate(axisOrthogonalToMovement);
		int headCoordinate = headLocation.getCoordinate(axisOrthogonalToMovement);
		return ((jointCoordinate <= headCoordinate) && (headCoordinate <= successorCoordinate)) //
				|| ((successorCoordinate <= headCoordinate) && (headCoordinate <= jointCoordinate));
	}

	private boolean jointAndSuccessorHaveSameCoordinateOnAxisAsHead(int index, Axis axis) {
		Location headLocation = getHeadLocation();
		int headLocationsCoordinate = headLocation.getCoordinate(axis);
		int jointsCoordinate = bodyPartLocations.get(index).getCoordinate(axis);
		int succesorJointsCoordinate = bodyPartLocations.get(index + 1).getCoordinate(axis);
		return jointsCoordinate == headLocationsCoordinate && succesorJointsCoordinate == headLocationsCoordinate;
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

	private Location getHead() {
		List<Location> bodyParts = getBodyParts();
		Location indexOfHead = bodyParts.get(bodyParts.size() - 1);
		return indexOfHead;
	}

	@Override
	public String toString() {
		return bodyPartLocations.toString();
	}

	public Location getTailLocation() {
		return bodyPartLocations.get(0);
	}

	public Location getHeadLocation() {
		return bodyPartLocations.get(bodyPartLocations.size() - 1);
	}

	public int getCoordinate(BodyPart bodyPart, Axis axis) {
		int indexOfBodyPart;

		if (bodyPart.equals(BodyPart.TAIL))
			indexOfBodyPart = 0;
		else
			indexOfBodyPart = bodyPartLocations.size() - 1;

		return bodyPartLocations.get(indexOfBodyPart).getCoordinate(axis);
	}

	public List<Location> getBodyParts() {
		return bodyPartLocations;
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

}
