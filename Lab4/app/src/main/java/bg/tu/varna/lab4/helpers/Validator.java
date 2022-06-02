package bg.tu.varna.lab4.helpers;

import android.widget.EditText;

public class Validator {
    public boolean validateTextLength(EditText mText, int minimum, int maximum) {
        String text = String.valueOf(mText.getText());
        if (text.length() < minimum) {
            mText.setError("Text length" + text.length() + ", text should be at least " + minimum + " symbols");
            return false;
        } else if (text.length() > maximum) {
            mText.setError("Text length" + text.length() + ", text should be less than " + maximum + " symbols");
            return false;
        }
        return true;
    }
    public boolean validateNumber(EditText mNumber, String startWith, String length) {
        String phone = mNumber.getText().toString();
        PatternValidator patternValidator = new PatternValidator(startWith + "[0-9]{" + length + "}");
        if (!patternValidator.validate(phone)) {
            mNumber.setError("Invalid number");
        }
        return true;
    }
}