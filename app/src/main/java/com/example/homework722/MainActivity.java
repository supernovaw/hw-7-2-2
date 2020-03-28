package com.example.homework722;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
	private static final int SMS_SEND_REQUEST = 42;

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
		int permission = ContextCompat.checkSelfPermission(this,
				Manifest.permission.SEND_SMS);
		if (permission != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.SEND_SMS}, SMS_SEND_REQUEST);
			Toast.makeText(this, R.string.send_sms_allow_request, Toast.LENGTH_SHORT).show();
			return;
		}

		String number = phoneNumberInput.getText().toString();
		String smsText = smsTextInput.getText().toString();
		SmsManager manager = SmsManager.getDefault();
		manager.sendTextMessage(number, null, smsText, null, null);
	}
}
