package bc.util;

public class ValidationUtilities {
    public ValidationUtilities() {
    }

    public static int checkEmailValid(String email) {
        String emailRegex = "^[\\w]+([\\w.][\\w]+)*@[\\w]+(\\.\\w+)+$";
        String[] emailSplit = email.split("[@]", 2);
        if (!email.matches(emailRegex)) {
            return 2;
        } else {
            return emailSplit[0].length() >= 6 && emailSplit[0].length() <= 30 ? 0 : 1;
        }
    }

    public static boolean checkUsernameValid(String username) {
        String regex = "^[a-zA-Z][\\w_]+[\\w][^_]$";
        return username.matches(regex);
    }

    public static boolean checkPasswordValid(String password) {
        String regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{6,20})";
        return password.matches(regex);
    }

    public static boolean checkNameOfUserValid(String name) {
        String regex = "^[^@#$%!(){}\\[\\]-_+=]+( [^@#$%!(){}\\[\\]-_+=])*$";
        return name.matches(regex);
    }

    public static boolean checkNameCategoryValid(String groupName) {
        String regex = "^[^@#$%!]+( [^@#$%!])*$";
        return groupName.matches(regex);
    }
}
