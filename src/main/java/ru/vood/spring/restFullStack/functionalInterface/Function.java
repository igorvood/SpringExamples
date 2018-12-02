package ru.vood.spring.restFullStack.functionalInterface;

import ru.vood.spring.restFullStack.wrapRequest.Page;
import ru.vood.spring.restFullStack.wrapRequest.WrapperForService;

import java.util.Objects;

@FunctionalInterface
public interface Function<T, R> {

    /**
     * Returns a function that always returns its input argument.
     *
     * @param <T> the type of the input and output objects to the function
     * @return a function that always returns its input argument
     */
    static <T> java.util.function.Function<T, T> identity() {
        return t -> t;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>    the type of input to the {@code before} function, and to the
     *               composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     * @see #andThen(java.util.function.Function)
     */
    default <V> java.util.function.Function<V, R> compose(java.util.function.Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>   the type of output of the {@code after} function, and of the
     *              composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     * @see #compose(java.util.function.Function)
     */
    default <V> java.util.function.Function<T, V> andThen(java.util.function.Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    default WrapperForService.WrappedObject<R> wrap(T t) {
        WrapperForService wrapper = new WrapperForService() {
        };
        try {
            return wrapper.getOk(apply(t));
        } catch (Exception e) {
            return wrapper.getError(e);
        }
    }

    default WrapperForService.WrappedObject<R> wrap(Page page, T t) {
        WrapperForService wrapper = new WrapperForService() {
        };
        try {
            return wrapper.getOk(page, apply(t));
        } catch (Exception e) {
            return wrapper.getError(e);
        }
    }

}
