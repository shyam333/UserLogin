package helloworld.demo.com.userlogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shyamramesh on 08/05/18.
 */

public class RegisterScreen extends AppCompatActivity {

    TextView nam,mail,pass,confirmpassword;
    EditText name,mailid,pssw,cnfirmpssw;
    Button registerbutton;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        myDb = new DatabaseHelper(this);

        nam = (TextView)findViewById(R.id.txt1);
        mail =(TextView)findViewById(R.id.txt2);
        pass =(TextView)findViewById(R.id.txt3);
        confirmpassword =(TextView)findViewById(R.id.txt4);
        name = (EditText)findViewById(R.id.edt1);
        mailid = (EditText)findViewById(R.id.edt2);
        pssw = (EditText)findViewById(R.id.edt3);
        cnfirmpssw = (EditText)findViewById(R.id.edt4);
        registerbutton = (Button)findViewById(R.id.registerbtn);

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateMethod();

            }
        });
    }

    private void validateMethod() {
        String password = pssw.getText().toString();

        if(password.equals(cnfirmpssw.getText().toString()))
        {
            addData();
        }
        else {
            Toast.makeText(this, "Enter Valid Password", Toast.LENGTH_SHORT).show();
        }
    }

    private void addData() {

        if(name.getText().toString().length() == 0)
        {
            Toast.makeText(RegisterScreen.this,"Enter Name",Toast.LENGTH_SHORT).show();
        }
        else if(mailid.getText().toString().length() == 0)
        {
            Toast.makeText(RegisterScreen.this,"Enter Email",Toast.LENGTH_SHORT).show();
        }
        else if(pssw.getText().toString().length() == 0)
        {
            Toast.makeText(RegisterScreen.this,"Enter Email",Toast.LENGTH_SHORT).show();
        }
        else if(cnfirmpssw.getText().toString().length() == 0)
        {
            Toast.makeText(RegisterScreen.this,"Enter Email",Toast.LENGTH_SHORT).show();
        }
        else{
            String EmailId = myDb.checkEmail(mailid.getText().toString());
            if(EmailId == null) {
                boolean isInserted = myDb.insertData(new Contact(null,
                        name.getText().toString(),
                        mailid.getText().toString(),
                        pssw.getText().toString()));

                if(isInserted)
                {
                    Toast.makeText(getBaseContext(),"data inserted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getBaseContext(),"data not inserted",Toast.LENGTH_LONG).show();
                }
            }
                else
                {
                Toast.makeText(getBaseContext(),"data already inserted",Toast.LENGTH_LONG).show();
                }


        }
    }
}
