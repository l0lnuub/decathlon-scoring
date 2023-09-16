package decathlon.service;

import decathlon.domain.DecathlonEvent;
import decathlon.domain.FinalScore;
import decathlon.domain.Performances;
import decathlon.domain.SingleEvent;
import org.springframework.stereotype.Service;

import static decathlon.domain.DecathlonEvent.*;
import static decathlon.domain.DecathlonEvent.isTrackEvent;
import static decathlon.domain.Performances.getDoubleValue;

@Service
public class ScoringService {

    public FinalScore calculateScores(Performances performances) {
        FinalScore score = new FinalScore();
        score.setHundred(calculatePoints(performances.getHundred(), HUNDRED_METER_RUN));
        score.setLongJump(calculatePoints(performances.getLongJump(), LONG_JUMP));
        score.setShotPut(calculatePoints(performances.getShotPut(), SHOT_PUT));
        score.setHighJump(calculatePoints(performances.getHighJump(), HIGH_JUMP));
        score.setFourHundred(calculatePoints(performances.getFourHundred(), FOUR_HUNDRED_METER_RUN));
        score.setHurdles(calculatePoints(performances.getHurdles(), HURDLES));
        score.setDiscus(calculatePoints(performances.getDiscus(), DISCUS));
        score.setPoleVault(calculatePoints(performances.getPoleVault(), POLE_VAULT));
        score.setJavelin(calculatePoints(performances.getJavelin(), JAVELIN));
        score.setLongRun(calculatePoints(performances.getLongRun(), LONG_RUN));
        score.calculateTotal();
        return score;
    }

    public int calculateSingleEventScore(SingleEvent event) {
        DecathlonEvent decathlonEvent = DecathlonEvent.resolve(event.getEvent());
        return calculatePoints(event.getResult(), decathlonEvent);
    }

    public static int calculatePoints(String performance, DecathlonEvent decathlonEvent) {
        if (performance != null) {
            Double perfNumeric = getDoubleValue(performance.trim());
            if (isTrackEvent(decathlonEvent) && perfNumeric <= 0) {
                return 0;
            }
            return isTrackEvent(decathlonEvent) ?
                    (int) Math.round(decathlonEvent.getA()*Math.pow((decathlonEvent.getB() - perfNumeric), decathlonEvent.getC())) :
                    (int) Math.round(decathlonEvent.getA()*Math.pow((perfNumeric - decathlonEvent.getB()), decathlonEvent.getC()));
        } else {
            return 0;
        }
    }

}
