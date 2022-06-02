package bg.tu.varna.lab8;

import android.util.Patterns;
import android.view.View;

public class LoginTask implements Runnable {

    private String email;
    private ValidateListener listener;

    public LoginTask(String email, ValidateListener listener) {
        this.email = email;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isEmail =
                email != null && !email.isEmpty() &&
                        Patterns.EMAIL_ADDRESS.matcher(email).matches();

        listener.OnValidateLogin(isEmail);
    }
}