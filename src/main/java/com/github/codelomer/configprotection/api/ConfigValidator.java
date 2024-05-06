package com.github.codelomer.configprotection.api;
/**
 * Interface describing object validation
 * @param <V> the return type of the object after validation
 */
public interface ConfigValidator<V> {

    /** validate method
     * @return validated value
     */
    V validate();
}
