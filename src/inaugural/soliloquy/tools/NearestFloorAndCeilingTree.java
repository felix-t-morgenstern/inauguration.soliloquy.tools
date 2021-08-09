package inaugural.soliloquy.tools;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;

// TODO: Draft test suite
public class NearestFloorAndCeilingTree {
    protected long _nodeValue;
    protected NearestFloorAndCeilingTree _leftNode;
    protected NearestFloorAndCeilingTree _rightNode;

    public long[] OrderedValues;
    public HashMap<Long, Integer> ValueIndices;
    public long MinimumValue;
    public long MaximumValue;

    private NearestFloorAndCeilingTree(long value) {
        _nodeValue = value;
    }

    private NearestFloorAndCeilingTree(long[] values) {
        OrderedValues = values;
        ValueIndices = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            ValueIndices.put(values[i], i);
        }
        MinimumValue = values[0];
        MaximumValue = values[values.length - 1];
        populateFromSortedArray(values);
    }

    // NB: Used for children
    private NearestFloorAndCeilingTree(long[] values, @SuppressWarnings("unused") boolean dummy) {
        populateFromSortedArray(values);
    }

    public static NearestFloorAndCeilingTree FromIntegers(Collection<Integer> unsorted) {
        return FromCollection(unsorted, i -> (long)i);
    }

    public static NearestFloorAndCeilingTree FromLongs (Collection<Long> unsorted) {
        return FromCollection(unsorted, i -> i);
    }

    private static <T> NearestFloorAndCeilingTree FromCollection(Collection<T> collection,
                                                                 Function<T, Long> castToLong) {
        if (Check.ifNull(collection, "collection").isEmpty()) {
            throw new IllegalArgumentException("NearestFloorAndCeilingTree.FromCollection: " +
                    "collection cannot be empty");
        }
        long[] toSort = new long[collection.size()];
        int index = 0;
        for(T i : collection) {
            toSort[index++] = castToLong.apply(i);
        }
        Arrays.sort(toSort);
        return new NearestFloorAndCeilingTree(toSort);
    }

    private void populateFromSortedArray(long[] floors) {
        if (floors.length == 1) {
            _nodeValue = floors[0];
        }
        if (floors.length == 2) {
            _nodeValue = floors[0];
            _rightNode = new NearestFloorAndCeilingTree(floors[1]);
        }
        if (floors.length == 3) {
            _leftNode = new NearestFloorAndCeilingTree(floors[0]);
            _nodeValue = floors[1];
            _rightNode = new NearestFloorAndCeilingTree(floors[2]);
        }
        if (floors.length > 3) {
            int centerIndex;
            if (floors.length % 2 == 1) {
                centerIndex = (floors.length / 2) + 1;
            }
            else {
                float middleOfRange = (floors[0] + floors[floors.length - 1]) / 2f;
                int leftOfCenterIndex = floors.length / 2;
                int rightOfCenterIndex = leftOfCenterIndex + 1;
                if (Math.abs(middleOfRange - floors[leftOfCenterIndex]) <
                        Math.abs(middleOfRange - floors[rightOfCenterIndex])) {
                    centerIndex = leftOfCenterIndex;
                }
                else {
                    centerIndex = rightOfCenterIndex;
                }
            }
            _nodeValue = floors[centerIndex];
            _leftNode = new NearestFloorAndCeilingTree(
                    Arrays.copyOfRange(floors, 0, centerIndex), true);
            _rightNode = new NearestFloorAndCeilingTree(
                    Arrays.copyOfRange(floors, centerIndex + 1, floors.length), true);
        }
    }

    public long getNearestFloor(long value) {
        Long nearestFloor = getNearestFloor(value, null);
        return nearestFloor == null ? MinimumValue : nearestFloor;
    }

    private Long getNearestFloor(long value, Long nearestFloor) {
        if (value == _nodeValue) {
            return _nodeValue;
        }

        if (value > _nodeValue) {
            nearestFloor = _nodeValue;
            if (_rightNode == null) {
                return nearestFloor;
            }
            return _rightNode.getNearestFloor(value, nearestFloor);
        }

        //if (value < nodeValue) {
        if (_leftNode == null) {
            return nearestFloor;
        }
        return _leftNode.getNearestFloor(value, nearestFloor);
    }

    public long getNearestCeiling(long value) {
        Long nearestCeiling = getNearestCeiling(value, null);
        return nearestCeiling == null ? MaximumValue : nearestCeiling;
    }

    private Long getNearestCeiling(long value, Long nearestCeiling) {
        if (value == _nodeValue) {
            return _nodeValue;
        }

        if (value < _nodeValue) {
            nearestCeiling = _nodeValue;
            if (_leftNode == null) {
                return nearestCeiling;
            }
            return _leftNode.getNearestCeiling(value, nearestCeiling);
        }

        //if (value < nodeValue) {
        if (_rightNode == null) {
            return nearestCeiling;
        }
        return _rightNode.getNearestCeiling(value, nearestCeiling);
    }

    public void incrementAllValues(long increment) {
        MinimumValue += increment;
        MaximumValue += increment;
        for (int i = OrderedValues.length - 1; i >= 0; i--) {
            ValueIndices.remove(OrderedValues[i]);
            ValueIndices.put(OrderedValues[i] += increment, i);
        }

        incrementAllValues(increment, true);
    }

    // NB: Used for children
    private void incrementAllValues(long increment,
                                    @SuppressWarnings("SameParameterValue") boolean dummy) {
        _nodeValue += increment;
        if (_leftNode != null) {
            _leftNode.incrementAllValues(increment, true);
        }
        if (_rightNode != null) {
            _rightNode.incrementAllValues(increment, true);
        }
    }
}
