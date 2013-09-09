package com.amatellanes.android;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemSelectedListener {

	private Spinner spProvincias, spLocalidades;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.spProvincias = (Spinner) findViewById(R.id.sp_provincia);
		this.spLocalidades = (Spinner) findViewById(R.id.sp_localidad);

		loadSpinnerProvincias();

	}

	/**
	 * Populate the Spinner.
	 */
	private void loadSpinnerProvincias() {

		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.provincias, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		this.spProvincias.setAdapter(adapter);

		// This activity implements the AdapterView.OnItemSelectedListener
		this.spProvincias.setOnItemSelectedListener(this);
		this.spLocalidades.setOnItemSelectedListener(this);

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		switch (parent.getId()) {
		case R.id.sp_provincia:

			// Retrieves an array
			TypedArray arrayLocalidades = getResources().obtainTypedArray(
					R.array.array_provincia_a_localidades);
			CharSequence[] localidades = arrayLocalidades.getTextArray(pos);
			arrayLocalidades.recycle();

			// Create an ArrayAdapter using the string array and a default
			// spinner layout
			ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
					this, android.R.layout.simple_spinner_item,
					android.R.id.text1, localidades);

			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			// Apply the adapter to the spinner
			this.spLocalidades.setAdapter(adapter);

			break;

		case R.id.sp_localidad:

			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// Callback method to be invoked when the selection disappears from this
		// view. The selection can disappear for instance when touch is
		// activated or when the adapter becomes empty.
	}

	/**
	 * Shows the selected strings from spinners.
	 * 
	 * @param v
	 *            The view that was clicked.
	 */
	public void showLocalidadSelected(View v) {
		Toast.makeText(
				getApplicationContext(),
				getString(R.string.message, spLocalidades.getSelectedItem()
						.toString(), spProvincias.getSelectedItem().toString()),
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
