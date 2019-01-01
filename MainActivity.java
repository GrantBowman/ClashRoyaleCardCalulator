package edu.purdue.gbowman.cardcostcalulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner card_level_spinner;
    private RadioGroup rarity_radio_group;
    private EditText amount_edit_text;
    private TextView output_text_view;
    private List<String> level_values = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the view widgets
        card_level_spinner = findViewById(R.id.card_level_spinner);
        rarity_radio_group = findViewById(R.id.rarity_radio_group);
        amount_edit_text = findViewById(R.id.amount_edit_text);
        output_text_view = findViewById(R.id.output_text_view);
        output_text_view.setText("output 1");

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
//        int[] levels = {1,2,3,4,5,6,7,8,9,10,11,12,13};

        //Initialize Category Spinner
//        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
//                android.R.layout.simple_spinner_dropdown_item,
//                level_values);
//        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
//                android.R.layout.simple_spinner_item,
//                level_values);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                level_values);
        card_level_spinner.setAdapter(spinnerArrayAdapter);
        card_level_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //
            }
            public void onNothingSelected(AdapterView<?> parent)
            {
                //card_level_spinner.setSelection(0);
            }
        });


    }//onCreate


    private void radioListener(RadioGroup radioGroup, int checkedId) {

                RadioButton selected_button = findViewById(checkedId);
                int i = rarity_radio_group.indexOfChild(selected_button);
                switch (i) {
                    case 0:
                        output_text_view.setText("common selected");
                        break;
                    case 1:
                        output_text_view.setText("rare selected");
                        break;
                    case 2:
                        output_text_view.setText("epic selected");
                        break;
                    case 3:
                        output_text_view.setText("legendary selected");
                        break;
                    default:
                        output_text_view.setText("how are we here");
                }
                System.out.printf("[debug] onCheckChanged Radio group (%d)\n", i);
    }

}//MainActivity
