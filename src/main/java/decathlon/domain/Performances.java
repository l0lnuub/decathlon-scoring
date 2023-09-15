package decathlon.domain;

import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;
import org.thymeleaf.util.StringUtils;

@Data
public class Performances {


    private String hundred;
    private String longJump;
    private String shotPut;
    private String highJump;
    private String fourHundred;
    private String hurdles;
    private String discus;
    private String poleVault;
    private String javelin;
    private String longRun;

    public static Double getDoubleValue(String longRun) {
        if (!StringUtils.isEmpty(longRun) && longRun.contains(":")) {
            String[] timeSplit = longRun.split(":");
            return parseDouble(timeSplit[0]) * 60 + parseDouble(timeSplit[1]);
        }
        return parseDouble(longRun);

    }

    private static Double parseDouble(String item) {
        return NumberUtils.isCreatable(item) ? Double.parseDouble(item) : 0.0;
    }
}
