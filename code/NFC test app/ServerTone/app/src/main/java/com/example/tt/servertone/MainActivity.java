package com.example.tt.servertone;


import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public void refresh(View view) {
        TextView m = findViewById(R.id.textView);
        String r = "New Scan";
        m.setText(r);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
//        sets up what to look for when activity resumes to screen
        super.onResume();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
//        Set up filters with what NFC technology to look for
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
//        find out if and what the NFC adapter on device is
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{intentfilter}, null);
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
        } catch (NullPointerException e) {

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


                textBox.setText(text);
                return;
            }
        }
    }
// Finish going through route
// Open on scan


}
