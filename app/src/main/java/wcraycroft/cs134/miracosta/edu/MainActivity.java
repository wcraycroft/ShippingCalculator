package wcraycroft.cs134.miracosta.edu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Declarations / Member variables
    // Text format
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());
    // Views
    private EditText weightEditText;
    private TextView baseCostTextView;
    private TextView addedCostTextView;
    private TextView totalCostTextView;
    // Models
    private ShipItem currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiate models
        currentItem = new ShipItem();
        // Instantiate Views
        weightEditText = findViewById(R.id.weightEditText);
        baseCostTextView = findViewById(R.id.baseCostTextView);
        addedCostTextView = findViewById(R.id.addedCostTextView);
        totalCostTextView = findViewById(R.id.totalCostTextView);
        // Display initial values
        baseCostTextView.setText(currency.format(currentItem.getBaseCost()));
        addedCostTextView.setText(currency.format(currentItem.getAddedCost()));
        totalCostTextView.setText(currency.format(currentItem.getTotalCost()));

        // Add EditText listener
        weightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no action
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Get new weight from view, send to model
                try {
                    currentItem.setWeight(Integer.parseInt(s.toString()));
                } catch (NumberFormatException e) {
                    Log.w("ShippingCalculator", "NumberFormatException caught in MainActivity.java @ line 54 (parsing empty string?)");
                    // If string is empty, set weight to 0 in model
                    currentItem.setWeight(0);
                }
                // Get calculated info from model, send to view
                baseCostTextView.setText(currency.format(currentItem.getBaseCost()));
                addedCostTextView.setText(currency.format(currentItem.getAddedCost()));
                totalCostTextView.setText(currency.format(currentItem.getTotalCost()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                // no action
            }
        });
    }
}
