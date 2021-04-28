package com.va.neoapp.presentation.chat;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.va.neoapp.R;
import com.va.neoapp.adapters.MessagesAdapter;
import com.va.neoapp.models.ChatItem;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.util.GlobalMethods;

import java.util.ArrayList;
import java.util.List;


public class MessagingAct extends BaseActivity {

    //private FirebaseUser firebaseUser;

    private RecyclerView chat_list_view;
    private List<ChatItem> chatItemList;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_messaging;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        chat_list_view = findViewById(R.id.chat_list_view);
        chat_list_view.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);
        chat_list_view.setLayoutManager(mLinearLayoutManager);


//        String user_id = getIntent().getStringExtra("user_id");
//        showUI(user_id);

        actionEvents();
    }

    private void showUI(String user_id) {
        /*try {
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.DB_REFERENCE).child(user_id);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    User user = snapshot.getValue(User.class);
                    ((AppCompatTextView) findViewById(R.id.user_name)).setText(user.getUsername());

                    if (user.getStatus().equalsIgnoreCase("online")) {
                        ((AppCompatTextView) findViewById(R.id.user_status)).setText("Online");
                    } else {
                        ((AppCompatTextView) findViewById(R.id.user_status)).setText("Offline");
                    }

                    readMessage(firebaseUser.getUid(), user_id,"");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            findViewById(R.id.send_message).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String textMsg = ((AppCompatEditText) findViewById(R.id.text_input_chat)).getText().toString().trim();

                    if (!textMsg.equals("")) {

                        sendMessage(firebaseUser.getUid(), user_id, textMsg);

                    } else {
                        Toast.makeText(com.va.avdemo.activities.MessagingAct.this, "You cant send an empty message", Toast.LENGTH_SHORT).show();
                    }

                }
            });


        } catch (Exception excep) {
            Log.e("exp_show_ui", excep.getMessage());

        }*/

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
        findViewById(R.id.chat_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendMessage(String sender, String receiver, String message) {
/*
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);
        hashMap.put("time", GlobalMethods.getCurrentDateFormat());

        reference.child("Chats").push().setValue(hashMap);*/

        ((AppCompatEditText) findViewById(R.id.text_input_chat)).setText("");
        GlobalMethods.hideKeyboard(MessagingAct.this);
    }

    private void readMessage(String myId, String userId, String imageUrl) {
        chatItemList = new ArrayList<>();
        /*DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatItemList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatItem chatItem = snapshot.getValue(ChatItem.class);

                    if ((chatItem.getReceiver().equalsIgnoreCase(myId) &&
                            chatItem.getSender().equalsIgnoreCase(userId)) ||

                            (chatItem.getReceiver().equalsIgnoreCase(userId) &&
                                    chatItem.getSender().equalsIgnoreCase(myId))
                    ) {

                        chatItemList.add(chatItem);
                    }
                }

                MessagesAdapter messagesAdapter = new MessagesAdapter(com.va.avdemo.activities.MessagingAct.this, chatItemList);
                chat_list_view.setAdapter(messagesAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        MessagesAdapter messagesAdapter = new MessagesAdapter(MessagingAct.this, chatItemList);
        chat_list_view.setAdapter(messagesAdapter);

    }
}
