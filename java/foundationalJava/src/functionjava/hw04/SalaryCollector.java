package functionjava.hw04;


import functionjava.hw02.Result;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by xqy on 18/1/25.
 */
public class SalaryCollector implements Collector<Salary,Map<String,Result>,List<Map.Entry<String,Result>>> {

    @Override
    public Supplier<Map<String,Result>> supplier() {
        return ()->new HashMap<>();
    }
    @Override
    public BiConsumer<Map<String,Result>, Salary> accumulator() {
        return (m,s)->{
            String name = s.getName().substring(0,2);
            if(s.totalIncome>=100000) {
                if (m.containsKey(name)) {
                    m.put(name, new Result(m.get(name).totalIncome + s.totalIncome/10000, m.get(name).count + 1));
                } else {
                    m.put(name, new Result(s.totalIncome/10000, 1));
                }
            }
        };
    }
    @Override
    public BinaryOperator<Map<String,Result>> combiner() {
        return (l1,l2)->{
            l1.putAll(l2);
            return l1;
        };
    }
    @Override
    public Function<Map<String, Result>, List<Map.Entry<String, Result>>> finisher() {
        return l->l.entrySet().stream()
                .sorted(new Comparator<Map.Entry<String, Result>>() {
                    @Override
                    public int compare(Map.Entry<String, Result> o1, Map.Entry<String, Result> o2) {
                        return (int) (o2.getValue().totalIncome - o1.getValue().totalIncome);
                    }
                })
                .collect(Collectors.toList());
    }
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
//        return EnumSet.of(Characteristics.IDENTITY_FINISH);
    }
}
