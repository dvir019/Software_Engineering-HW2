package util;

/**
 *  Utility class for input parameter validation.
 */
public class Assertions {

    private Assertions() {
        // can not be instantiated
    }

    static void notNull(final Object parameter, final String parameterName) {
        if (parameter == null) {
            throw new IllegalArgumentException("Parameter '" + parameterName + "' must not be null!");
        }
    }
}
