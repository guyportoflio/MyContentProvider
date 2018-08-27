package com.example.presly.mycontentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


  //  public boolean onCreateOptionsMenu(Menu menu){
  //    getMenuInflater().inflate(R.menu.main, menu);
  //    return true;
  //  }

    public void onClickAddName(View view){
        // adds a new student record
        ContentValues values = new ContentValues();

        values.put(StudentsProvider.NAME,((EditText)findViewById(R.id.txtName)).getText().toString());

        values.put(StudentsProvider.GRADE,((EditText)findViewById(R.id.txtGrade)).getText().toString());

        Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);

       Toast.makeText(this,uri.toString(),Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view){
        // retrieve student records
        String URL = "Content://com.example.provider.College/students";
        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");
        if(c.moveToFirst()){
            do{
                Toast.makeText(this,c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                " , " + c.getString(c.getColumnIndex(StudentsProvider.NAME)) +
                " , " + c.getString(c.getColumnIndex(StudentsProvider.GRADE)),Toast.LENGTH_SHORT).show();
            } while(c.moveToNext());

        }
    }
}
