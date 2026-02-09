package com.jarvis.worksmartmoduelcompliancetracker.constant;

public enum ApprovalLevel {

    MANAGER_L1("L1"),
    SVH_L2("L2"),
    BU_HEAD_L3("L3"),
    UNKNOWN("Unknown");

    private final String label;

    ApprovalLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static ApprovalLevel fromWeek(int week) {

        if (week == 1) {
            return MANAGER_L1;
        } else if (week >= 2 && week <= 5) {
            return SVH_L2;
        } else if (week >= 6 && week <= 13) {
            return BU_HEAD_L3;
        } else {
            return UNKNOWN;
        }
    }
}
