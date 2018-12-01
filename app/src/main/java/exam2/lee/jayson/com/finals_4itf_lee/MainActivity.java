package exam2.lee.jayson.com.finals_4itf_lee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import exam2.lee.jayson.com.finals_4itf_lee.Student;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference root;

    private EditText  firstName;
    private EditText lastName;
    private EditText grade1;
    private EditText grade2;
    private TextView aveOut;

    ArrayList<String> keylist;

    private Button ave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("grade");
        keylist = new ArrayList<>();
        init();

    }

    private void init(){
        firstName = findViewById(R.id.GetFName);
        lastName = findViewById(R.id.GetLName);
        grade1 = findViewById(R.id.GetExam1);
        grade2 = findViewById(R.id.GetExam2);
        aveOut = findViewById(R.id.aveOut);

        ave = findViewById(R.id.AverageButton);
    }

    public void aveClick(View v){
        if(v.getId() == R.id.AverageButton){
            int a, b;
            String fname, lname;
            try{
                fname = firstName.getText().toString().trim();
                lname = lastName.getText().toString().trim();
                a = Integer.parseInt(grade1.getText().toString().trim());
                b = Integer.parseInt(grade2.getText().toString().trim());
                int ave = (a+b)/2;
                Student student_info = new Student(fname,lname,ave);
                String key = root.push().getKey();
                root.child(key).setValue(student_info);
                keylist.add(key);
                root.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int lastObj = (int) dataSnapshot.getChildrenCount() -1;
                        System.out.println(lastObj);
                        Student stud = dataSnapshot.child(keylist.get(lastObj)).getValue(Student.class);
                        aveOut.setText(stud.getAve().toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            catch (NumberFormatException e){
                Toast.makeText(this, "Cannot put null values", Toast.LENGTH_LONG).show();
            }


        }
    }
}
