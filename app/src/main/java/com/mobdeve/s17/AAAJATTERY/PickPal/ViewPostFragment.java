package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewPostFragment extends Fragment {

    private RecyclerView recyclerViewComments;
    private Button buttonLeaveComment;
    private TextView textPost, textNumberVotes;
    private EditText editComment;
    private CommentAdapter commentAdapter;
    private List<CommentData> commentList;
    private ImageButton backButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_post, container, false);

        // Initialize views
        recyclerViewComments = view.findViewById(R.id.recycler_view_comments);
        buttonLeaveComment = view.findViewById(R.id.btn_comment);
        textPost = view.findViewById(R.id.caption);
        textNumberVotes = view.findViewById(R.id.number_votes);
        editComment = view.findViewById(R.id.edit_comment);
        backButton = view.findViewById(R.id.btn_back);

        // Set up RecyclerView
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(getActivity()));
        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(getActivity(), commentList);
        recyclerViewComments.setAdapter(commentAdapter);

        // Load post data (this is just an example, you should load actual data)
        loadPostData();

        // Set click listener for the Leave Comment button
        buttonLeaveComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editComment.getVisibility() == View.GONE) {
                    // Show the EditText to allow the user to type a comment
                    editComment.setVisibility(View.VISIBLE);
                    buttonLeaveComment.setText("Publish Comment");
                } else {
                    // User clicked "Publish Comment"
                    String commentText = editComment.getText().toString().trim();
                    if (!commentText.isEmpty()) {
                        // Add comment to the list
                        CommentData newComment = new CommentData("username", commentText, "Just now", R.drawable.user_pic);
                        commentList.add(newComment);
                        commentAdapter.notifyItemInserted(commentList.size() - 1);

                        // Clear the input and reset button
                        editComment.setText("");
                        editComment.setVisibility(View.GONE);
                        buttonLeaveComment.setText("Leave a comment");

                        // Scroll to the newly added comment
                        recyclerViewComments.smoothScrollToPosition(commentList.size() - 1);
                    }
                }
            }
        });

        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed(); // Go back to the previous fragment
            }
        });

        return view;
    }

    private void loadPostData() {
        // Replace this with actual post data loading logic
        textPost.setText("Where should I go on October 17");
        textNumberVotes.setText("1000 votes");

        // Load some dummy comments
        commentList.add(new CommentData("mikmik", "THE ANSWER IS SO OBVIOUS", "5 mins ago", R.drawable.user_pic));
        commentList.add(new CommentData("labubu", "YES", "1 day ago", R.drawable.user_pic));
        commentList.add(new CommentData("kiki", "I broke the tie", "1 week ago", R.drawable.user_pic));

        // Notify adapter to refresh the data
        commentAdapter.notifyDataSetChanged();
    }
}