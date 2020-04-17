package vn.edu.ntu.nhatthien.nguyennhatthien_58131414_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("tag", "onCreate Called");
        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("tag", "onStart Called");
        Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag", "onResume Called");
        Toast.makeText(getApplicationContext(), "onResume", Toast.LENGTH_SHORT).show();
        time = (TextView)findViewById(R.id.txtTG);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String txtTG = sdf.format(date.getTime());
        time.setText(txtTG);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("tag", "onRestart Called");
        Toast.makeText(getApplicationContext(), "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("tag", "onPause Called");
        Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("tag", "onStop Called");
        Toast.makeText(getApplicationContext(), "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tag", "onDestroy Called");
        Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_SHORT).show();
    }
}
