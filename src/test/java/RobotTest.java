import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class RobotTest {
    final double TURNOVER = Robot.TURNOVER;
    final double ERR = 1E-9;
    private static int testCounter;
    private Robot robot;
    private long startTime;

    @BeforeAll
    static void start() {
        System.out.println("Start of testing...");
        testCounter = 1;

    }

    @AfterAll
    static void end() {
        System.out.println("End of testing");
    }

    @BeforeEach
    public void robotInit() {
        startTime = System.currentTimeMillis();
        robot = new Robot(
                "Test", 1L, 100, Scope.MILITARY, 0, 0, 0);
        System.out.println("\tTest " + testCounter + " started...");
    }

    @AfterEach
    public void printAfter() {
        System.out.println("\t\tTest ended with " + (System.currentTimeMillis() - startTime) + " ms");
        testCounter++;
    }

    @Test
    void setPositionTest() {
        final long[] arguments = {12L, 41L};
        final long[] expected = {arguments[0], arguments[1]};

        robot.setPosition(arguments[0], arguments[1]);

        long[] result = {robot.getPositionX(), robot.getPositionY()};
//        assertTrue(expected[0] == result[0] && expected[1] == result[1]);
        assertThat(expected[0], equalTo(result[0]));
        assertThat(expected[1], equalTo(result[1]));
    }

    @Test
    void rotateTest() {
        final double original = 10d;
        final double argument = -53.7d;
        final double expected = TURNOVER + original + argument;

        robot.setAngle(original);
        robot.rotate(argument);

        double result = robot.getAngle();
//        assertTrue(isLesserThanErr(expected, result));
        assertThat(isLesserThanErr(expected, result), equalTo(true));
    }

    @Test
    void setAngleTest() {
        final double expected = TURNOVER - 17.71;
        final double argument = TURNOVER * 4 - 17.71;

        robot.setAngle(argument);

        double result = robot.getAngle();
        if (isLesserThanErr(expected, result))
            result = expected;
//        assertEquals(expected, result);
        assertThat(expected, is(equalTo(result)));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, TURNOVER, TURNOVER * 2, TURNOVER * 3, TURNOVER * 77})
    void rotateTurnoverTest(double argument) {
        final double expected = 10d;

        robot.setAngle(expected);
        robot.rotate(argument);

        double result = robot.getAngle();
//        assertEquals(expected, result);
        assertThat(result, allOf(lessThan(360d), greaterThanOrEqualTo(0d)));
    }

    @ParameterizedTest
    @MethodSource("setDestination")
    void moveTest(Long argument) {
        //final long[] argument = {-50, 30};
        final double maxAngle = 180d;
        final double minAngle = 90d;

        robot.move(argument, 10L);
        double result = robot.getAngle();

        assertThat(result, allOf(lessThanOrEqualTo(maxAngle), greaterThanOrEqualTo(minAngle)));
    }

    static Stream<Long> setDestination() {
        return Stream.of(-10L, -410L, -1L, 0L);
    }

    private boolean isLesserThanErr(double expected, double result) {
        return Math.abs(expected - result) <= ERR;
    }
}
