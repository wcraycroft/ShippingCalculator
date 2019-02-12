/**
 * Controller class for the Shipping Calculator's main activity.
 * This activity allows the user to enter an item weight in ounces via EditText.
 * When the EditText is changed, the controller will send new weight to the model,
 * and will update cost information.
 */

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

    /** Formats all cost output to local currency (values are not converted) */
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());
    /** EditText view used for user input*/
    private EditText weightEditText;
    /** TextView used to output base cost*/
    private TextView baseCostTextView;
    /** TextView used to output added cost*/
    private TextView addedCostTextView;
    /** TextView used to output total cost*/
    private TextView totalCostTextView;
    /** Model class used to calculate shipping costs. */
    private ShipItem currentItem;

    @Override
    /**
     *  Called at beginning of activity lifecycle.
     *  Initializes main layout.
     *  Instantiates ShipItem model with default values.
     *  Instantiates and links Views.
     *  Adds TextChangedListener to EditText view.
     *
     * @param savedInstanceState Saved data from previous state. Currently, no data is saved.
     *  */
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
            /**
             * Called when user makes a change to weightEditText.
             * Tries to get integer input from EditText, catches NumberFormatExceptions.
             * Sends value to model, get calculated data and outputs it to view.
             *
             * @param s Current CharSequence in EditText
             */
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Get new weight from view, send to model
                try {
                    currentItem.setWeight(Integer.parseInt(s.toString()));
                } catch (NumberFormatException e) {
                    Log.w("ShippingCalculator", "NumberFormatException caught in MainActivity.java @ line 54 (parsing empty string?)");
                    // If string is empty, send weight of 0 to model.
                    currentItem.setWeight(0);
                }
                // Get calculated info from model, send to view.
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
