package com.md.passwordgenerator;

/*
 * Author: MD Danish Ansari
 * Date: 15 July 2018
 * Insta: @md.danish.ansari, @coding.is.life
 * Github: https://github.com/mddanishansari
 */


import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //all alphabets and number except 0, O, I, L, i, 0 and l
    // as they may create confusion
    private String alphabets = "123456789ABCDEFGHJKMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";

    //various views
    private TickerView tvPassword;
    private TextView tvPwdLength;
    private SeekBar pwdLength;

    private Random random;
    private int passwordLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize random obj
        random = new Random();

        //your favorite findViewByIds
        tvPassword = findViewById(R.id.tvPassword);
        tvPwdLength = findViewById(R.id.tvPwdLength);
        pwdLength = findViewById(R.id.pwdLength);

        //default password length i.e. 8
        passwordLength = pwdLength.getProgress();

        //some ticker view setup
        tvPassword.setCharacterLists(TickerUtils.provideAlphabeticalList());
        tvPassword.setGravity(Gravity.CENTER);


        //setting min pwd length to 4
        // unfortunately it's upported only from Oreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pwdLength.setMin(4);
        }

        //display default pwd lwngth for reference
        tvPwdLength.setText(passwordLength + "");

        //default random pwd
        tvPassword.setText(generatePassword(pwdLength.getProgress()));

        //password lenth change listener
        pwdLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                passwordLength = progress;
                tvPwdLength.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    /**
     * password changes when this button is clicked
     *
     * @param view
     */
    public void onGeneratePasswordClicked(View view) {
        tvPassword.setText(generatePassword(passwordLength));
    }

    /**
     * the heart of the application
     *
     * @param length
     * @return
     */
    private String generatePassword(int length) {
        StringBuilder pwdBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            pwdBuilder.append(alphabets.charAt(random.nextInt(alphabets.length())));
        }
        return pwdBuilder.toString();
    }
}
