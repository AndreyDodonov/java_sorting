package app.concurrency;

import app.model.Bus;
import app.model.BusField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;


public class OccurenceCounterImpl implements OccurenceCounter {

    @Override
    public long count(List<Bus> data, BusField field, Object targetValue, int threadCount) throws InterruptedException {

        AtomicLong countValues = new AtomicLong();

        List<List<Bus>> chunksOfBusList = splitIntoChunks(data, threadCount);

        switch (field) {
            case REG_NUMBER -> {
                countValues.set(checkBuses(chunksOfBusList, Bus::getRegNumber, targetValue));
            }
            case MODEL -> {
                countValues.set(checkBuses(chunksOfBusList, Bus::getModel, targetValue));
            }
            case MILEAGE -> {
                countValues.set(checkBuses(chunksOfBusList, Bus::getMileage, targetValue));
            }
        }

        return countValues.get();
    }

    private List<List<Bus>> splitIntoChunks(List<Bus> list, int numChunks) {
        if (list == null || list.isEmpty() || numChunks <= 0) {
            return List.of();
        }

        int size = list.size();
        int chunkSize = (size + numChunks - 1) / numChunks; // округление вверх

        List<List<Bus>> chunks = new ArrayList<>();
        for (int i = 0; i < size; i += chunkSize) {
            int end = Math.min(i + chunkSize, size);
            chunks.add(new ArrayList<>(list.subList(i, end)));
        }
        return chunks;
    }


    private <T> long checkBuses(List<List<Bus>> chunksOfBusList,
                                Function<Bus, T> extractor,
                                Object targetValue) {
        return chunksOfBusList.parallelStream()
            .flatMap(List::stream)
            .filter(bus -> Objects.equals(targetValue, extractor.apply(bus)))
            .count();
    }


}
