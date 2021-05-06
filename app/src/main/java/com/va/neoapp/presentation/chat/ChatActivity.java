package com.va.neoapp.presentation.chat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.va.neoapp.R;
import com.va.neoapp.adapters.ChatAdapter;
import com.va.neoapp.models.ChatMain;
import com.va.neoapp.presentation.BaseActivity;

import java.util.ArrayList;

public class ChatActivity extends BaseActivity {

    private ArrayList<ChatMain> usersList;
    private RecyclerView userRecyclerView;

    private ProgressDialog progressDialog;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_chat;
    }


    @Override
    protected void initGUI(Bundle savedInstanceState) {

        userRecyclerView = findViewById(R.id.chat_users_list);
        userRecyclerView.setItemAnimator(new DefaultItemAnimator());
        userRecyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this, LinearLayoutManager.VERTICAL, false));

        usersList = new ArrayList<>();


        // progressDialog =  GlobalMethods.showProgress(ChatActivity.this);
        //api request
        //
        //

        //GlobalMethods.hideProgress(progressDialog);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        // start rlistening
    }

    @Override
    protected void onPause() {
        super.onPause();
        // stop listenning
    }

    private void actionEvents() {
       /* findViewById(R.id.chat_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }

    private void fetchUsersList() {

        //FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        /*DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.DB_REFERENCE);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                usersList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    assert user != null;
                    assert firebaseUser != null;
                    if (!user.getId().equalsIgnoreCase(firebaseUser.getUid())) {
                        usersList.add(user);
                    }

                }

                updateUI(usersList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

    }

    private void updateUI(ArrayList<ChatMain> usersList) {
        ChatAdapter usersAdapter = new ChatAdapter(ChatActivity.this, usersList);
        userRecyclerView.setAdapter(usersAdapter);
        usersAdapter.setOnItemClickListener(new ChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ChatMain user) {

                Intent intent = new Intent(ChatActivity.this, MessagingAct.class);
                intent.putExtra("user_id", user.getId());
                startActivity(intent);

            }
        });
    }
}
