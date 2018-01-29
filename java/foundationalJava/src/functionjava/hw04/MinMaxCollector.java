package functionjava.hw04;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * Created by xqy on 18/1/25.
 */
public class MinMaxCollector<T> implements Collector<T,
        MinMaxCollector.MinMaxAccumulator<T>,
        MinMaxCollector.MinMax<T>> {

    public static class MinMax<T> {
        private final Optional<T> min;
        private final Optional<T> max;

        public MinMax(T min, T max) {
            this.min = Optional.ofNullable(min);
            this.max = Optional.ofNullable(max);
        }
        public Optional<T> getMax(){
            return max;
        }
        public Optional<T> getMin(){
            return min;
        }
        public String toString(){
            return "min:"+min+"\n"+"max:"+max;
        }
    }

    public static class MinMaxAccumulator<T> {
        private final Comparator<? super T> cmp;
        private T min = null;
        private T max = null;

        public MinMaxAccumulator(Comparator<? super T> cmp) {
            this.cmp = cmp;
        }

        public void accumulate(T elem) {
            min = (min == null || cmp.compare(elem, min) < 0)
                    ? elem : min;
            max = (max == null || cmp.compare(elem, max) > 0)
                    ? elem : max;
        }
        public MinMaxAccumulator<T> combine(MinMaxAccumulator<T> other) {
            MinMax<T> otherMinMax = other.toMinMax();
            otherMinMax.getMax().ifPresent(this::accumulate);
            otherMinMax.getMin().ifPresent(this::accumulate);
            return this;
        }
        public MinMax<T> toMinMax() {
            return new MinMax<>(min, max);
        }
    }

    private final Comparator<? super T> cmp;

    public MinMaxCollector(Comparator<? super T> cmp) {
        this.cmp = Objects.requireNonNull(cmp);
    }
    @Override
    public Supplier<MinMaxAccumulator<T>> supplier() {
        return () -> new MinMaxAccumulator<>(cmp);
    }
    @Override
    public BiConsumer<MinMaxAccumulator<T>, T> accumulator() {
        return MinMaxAccumulator::accumulate;
    }
    @Override
    public BinaryOperator<MinMaxAccumulator<T>> combiner() {
        return MinMaxAccumulator::combine;
    }
    @Override
    public Function<MinMaxAccumulator<T>, MinMax<T>> finisher() {
        return MinMaxAccumulator::toMinMax;
    }
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

    public static void main(String[] args){
        Stream<Map.Entry<String,Integer>> boxes = Stream.of(
                new AbstractMap.SimpleEntry<>("a",1),
                new AbstractMap.SimpleEntry<>("b",3),
                new AbstractMap.SimpleEntry<>("c",0),
                new AbstractMap.SimpleEntry<>("d",4),
                new AbstractMap.SimpleEntry<>("e",11)
        );

        MinMaxCollector.MinMax<Map.Entry<String,Integer>> boxMinMax = boxes
                .collect(new MinMaxCollector<>(comparing(Map.Entry<String,Integer>::getValue)));

        System.out.println(boxMinMax);
    }
}
