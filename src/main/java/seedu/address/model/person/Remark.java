package seedu.address.model.person;

public class Remark {

    public final String value;
    public Remark(String remark) {
        //requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Remark
                && value.equals(((Remark) other).value));
    }
}
