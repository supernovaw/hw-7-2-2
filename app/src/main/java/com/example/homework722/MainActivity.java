package com.example.homework722;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
	private EditText phoneNumberInput;
	private EditText smsTextInput;
	private Button callButton;
	private Button sendSmsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();

		callButton.setOnClickListener(v -> call());
		sendSmsButton.setOnClickListener(v -> sendSms());
	}

	private void initViews() {
		phoneNumberInput = findViewById(R.id.phoneNumberInput);
		smsTextInput = findViewById(R.id.smsTextInput);
		callButton = findViewById(R.id.callButton);
		sendSmsButton = findViewById(R.id.sendSmsButton);
	}

	private void call() {
		String number = phoneNumberInput.getText().toString();
		Intent intent = new Intent(Intent.ACTION_DIAL);
		// Uri.encode is useful to parse numbers like *101# as *101%23
		intent.setData(Uri.parse("tel:" + Uri.encode(number)));
		startActivity(intent);
	}

	private void sendSms() {
		String number = phoneNumberInput.getText().toString();
		String smsText = smsTextInput.getText().toString();
		SmsManager manager = SmsManager.getDefault();
		manager.sendTextMessage(number, null, smsText, null, null);
	}
}
