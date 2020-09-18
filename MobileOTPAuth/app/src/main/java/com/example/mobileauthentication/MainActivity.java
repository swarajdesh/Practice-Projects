package com.example.mobileauthentication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText country,phone,OTP;
    ImageView iv;
    FirebaseAuth firebaseAuth;
    String verificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    country = findViewById(R.id.country);
    phone = findViewById(R.id.phn);
    iv = findViewById(R.id.btn);
   OTP = findViewById(R.id.otp);


    firebaseAuth = FirebaseAuth.getInstance();

    iv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String number = phone.getText().toString().trim();
            if (number.isEmpty() || number.length() < 10)
            {
                phone.setError("Valid number is required");
                phone.requestFocus();
                return;
            }
            final String phonenumber = "+" + "91" + number;
            sendVerificationCode(phonenumber);
        }
    });

    }

        private void sendVerificationCode(String number){

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    number,
                    60,
                    TimeUnit.SECONDS,
                    TaskExecutors.MAIN_THREAD,
                    mCallBack
            );
        }
        private PhoneAuthProvider.OnVerificationStateChangedCallbacks
                mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId = s;
            }

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();
                if(code != null);{
                    OTP.setText(code);
                    verifyCode(code);
                }


            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();

            }
        };

        private void verifyCode(String code){
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            signInWithCredential(credential);

        }

        private void signInWithCredential(PhoneAuthCredential credential){
            firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                            }else{
                                Toast.makeText(MainActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }

}
