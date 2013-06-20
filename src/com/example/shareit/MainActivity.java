package com.example.shareit;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;



public class MainActivity extends Activity{
	
	protected static final String DbHelper = null;
	private RadioGroup radioGroup;
	private RadioButton radioButton;
	private Button addButton;
	private EditText et;

	SQLiteDatabase db;
	DbHelper dbhelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("AHP", "Calling OnButton....");
		addListenerOnButton();

	}

	  public void addListenerOnButton() {
		  
		  radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		  addButton = (Button) findViewById(R.id.button1);
		  et = (EditText) findViewById(R.id.editText1);
		  addButton.setOnClickListener(new OnClickListener() {
			  
				@Override
				public void onClick(View v) {
		 
				        // get selected radio button from radioGroup
					int selectedId = radioGroup.getCheckedRadioButtonId();
		 
					// find the radiobutton by returned id
				        radioButton = (RadioButton) findViewById(selectedId);
		 
					Toast.makeText(MainActivity.this,
						radioButton.getText(), Toast.LENGTH_SHORT).show();
					
					Log.d("AHP", et.getText().toString());
					
					Log.d("AHP", "In OnButton....");
					String FILENAME = "hello_file";
					String string = "hello world!";
					String ret = null;

					FileOutputStream fos = null;
					try {
						fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
						fos.write(string.getBytes());
						fos.close();
						Log.d("AHP", "File Created....");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					dbhelper= new DbHelper(MainActivity.this);
					db = dbhelper.getWritableDatabase();
					ContentValues cv = new ContentValues();
					cv.put("name", "AMEYAP");
					cv.put("spent", "123");
					
					//db.insert(DbHelper, "shareit_table",cv);
					db.insert("shareit_table", "shareit_table",cv);
					
					
					try {
						InputStream inputStream = openFileInput(FILENAME);

						if ( inputStream != null ) {
						InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
						BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
						String receiveString = "";
						StringBuilder stringBuilder = new StringBuilder();

						while ( (receiveString = bufferedReader.readLine()) != null ) {
						stringBuilder.append(receiveString);
						}

						ret = stringBuilder.toString();
						Log.d("AHP", "file read complete...."+ret);
						inputStream.close();
						}
						}
						catch (FileNotFoundException e) {
						Log.e("AHP", "File not found: ...." + e.toString());
						} catch (IOException e) {
						Log.e("AHP", "Can not read file: ...." + e.toString());
						}
					
					Log.d("AHP", ret);
		 
				}
		 
			});
		 
		  }
	  
	  
	  
	  public void onRadioButtonClicked(View v) {
		    RadioButton button = (RadioButton) v;
		    Toast.makeText(MainActivity.this,
		        button.getText() + " was chosen.",
		        Toast.LENGTH_SHORT).show();
		}

}
