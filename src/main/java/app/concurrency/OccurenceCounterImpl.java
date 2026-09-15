package app.concurrency;

import app.collection.ArrayBusCollection;
import app.collection.BusCollection;
import app.model.Bus;
import app.model.BusField;
import app.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class OccurenceCounterImpl implements OccurenceCounter {

    @Override
    public long count(BusCollection data, BusField field, Object targetValue, int threadCount)
            throws InterruptedException {

        if (threadCount <= 0) {
            throw new ValidationException("Количество потоков должно быть больше 0: " + threadCount);
        }

        AtomicLong countValues = new AtomicLong();

        List<BusCollection> chunksOfBusList = splitIntoChunks(data, threadCount);

        switch (field) {
            case REG_NUMBER -> countValues.set(checkBuses(chunksOfBusList, Bus::getRegNumber, targetValue));

            case MODEL -> countValues.set(checkBuses(chunksOfBusList, Bus::getModel, targetValue));

            case MILEAGE -> countValues.set(checkBuses(chunksOfBusList, Bus::getMileage, targetValue));

        }

        return countValues.get();
    }

    private List<BusCollection> splitIntoChunks(BusCollection list, int numChunks) {
        if (list == null || list.isEmpty() || numChunks <= 0) {
            return List.of();
        }

        int size = list.size();
        int chunkSize = (size + numChunks - 1) / numChunks; // округление вверх

        List<BusCollection> chunks = new ArrayList<>();
        for (int i = 0; i < size; i += chunkSize) {
            int end = Math.min(i + chunkSize, size);
            chunks.add(new ArrayBusCollection(list.subList(i, end)));
        }
        return chunks;
    }

    private <T> long checkBuses(List<BusCollection> chunksOfBusList, Function<Bus, T> extractor, Object targetValue) {
        return chunksOfBusList.parallelStream().flatMap(BusCollection::stream)
                .filter(bus -> Objects.equals(targetValue, extractor.apply(bus))).count();
    }

}
