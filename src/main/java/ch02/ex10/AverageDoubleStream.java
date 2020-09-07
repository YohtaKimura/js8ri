package ch02.ex10;

import java.util.stream.Stream;

// http://closedunbounded.hatenablog.com/entry/2015/01/22/133943
// なかなか思いつけないと思う
public class AverageDoubleStream {
    public static Double average(Stream<Double> stream) {
        return stream.reduce(
                new double[3],
                // accumulator に順序よく行なっていく処理
                (r, e) -> {
                    r[0] += e.doubleValue();
                    r[1] += 1.0;
                    r[2] = r[0] / r[1];
                    return r;},
                // 中間生成物同士に対して行なう処理
                (r, s) -> {
                    r[0] += s[0];
                    r[1] += s[1];
                    r[2] = r[0] / r[1];
                    return r;
                }
                )[2];
    }
}
