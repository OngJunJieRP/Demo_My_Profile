package sg.edu.rp.c346.id19032110.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    // Declaration
    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Binding
        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioButtonGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                int intGender = rgGender.getCheckedRadioButtonId();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("Name", strName);
                prefEdit.putFloat("GPA", gpa);
                prefEdit.putInt("Gender", intGender);

                prefEdit.commit();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Step 1a: Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        Float gpa = Float.parseFloat(etGPA.getText().toString());
        int intGender = rgGender.getCheckedRadioButtonId();

        // Step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 1c: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        // Step 1d: Add the key-value pair
        prefEdit.putString("Name", strName);
        prefEdit.putFloat("GPA", gpa);
        prefEdit.putInt("Gender", intGender);

        // Step 1e: Call commit() to save the changes into SharedPreferences
        prefEdit.commit();
        }

    @Override
    protected void onResume() {
        super.onResume();
        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2b: Retrieve the saved data from the SharedPreferences object
        String msgName = prefs.getString("Name", "Ong Jun Jie");
        Float msgGPA = prefs.getFloat("GPA", 0);
        int intGender = prefs.getInt("Gender", R.id.radioButtonGenderMale);

        // Step 2c: Update the UI element with the value
        etName.setText(msgName);
        etGPA.setText(msgGPA + "");
        rgGender.check(intGender);
    }

}
