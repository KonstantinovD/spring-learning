package ch2_plain_java.sn3_functional_interfaces;

@FunctionalInterface
public interface TripleFunction<T, R> {
  R apply(T t1, T t2, T t3);
}
