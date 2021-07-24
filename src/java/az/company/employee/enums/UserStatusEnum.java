package az.company.employee.enums;

public enum UserStatusEnum {

    CONFIRMED(1), UNCONFIRMED(0);

    private final short value;

    private UserStatusEnum(final int value) {
        this.value = (short) value;
    }

    public int getValue() {
        return value;
    }

}
