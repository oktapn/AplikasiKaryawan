package com.example.okta.aplikasikaryawan.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okta.aplikasikaryawan.R;
import com.example.okta.aplikasikaryawan.database.KaryawanDB;
import com.example.okta.aplikasikaryawan.database.KaryawanHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormInputKaryawanActivity extends AppCompatActivity {


    @BindView(R.id.TVHolderPilih)
    TextView TVHolderSpinner;
    @BindView(R.id.ETNama)
    EditText ETNama;
    @BindView(R.id.ETNik)
    EditText ETNik;
    @BindView(R.id.ETempatLahir)
    EditText ETempatLahir;
    @BindView(R.id.ETanggalLahir)
    EditText ETanggalLahir;
    @BindView(R.id.Spinner)
    Spinner Spinner;
    @BindView(R.id.ETALAMAT)
    EditText ETALAMAT;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radioButton)
    RadioButton radioButton;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.radioButton3)
    RadioButton radioButton3;
    KaryawanHelper db;


    String[] plants = new String[]{"Pilih Jenis Kelamin...", "Laki-laki", "Perempuan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_input_karyawan);
        ButterKnife.bind(this);
        setSpinner();
        db = new KaryawanHelper(this);
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(myCalendar);
            }

        };

        ETanggalLahir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(FormInputKaryawanActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(Calendar myCalendar) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        ETanggalLahir.setText(sdf.format(myCalendar.getTime()));
    }

    private void setSpinner() {
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, plants) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.BLACK);
                    tv.setTypeface(null, Typeface.ITALIC);
                } else {
                    tv.setTextColor(Color.WHITE);
                }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner.setAdapter(spinnerArrayAdapter);
        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                if (view != null) {
                    if (position == 0) {
                        tv.setTextColor(Color.BLACK);
                        tv.setTypeface(null, Typeface.ITALIC);
                    }
                }
                if (position > 0) {
                    TVHolderSpinner.setVisibility(View.GONE);
                    tv.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.BtnLogin)
    void login() {
        boolean failFlag = false;
        if (ETNama.getText().toString().isEmpty()) {
            failFlag = true;
        }
        if (ETNik.getText().toString().isEmpty()) {
            failFlag = true;
        }
        if (ETempatLahir.getText().toString().isEmpty()) {
            failFlag = true;
        }
        if (ETanggalLahir.getText().toString().isEmpty()) {
            failFlag = true;
        }
        if (Spinner.getSelectedItem().equals("Pilih Jenis Kelamin...")) {
            failFlag = true;
        }
        if (ETALAMAT.getText().toString().isEmpty()) {
            failFlag = true;
        }
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
            failFlag = true;
        }
        if (failFlag == true) {
            Toast.makeText(getApplicationContext(), "Ada data yang kosong", Toast.LENGTH_LONG).show();
        }
        if (!failFlag) {
            int radioButtonID = radioGroup.getCheckedRadioButtonId();
            View radioButton = radioGroup.findViewById(radioButtonID);
            int idx = radioGroup.indexOfChild(radioButton);
            RadioButton r = (RadioButton) radioGroup.getChildAt(idx);
            String selectedtext = r.getText().toString();
            db.addKaryawan(new KaryawanDB(Integer.valueOf(ETNik.getText().toString()), ETNama.getText().toString(), ETempatLahir.getText().toString() + ETanggalLahir.getText().toString(), Spinner.getSelectedItem().toString(), ETALAMAT.getText().toString(), selectedtext));
            Toast.makeText(getApplicationContext(), "DATA KARYAWAN TELAH MASUK ", Toast.LENGTH_LONG).show();
            hideKeyboard(this);
            finish();
        }
    }

    public static void hideKeyboard(Context context) {
        try {
            ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            if ((((Activity) context).getCurrentFocus() != null) && (((Activity) context).getCurrentFocus().getWindowToken() != null)) {
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
