package s15666.pjwstk.pamobmi;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText weightField;
    private EditText heightField;

    private TextView bmiResultField;
    private TextView bmiCategoryField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.weightField = findViewById(R.id.weightField);
        this.heightField = findViewById(R.id.heightField);

        this.bmiResultField = findViewById(R.id.bmiResultField);
        this.bmiCategoryField = findViewById(R.id.bmiCategoryField);

        setWatcher();
    }

    private void setWatcher() {
        BmiResultUpdater resultUpdater = new BmiResultUpdater(getApplicationContext(), bmiResultField, bmiCategoryField);
        BmiParamWatcher paramWatcher = new BmiParamWatcher(weightField, heightField, resultUpdater);

        this.weightField.addTextChangedListener(paramWatcher);
        this.heightField.addTextChangedListener(paramWatcher);
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
