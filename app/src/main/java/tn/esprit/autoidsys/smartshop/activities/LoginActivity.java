package tn.esprit.autoidsys.smartshop.activities;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;

import com.andexert.library.RippleView;
import com.dd.CircularProgressButton;

import tn.esprit.autoidsys.smartshop.R;


public class LoginActivity extends Activity {

    public EditText EtLogin;
    public EditText EtPassword;
    public RippleView rippleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        EtLogin = (EditText) findViewById(R.id.login_et);
        EtPassword = (EditText) findViewById(R.id.password_et);
        final CircularProgressButton circularButton1 = (CircularProgressButton) findViewById(R.id.btnWithText);
        circularButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((EtLogin.getText().toString().equals("")) && (EtPassword.getText().toString().equals(""))) {
                    Log.i("sign in ", "dkhal");
                    if (circularButton1.getProgress() == 0) {
                        simulateSuccessProgress(circularButton1);
                    } else {
                        circularButton1.setProgress(0);
                    }
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);

                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();


                } else {
                    Log.i("sign in ", "error");
                    if (circularButton1.getProgress() == 0) {
                        simulateErrorProgress(circularButton1);
                    } else {
                        circularButton1.setProgress(0);
                    }
                }


            }
        });
    }

    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
            }
        });
        widthAnimation.start();
    }

    private void simulateErrorProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if (value == 99) {
                    button.setProgress(-1);
                }
            }
        });
        widthAnimation.start();
    }
}