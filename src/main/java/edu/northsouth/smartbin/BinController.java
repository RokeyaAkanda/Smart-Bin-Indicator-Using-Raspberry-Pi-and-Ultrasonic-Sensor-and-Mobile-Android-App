package edu.northsouth.smartbin;

public class BinController {
    public void calculateAmount(float depth1, float depth2) {
        if ((depth1 >= SettingsConstants.highDepth && depth2 >= SettingsConstants.highDepth)) {
            System.out.println("High");
        } else if ((depth1 >= SettingsConstants.mediumDepth && depth2 <= SettingsConstants.mediumDepth) || (depth2 >= SettingsConstants.mediumDepth && depth1 <= SettingsConstants.mediumDepth)) {
            System.out.println("Medium");
        } else if ((depth1 >= SettingsConstants.lowDepth && depth2 <= SettingsConstants.lowDepth) || (depth2 >= SettingsConstants.lowDepth && depth1 <= SettingsConstants.lowDepth)) {
            System.out.println("Low");
        }
    }
}
