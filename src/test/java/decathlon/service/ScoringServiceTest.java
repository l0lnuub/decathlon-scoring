package decathlon.service;

import decathlon.domain.DecathlonEvent;
import decathlon.domain.FinalScore;
import decathlon.domain.Performances;
import decathlon.domain.SingleEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoringServiceTest {

    private ScoringService scoringServiceUnderTest;

    @BeforeEach
    void setUp() {
        scoringServiceUnderTest = new ScoringService();
    }

    @Test
    void testCalculateScoresWithWrongInputsProducesZeroPoints() {
        Performances performances = new Performances();
        performances.setHundred("hundred");
        performances.setLongJump("longJump");
        performances.setShotPut("shotPut");
        performances.setHighJump("highJump");
        performances.setFourHundred("fourHundred");
        performances.setHurdles("hurdles");
        performances.setDiscus("discus");
        performances.setPoleVault("poleVault");
        performances.setJavelin("javelin");
        performances.setLongRun("longRun");

        FinalScore expectedResult = new FinalScore();
        expectedResult.setHundred(0);
        expectedResult.setLongJump(0);
        expectedResult.setShotPut(0);
        expectedResult.setHighJump(0);
        expectedResult.setFourHundred(0);
        expectedResult.setHurdles(0);
        expectedResult.setDiscus(0);
        expectedResult.setPoleVault(0);
        expectedResult.setJavelin(0);
        expectedResult.setLongRun(0);
        expectedResult.calculateTotal();

        FinalScore result = scoringServiceUnderTest.calculateScores(performances);

        assertEquals(expectedResult, result);
    }

    @Test
    void testCalculateSingleEventScoreWithWrongInputProducesZeroPoints() {
        final SingleEvent event = new SingleEvent();
        event.setEvent("100m");
        event.setResult("result");

        final int result = scoringServiceUnderTest.calculateSingleEventScore(event);

        assertEquals(0, result);
    }

    @Test
    void testCalculatePoints() {
        assertEquals(900, ScoringService.calculatePoints("10.827", DecathlonEvent.HUNDRED_METER_RUN));
    }

    @Test
    void testCalculateScores900PointsForEachEvent() {
        Performances performances = new Performances();
        performances.setHundred("10.827");
        performances.setLongJump("735.8");
        performances.setShotPut("16.78");
        performances.setHighJump("210.34");
        performances.setFourHundred("48.19");
        performances.setHurdles("14.59");
        performances.setDiscus("51.4");
        performances.setPoleVault("496.45");
        performances.setJavelin("70.67");
        performances.setLongRun("247.42");

        FinalScore expectedResult = new FinalScore();
        expectedResult.setHundred(900);
        expectedResult.setLongJump(900);
        expectedResult.setShotPut(900);
        expectedResult.setHighJump(900);
        expectedResult.setFourHundred(900);
        expectedResult.setHurdles(900);
        expectedResult.setDiscus(900);
        expectedResult.setPoleVault(900);
        expectedResult.setJavelin(900);
        expectedResult.setLongRun(900);
        expectedResult.calculateTotal();

        FinalScore result = scoringServiceUnderTest.calculateScores(performances);

        assertEquals(expectedResult, result);
    }

    @Test
    void testLongRunInMinutesAndSecondsEqualsInSeconds() {
        int actualMinutesAndSeconds = ScoringService.calculatePoints("04:07.42 ", DecathlonEvent.LONG_RUN);
        int actualInSeconds = ScoringService.calculatePoints("247.42", DecathlonEvent.LONG_RUN);
        assertEquals(actualInSeconds, actualMinutesAndSeconds);
        assertEquals(900, actualMinutesAndSeconds);
    }

    @Test
    void testSomeInputsFaultyReturnsButFieldsAreZero() {
        Performances performances = new Performances();
        performances.setHundred("10.827");
        performances.setLongJump("clearly wrong");
        performances.setShotPut("16.78");
        performances.setHighJump("210.34");
        performances.setFourHundred("48.19");
        performances.setHurdles("14.59");
        performances.setDiscus("51.4");
        performances.setPoleVault("clearly wrong");
        performances.setJavelin("70.67");
        performances.setLongRun("247.42");

        FinalScore expectedResult = new FinalScore();
        expectedResult.setHundred(900);
        expectedResult.setLongJump(0);
        expectedResult.setShotPut(900);
        expectedResult.setHighJump(900);
        expectedResult.setFourHundred(900);
        expectedResult.setHurdles(900);
        expectedResult.setDiscus(900);
        expectedResult.setPoleVault(0);
        expectedResult.setJavelin(900);
        expectedResult.setLongRun(900);
        expectedResult.calculateTotal();

        FinalScore result = scoringServiceUnderTest.calculateScores(performances);

        assertEquals(expectedResult, result);
    }
}
