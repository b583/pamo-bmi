package s15666.pjwstk.pamobmi;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import s15666.pjwstk.pamobmi.bmi.BmiViewModel;

public class MainActivity extends AppCompatActivity {

    private BmiViewModel model;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        model = new ViewModelProvider(this).get(BmiViewModel.class);
        doneButton = findViewById(R.id.welcome_done_button);
        init();
    }

    private void init() {
        Boolean isValid = model.isValid().getValue();
        doneButton.setEnabled(isValid == null ? false : isValid);
        model.isValid().observe(this, aBoolean -> doneButton.setEnabled(aBoolean));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
