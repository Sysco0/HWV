package hawlandshut.projekt.hwv.enums;

/**
 * Created by Mansi on 08.03.2017.
 */
public enum Gender {
    Male,
    Female;
    public static Gender convert(hawlandshut.projekt.hwv.response.enums.Gender gender) {
        if (null == gender) {
            return null;
        }
        switch (gender) {
            case Female:
                return Female;
            case Male:
            default:
                return Male;
        }
    }

}
