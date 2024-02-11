package org.prettyprint;

@FunctionalInterface
public interface Executable<T> {
    String execute(T t, int... pad);
}
