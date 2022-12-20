package ch2_plain_java.sn11_generics;

import java.io.Serializable;

// Interface expected here - only interfaces со второго агрумента
public class Vehicle<T extends Object & Comparable<? super T> & Serializable & Engine<? extends T>> {
  // Первое ограничение — в данном случае Object — используется для erasure, процесса затирания типов.
  // Его выполняет компилятор на этапе компиляции.

}
