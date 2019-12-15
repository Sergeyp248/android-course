package com.example.usersstorageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.usersstorageapp.dao.UserDAO;
import com.example.usersstorageapp.database.AppDatabase;
import com.example.usersstorageapp.entities.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase repository;
    private UserDAO userDao;
    private TextView viewForPrimeNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
        userDao = repository.userDao();

        this.viewForPrimeNumbers = findViewById(R.id.usersTextView);

        this.viewForPrimeNumbers.setText(userDao.findAll().toString());
    }

    public void insertUser(View view) {
        User user = new User();
        user.setFirstName(((TextView) findViewById(R.id.firstName)).getText().toString());
        user.setLastName(((TextView) findViewById(R.id.lastName)).getText().toString());
        user.setBirthYear(((TextView) findViewById(R.id.birthDate)).getText().toString());
        userDao.insertUser(user);
    }

    public void findUser(View view) {
        EditText editTextSearch = findViewById(R.id.searchUser);
        String searchValue = editTextSearch.getText().toString();
        User getUser = userDao.getByFirstNameOrLastname(searchValue);
        TextView textView = findViewById(R.id.searchUserTextView);
        if (getUser != null)
            textView.setText(getUser.toString());
    }

    public void getAllUsers(View view) {
        List<User> users = userDao.findAll();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            result.append(users.get(i).toString());
            result.append("\n");
        }
        this.viewForPrimeNumbers.setText(result);
    }
}