package edu.purdue.gbowman.cardcostcalulator;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner card_level_spinner;
    private RadioGroup rarity_radio_group;
    private EditText amount_edit_text;
    private TextView output_text_view;
    private Button calculate_button;
    private List<String> level_values = new ArrayList<>();
    private Calculator ccc = new Calculator();


    // 9, 7, 4, 1
    private static final int COMMON = 0;
    private static final int RARE = 2;
    private static final int EPIC = 5;
    private static final int LEGENDARY = 8;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\nXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        //initialize the view widgets
        card_level_spinner = findViewById(R.id.card_level_spinner);
        rarity_radio_group = findViewById(R.id.rarity_radio_group);
        amount_edit_text = findViewById(R.id.amount_edit_text);
        output_text_view = findViewById(R.id.output_text_view);
//        output_text_view.setText("output 0");
        calculate_button = findViewById(R.id.calculate_button);

        //make the level values in the level list
        for (int i = 1; i <= 13; i++) {
            level_values.add("Level " + i);
        }

        RadioGroup.OnCheckedChangeListener radio_listener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                radioListener(radioGroup, checkedId);
            }//onCheckedChanged
        };

        rarity_radio_group.setOnCheckedChangeListener(radio_listener);

        //Initialize Category Spinner
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                level_values);
        card_level_spinner.setAdapter(spinnerArrayAdapter);

        Button.OnClickListener calculate_button_listener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonListener();
            }
        };
        calculate_button.setOnClickListener(calculate_button_listener);

        //text output options: scroll so horizontal might work
//        output_text_view.setScroller(new Scroller());

    }//onCreate


    private void radioListener(RadioGroup radioGroup, int checkedId) {

                RadioButton selected_button = findViewById(checkedId);
                int i = rarity_radio_group.indexOfChild(selected_button);
                switch (i) {
                    case 0:
//                        output_text_view.setText("common selected");
                        break;
                    case 1:
//                        output_text_view.setText("rare selected");
                        break;
                    case 2:
//                        output_text_view.setText("epic selected");
                        break;
                    case 3:
//                        output_text_view.setText("legendary selected");
                        break;
                    default:
//                        output_text_view.setText("how are we here");
                }
//                System.out.printf("[debug] onCheckChanged Radio group (%d)\n", i);
    }//radioListener

    private void buttonListener() {
        //get info
        int level = card_level_spinner.getSelectedItemPosition() + 1;
        int rarity = rarity_radio_group.indexOfChild(findViewById(rarity_radio_group.getCheckedRadioButtonId()));
        int cardCount = 0;
        //get card amount
        System.out.println(amount_edit_text.getText().toString());
        try {
            cardCount = Integer.parseInt(amount_edit_text.getText().toString());
        }
        catch (NumberFormatException e) {
            cardCount = 0;
        }

        //make sure level matches rarity
        if (level < ccc.rarityToOffset(rarity)) {
            output_text_view.setText(String.format(Locale.US, "Invalid level for chosen rarity!" +
                    "\nPlease select level %d or higher!", ccc.rarityToOffset(rarity) +1));
//            System.out.println("Invalid level for chosen rarity!");
            return;
        }

        String result = ccc.calculate(rarity, level, cardCount);
//        System.out.println(result);
        output_text_view.setText(result);

    }

}//MainActivity
