/*
//package com.example.farzadfarshad.adeiye.Activity;
//
//import android.content.Intent;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.farzadfarshad.adeiye.Model.ChatMessage;
//import com.example.farzadfarshad.adeiye.R;
//import com.firebase.ui.auth.AuthUI;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class MessangerActivity extends AppCompatActivity {
//
//    int SIGN_IN_REQUEST_CODE = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.messanger_layout);
//
//        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
//            // Start sign in/sign up activity
//            startActivityForResult(
//                    AuthUI.getInstance()
//                            .createSignInIntentBuilder()
//                            .build(),
//                    SIGN_IN_REQUEST_CODE
//            );
//        } else {
//            // User is already signed in. Therefore, display
//            // a welcome Toast
//            Toast.makeText(this,
//                    "Welcome " + FirebaseAuth.getInstance()
//                            .getCurrentUser()
//                            .getDisplayName(),
//                    Toast.LENGTH_LONG)
//                    .show();
//
//            // Load chat room contents
//            displayChatMessages();
//        }
//
//
//        FloatingActionButton fab =
//                (FloatingActionButton) findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText input = (EditText) findViewById(R.id.input);
//
//                // Read the input field and push a new instance
//                // of ChatMessage to the Firebase database
//                FirebaseDatabase.getInstance()
//                        .getReference()
//                        .push()
//                        .setValue(new ChatMessage(input.getText().toString(),
//                                FirebaseAuth.getInstance()
//                                        .getCurrentUser()
//                                        .getDisplayName())
//                        );
//
//                // Clear the input
//                input.setText("");
//            }
//        });
//    }
//
//
//    private void displayChatMessages() {
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,
//                                    Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == SIGN_IN_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                Toast.makeText(this,
//                        "Successfully signed in. Welcome!",
//                        Toast.LENGTH_LONG)
//                        .show();
//                displayChatMessages();
//            } else {
//                Toast.makeText(this,
//                        "We couldn't sign you in. Please try again later.",
//                        Toast.LENGTH_LONG)
//                        .show();
//
//                // Close the app
//                finish();
//            }
//        }
//    }
//}
*/
