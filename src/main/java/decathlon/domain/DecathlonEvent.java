package decathlon.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum DecathlonEvent {
    HUNDRED_METER_RUN(25.4347, 18.0, 1.81, "100m"),
    LONG_JUMP(0.14354, 220.0, 1.4, "longJump"),
    SHOT_PUT(51.39, 1.5, 1.05, "shotPut"),
    HIGH_JUMP(0.8465, 75.0, 1.42, "highJump"),
    FOUR_HUNDRED_METER_RUN(1.53775, 82.0, 1.81, "400m"),
    HURDLES(5.74352, 28.5, 1.92, "hurdles"),
    DISCUS(12.91, 4.0, 1.1, "discus"),
    POLE_VAULT(0.2797, 100.0, 1.35, "pole"),
    JAVELIN(10.14, 7.0, 1.08, "javelin"),
    LONG_RUN(0.03768, 480.0, 1.85, "1500m");

    private final Double A;
    private final Double B;
    private final Double C;
    private final String eventName;

    DecathlonEvent(Double a, Double b, Double c, String eventName) {
        A = a;
        B = b;
        C = c;
        this.eventName = eventName;
    }

    public static boolean isTrackEvent(DecathlonEvent decathlonEvent) {
        return List.of(HUNDRED_METER_RUN, FOUR_HUNDRED_METER_RUN, HURDLES, LONG_RUN).contains(decathlonEvent);
    }

    public static DecathlonEvent resolve(String eventName) {
        return Arrays.stream(values())
                .filter(event -> event.getEventName().equals(eventName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
