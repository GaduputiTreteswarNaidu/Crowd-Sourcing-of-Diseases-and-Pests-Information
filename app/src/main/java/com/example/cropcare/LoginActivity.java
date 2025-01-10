//package com.example.cropcare;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.Objects;
//
//public class LoginActivity extends AppCompatActivity {
//
//    EditText loginUsername, loginPassword;
//    Button loginButton;
//    TextView signupRedirectText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        loginUsername = findViewById(R.id.login_username);
//        loginPassword = findViewById(R.id.login_password);
//        loginButton = findViewById(R.id.login_button);
//        signupRedirectText = findViewById(R.id.signupRedirectText);
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!validateUsername() || !validatePassword()) {
//
//                     // Don't proceed if validation fails
//                }
//                checkUser();
//            }
//        });
//
//        signupRedirectText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    public Boolean validateUsername() {
//        String val = loginUsername.getText().toString();
//        if (val.isEmpty()) {
//            loginUsername.setError("Username cannot be empty");
//            return false;
//        }
//        loginUsername.setError(null);
//        return true;
//    }
//
//    public Boolean validatePassword() {
//        String val = loginPassword.getText().toString();
//        if (val.isEmpty()) {
//            loginPassword.setError("Password cannot be empty");
//            return false;
//        }
//        loginPassword.setError(null);
//        return true;
//    }
//
//    public void checkUser() {
//        String userUsername = loginUsername.getText().toString().trim();
//        String userPassword = loginPassword.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
//
//        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    loginUsername.setError(null);
//
//                    String passwordFromDB = null;
//
//                    // Retrieve the password for the username
//                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
//                        passwordFromDB = userSnapshot.child("password").getValue(String.class);
//                    }
//
//                    // Check if password matches
//                    if (passwordFromDB != null && passwordFromDB.equals(userPassword)) {
//                        // If credentials are correct, navigate to the next screen
//                        Intent intent = new Intent(LoginActivity.this, Continue_Login.class);
//                        startActivity(intent);
//                        finish();  // Optional: Finish LoginActivity to prevent going back to login
//                    } else {
//                        // If password doesn't match, show error
//                        loginPassword.setError("Invalid Credentials");
//                        loginPassword.requestFocus();
//                    }
//
//                } else {
//                    // If user doesn't exist, show error on username
//                    loginUsername.setError("User does not exist");
//                    loginUsername.requestFocus();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Handle database error if needed
//            }
//        });
//    }
//}
package com.example.cropcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;
    HelperClass db;  // SQLite Helper Class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);

        // Initialize SQLite database helper
        db = new HelperClass(getApplicationContext(), "farmerrecords", null, 1);

        // Handle login button click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() || !validatePassword()) {
                    return; // Stop if validation fails
                }
                checkUser(); // Validate user credentials
            }
        });

        // Redirect to sign-up activity
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    // Validate username field
    public Boolean validateUsername() {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("Username cannot be empty");
            return false;
        }
        loginUsername.setError(null);
        return true;
    }

    // Validate password field
    public Boolean validatePassword() {
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        }
        loginPassword.setError(null);
        return true;
    }

    // Check user credentials in SQLite database
    public void checkUser() {
        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        // Query SQLite database for user credentials
        String storedPassword = db.getPasswordForUsername(userUsername);

        if (storedPassword != null) {
            if (storedPassword.equals(userPassword)) {
                // Login successful
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);  // Mark the user as logged in
                editor.putString("username", userUsername);  // Optionally save the username
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), AskCommunity.class);
                startActivity(intent);
                finish(); // Optional: Prevent going back to login
            } else {
                // Password mismatch
                loginPassword.setError("Invalid Credentials");
                loginPassword.requestFocus();
            }
        } else {
            // Username not found
            loginUsername.setError("User does not exist");
            loginUsername.requestFocus();
        }
    }
}

