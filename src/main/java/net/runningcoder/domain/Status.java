package net.runningcoder.domain;

/**
 * Created by wangmaocheng on 2017/11/8.
 */
public enum Status {
    ENABLED(1, "使用"), DISABLED(0, "禁用");

    final int value;
    final String description;

    Status(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static Status valueOf(Integer value) {
        if (value == null)
            return null;

        Status[] values = Status.values();
        for (Status status : values) {
            if (value.equals(status.getValue())) {
                return status;
            }
        }
        return null;
    }

}
