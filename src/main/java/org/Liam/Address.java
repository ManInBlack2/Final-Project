package org.Liam;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class Address {
    @Setter private Integer streetNo;
    @Setter private String street;
    @Setter private String city;
    @Setter private Province province;
    private String postalCode;

    public Address(Integer streetNo, String street, String city, Province province, String postalCode) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
        } else {
            this.streetNo = null;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
        }
    }

    public enum Province {
        AB,
        BC,
        MB,
        NB,
        NL,
        NS,
        ON,
        PE,
        QC,
        SK,
        YT
    }

    static boolean isPostalCodeValid(String postalCode) {
        if (postalCode.length() != 6) {
            return false;
        }

        for (char i = 0; i < postalCode.length(); i++) {
            if (i % 2 == 0) {
                if (!Character.isLetter(postalCode.charAt(i))) {
                    return false;
                }
            }else if (!Character.isDigit(postalCode.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public void setPostalCode(String postalCode) {
        if (isPostalCodeValid(postalCode)) {
            this.postalCode = postalCode;
        }
    }
}
