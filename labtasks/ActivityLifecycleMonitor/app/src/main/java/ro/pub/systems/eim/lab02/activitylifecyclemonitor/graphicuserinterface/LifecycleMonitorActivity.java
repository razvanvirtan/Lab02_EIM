package ro.pub.systems.eim.lab02.activitylifecyclemonitor.graphicuserinterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;

import ro.pub.systems.eim.lab02.activitylifecyclemonitor.R;
import ro.pub.systems.eim.lab02.activitylifecyclemonitor.general.Constants;
import ro.pub.systems.eim.lab02.activitylifecyclemonitor.general.Utilities;

public class LifecycleMonitorActivity extends AppCompatActivity {

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            EditText usernameEditText = (EditText)findViewById(R.id.username_edit_text);
            EditText passwordEditText = (EditText)findViewById(R.id.password_edit_text);
            if (((Button)view).getText().toString().equals(getResources().getString(R.string.ok_button_content))) {
                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                View popupContent;
                if (Utilities.allowAccess(getApplicationContext(), username, password)) {
                    popupContent = layoutInflater.inflate(R.layout.popup_window_authentication_success, null);
                } else {
                    popupContent = layoutInflater.inflate(R.layout.popup_window_authentication_fail, null);
                }
                final PopupWindow popupWindow = new PopupWindow(popupContent, android.app.ActionBar.LayoutParams.WRAP_CONTENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
                Button dismissButton = (Button)popupContent.findViewById(R.id.dismiss_button);
                dismissButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
            if (((Button)view).getText().toString().equals(getResources().getString(R.string.cancel_button_content))) {
                usernameEditText.setText(getResources().getText(R.string.empty));
                passwordEditText.setText(getResources().getText(R.string.empty));
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_monitor);

        Button okButton = (Button) findViewById(R.id.ok_button);
        okButton.setOnClickListener(buttonClickListener);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState == null)
            Log.d(Constants.TAG, "onCreate() method was invoked without a previous state");
        else {
            Log.d(Constants.TAG, "onCreate() method was invoked with a previous state");

            EditText username = (EditText) findViewById(R.id.username_edit_text);
            EditText password = (EditText) findViewById(R.id.password_edit_text);
            CheckBox checkBox = (CheckBox) findViewById(R.id.remember_me_checkbox);

            if (savedInstanceState.getString("username") != null)
                username.setText(savedInstanceState.getString("username"));

            if (savedInstanceState.getString("password") != null)
                password.setText(savedInstanceState.getString("password"));

            checkBox.setChecked(savedInstanceState.getBoolean("checkbox"));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Constants.TAG, "onRestart() method ws invoked");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constants.TAG, "onStart() method ws invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Constants.TAG, "onResume() method ws invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Constants.TAG, "onPause() method ws invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constants.TAG, "onStop() method ws invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, "onDestroy() method ws invoked");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        CheckBox checkBox = (CheckBox) findViewById(R.id.remember_me_checkbox);
        EditText username = (EditText) findViewById(R.id.username_edit_text);
        EditText password = (EditText) findViewById(R.id.password_edit_text);

        if (checkBox.isChecked()) {
            savedInstanceState.putString("username", username.getText().toString());
            savedInstanceState.putString("password", password.getText().toString());
            savedInstanceState.putBoolean("checkbox", checkBox.isChecked());
        }
    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        EditText username = (EditText) findViewById(R.id.username_edit_text);
//        EditText password = (EditText) findViewById(R.id.password_edit_text);
//        CheckBox checkBox = (CheckBox) findViewById(R.id.remember_me_checkbox);
//
//        if (savedInstanceState.getString("username") != null)
//            username.setText(savedInstanceState.getString("username"));
//
//        if (savedInstanceState.getString("password") != null)
//            password.setText(savedInstanceState.getString("password"));
//
//        checkBox.setChecked(savedInstanceState.getBoolean("checkbox"));
//
//    }

}
