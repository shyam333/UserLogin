package helloworld.demo.com.userlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";
    DatabaseHelper myDb;

    Button button;
    EditText email,password;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        email = (EditText)findViewById(R.id.edt1);
//        Pattern p = Pattern.compile("[@gmail.com]");
//        Matcher m = p.matcher(s1);
//        while (m.find())
//        {
//            Log.e(TAG,"MainActivity"+m.find());
//        }
        password = (EditText)findViewById(R.id.edt2);
        button = (Button)findViewById(R.id.btn);
        register = (TextView)findViewById(R.id.txt1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final String s1 = email.getText().toString();
//                Pattern p = Pattern.compile("[a-z@gmail.com]+");
//                Matcher m = p.matcher(s1);


               // Log.d(TAG,"pattern :"+m.find());

//                while (m.find())
//                {
//                    System.out.println("output: "+m.group());
//
//                }
                if(myDb.checkUser(email.getText().toString(),
                        password.getText().toString()))
                {
                    Intent intent = new Intent(MainActivity.this, SecondClass.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"Invalid EmailId",Toast.LENGTH_SHORT).show();
                }
//                if(!email.getText().toString().equals("admin@gmail.com"))
//                {
//                    Toast.makeText(MainActivity.this,"Enter Valid EmailId",Toast.LENGTH_SHORT).show();
//                }
//                else if(!password.getText().toString().equals("admin"))
//                {
//                    Toast.makeText(MainActivity.this,"Enter Valid Password",Toast.LENGTH_SHORT).show();
//                }
//                else  {
//                    Intent intent = new Intent(MainActivity.this, SecondClass.class);
//                    startActivity(intent);
//                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterScreen.class);
                startActivity(intent);
            }
        });
    }
}
