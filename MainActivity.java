    package alen.mirea.turistinrussia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import Models.User;

    public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnRegister;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = findViewById(R.id.signin);
        btnRegister = findViewById(R.id.register);

        root = findViewById(R.id.root_element);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterWindow();
            }

        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSigiInWindow();
            }

        });
    }

        private void showSigiInWindow() {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Войти");
            dialog.setMessage("Введите все данные для входа");
            LayoutInflater inflater = LayoutInflater.from(this);
            View signwin = inflater.inflate(R.layout.signwin, null);
            dialog.setView(signwin);

            final MaterialEditText email = signwin.findViewById(R.id.emailfield);
            final MaterialEditText pass = signwin.findViewById(R.id.passfield);

            dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    if(TextUtils.isEmpty(email.getText().toString())){
                        Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(pass.getText().toString().length()<5){
                        Snackbar.make(root, "Введите пароль", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    auth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(MainActivity.this, MapActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Snackbar.make(root, "Ошибка авторизации"+ e.getMessage(), Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }

            });

            dialog.show();
        }

        private void showRegisterWindow() {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Зарегистрироваться");
            dialog.setMessage("Введите все данные для регистрации");
            LayoutInflater inflater = LayoutInflater.from(this);
            View registerwin = inflater.inflate(R.layout.regwin, null);
            dialog.setView(registerwin);

            final MaterialEditText email = registerwin.findViewById(R.id.emailfield);
            final MaterialEditText pass = registerwin.findViewById(R.id.passfield);
            final MaterialEditText name = registerwin.findViewById(R.id.namefield);

            dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            dialog.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    if(TextUtils.isEmpty(email.getText().toString())){
                        Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(name.getText().toString())){
                        Snackbar.make(root, "Введите ваше имя", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(pass.getText().toString().length()<5){
                        Snackbar.make(root, "Введите пароль, который больше 5 символов", Snackbar.LENGTH_SHORT).show();
                        return;
                    }


                    auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    User user = new User();
                                    user.setEmail(email.getText().toString());
                                    user.setName(name.getText().toString());
                                    user.setPass(pass.getText().toString());

                                    users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Snackbar.make(root, "Пользователь добавлен!", Snackbar.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Snackbar.make(root, "Ошибка регистрации"+ e.getMessage(),Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            dialog.show();

        }
    }