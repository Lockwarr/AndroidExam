package bg.tu.varna.lab4.helpers;

import java.util.regex.Pattern;

public class PatternValidator {

    private Pattern pattern;

    public PatternValidator(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public boolean validate(String text) {
        return pattern.matcher(text).matches();
    }
}