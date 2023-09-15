package decathlon.domain;

import lombok.Data;

@Data
public class FinalScore {
    private int hundred;
    private int longJump;
    private int shotPut;
    private int highJump;
    private int fourHundred;
    private int hurdles;
    private int discus;
    private int poleVault;
    private int javelin;
    private int longRun;
    private int total;
    
    public void calculateTotal() {
        total = hundred + longJump + shotPut + highJump + fourHundred + hurdles + discus + poleVault + javelin + longRun;
    }
}
