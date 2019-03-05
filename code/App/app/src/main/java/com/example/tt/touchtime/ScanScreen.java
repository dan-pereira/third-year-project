package com.example.tt.touchtime;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.Arrays;

public class ScanScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_screen);
    }

    @Override
    protected void onResume() throws NullPointerException {
//        sets up what to look for when activity resumes to screen
        super.onResume();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
//        Set up filters with what NFC technology to look for
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
//        find out if and what the NFC adapter on device is
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        TextView textBox = findViewById(R.id.textView3);
        try {
            NfcAdapter nfcAdpt = NfcAdapter.getDefaultAdapter(this);
            if (!nfcAdpt.isEnabled()){
                textBox.setText(R.string.enable_nfc);
            }
            else {
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{intentfilter}, null);
            }
        }
        catch (NullPointerException e){
            textBox.setText(R.string.nfc_no_capabilities);
        }
    }


    @Override
    protected void onNewIntent(Intent intent) throws NullPointerException {
//        When Intent is triggered read info off of discovered tag
        try {
            if ((NfcAdapter.ACTION_TAG_DISCOVERED).equals(intent.getAction())) {
                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                //            This will parse nfc tag data
                nfcRead(tag);
                Log.i("-----___----", "Tag Detected");
            }
        }
        catch (NullPointerException e) {
            TextView textBox = findViewById(R.id.textView3);
            textBox.setText(R.string.nfc_error_empty);
            Log.i("-----_-----", "null");
        }
    }

    @SuppressLint("SetTextI18n")
    private void nfcRead(Tag tag) {
        Ndef ndef = Ndef.get(tag);
        TextView textBox = findViewById(R.id.textView);
//        Check if tag holds any data
        if (ndef == null) {
            textBox.setText("Empty Tag Error");
            return;
        }
        NdefMessage message = ndef.getCachedNdefMessage();
        NdefRecord[] record = message.getRecords();
        for (NdefRecord tagRecord : record) {
            if (tagRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(tagRecord.getType(), NdefRecord.RTD_TEXT)) {

                byte[] payload = tagRecord.getPayload();
                String str = new String(payload);
                String text = str.substring(3);

//              Here text is being passed to next activity
                Bundle passingString = new Bundle();
                passingString.putString("ROOMID", text);
                passingString.putString("USER_TYPE", "student");
                Intent intent = new Intent(this, TimetableView.class);
                intent.putExtras(passingString);
                startActivity(intent);
            }
        }
    }
}
