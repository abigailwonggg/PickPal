package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class PollAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_TEXT = 0;
    private static final int VIEW_TYPE_IMAGE = 1;

    private List<PollData> pollList;
    private Context context;
    private String currentClub; // Attribute for filtering by club

    // Constructor
    public PollAdapter(Context context, List<PollData> pollList, String currentClub) {
        this.pollList = pollList;
        this.context = context;
        this.currentClub = currentClub; // Initialize current club
    }

    @Override
    public int getItemViewType(int position) {
        PollData poll = pollList.get(position);
        return poll.hasImages() ? VIEW_TYPE_IMAGE : VIEW_TYPE_TEXT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_IMAGE) {
            View view = inflater.inflate(R.layout.poll_item_images, parent, false);
            return new PollImageViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.poll_item, parent, false);
            return new PollTextViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PollData poll = pollList.get(position);

        // Filter polls based on the current club or "Feed"
        if (!poll.getClub().equals(currentClub) && !poll.getClub().equals("Feed")) {
            holder.itemView.setVisibility(View.GONE);
            return;
        } else {
            holder.itemView.setVisibility(View.VISIBLE);
        }

        if (holder instanceof PollImageViewHolder) {
            ((PollImageViewHolder) holder).bind(poll);
        } else {
            ((PollTextViewHolder) holder).bind(poll);
        }

        // Set up button click listener to navigate to ViewPostFragment
        holder.itemView.findViewById(R.id.btn_vote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Navigating to ViewPostFragment", Toast.LENGTH_SHORT).show();
                // Navigate to ViewPostFragment
                ((MainActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, new ViewPostFragment()) // Adjust R.id.fragment_container to your actual container ID
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Set up click listener for comment_icon to navigate to ViewPostFragment
        holder.itemView.findViewById(R.id.comment_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Navigating to ViewPostFragment", Toast.LENGTH_SHORT).show();
                ((MainActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, new ViewPostFragment()) // Adjust R.id.fragment_container to your actual container ID
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pollList.size();
    }

    // ViewHolder for text-only polls
    public static class PollTextViewHolder extends RecyclerView.ViewHolder {
        TextView username, pollQuestion, voteCount;
        TextView option1, option2, option3, option4;

        public PollTextViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            pollQuestion = itemView.findViewById(R.id.poll_question);
            voteCount = itemView.findViewById(R.id.vote_count);
            option1 = itemView.findViewById(R.id.option_1);
            option2 = itemView.findViewById(R.id.option_2);
            option3 = itemView.findViewById(R.id.option_3);
            option4 = itemView.findViewById(R.id.option_4);
        }

        public void bind(PollData poll) {
            username.setText(poll.getUsername());
            pollQuestion.setText(poll.getPollQuestion());
            voteCount.setText(poll.getVoteCount() + " votes");

            // Set options and control visibility
            option1.setText(poll.getOptions().get(0));
            option2.setText(poll.getOptions().get(1));

            // Hide options 3 and 4 by default
            option3.setVisibility(View.GONE);
            option4.setVisibility(View.GONE);

            if (poll.getOptions().size() > 2) {
                option3.setVisibility(View.VISIBLE);
                option3.setText(poll.getOptions().get(2));
            }

            if (poll.getOptions().size() > 3) {
                option4.setVisibility(View.VISIBLE);
                option4.setText(poll.getOptions().get(3));
            }
        }
    }

    // ViewHolder for image polls
    public static class PollImageViewHolder extends RecyclerView.ViewHolder {
        TextView username, pollQuestion, voteCount;
        ShapeableImageView option1Image, option2Image, option3Image, option4Image;
        TextView option1Text, option2Text, option3Text, option4Text;

        public PollImageViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            pollQuestion = itemView.findViewById(R.id.poll_question);
            voteCount = itemView.findViewById(R.id.vote_count);

            option1Image = itemView.findViewById(R.id.option_1_image);
            option2Image = itemView.findViewById(R.id.option_2_image);
            option3Image = itemView.findViewById(R.id.option_3_image);
            option4Image = itemView.findViewById(R.id.option_4_image);

            option1Text = itemView.findViewById(R.id.option_1_image_text);
            option2Text = itemView.findViewById(R.id.option_2_image_text);
            option3Text = itemView.findViewById(R.id.option_3_image_text);
            option4Text = itemView.findViewById(R.id.option_4_image_text);
        }

        public void bind(PollData poll) {
            username.setText(poll.getUsername());
            pollQuestion.setText(poll.getPollQuestion());
            voteCount.setText(poll.getVoteCount() + " votes");

            // Set text for the poll options
            option1Text.setText(poll.getOptions().get(0));
            option2Text.setText(poll.getOptions().get(1));

            // Load images or use default_image drawable
            if (poll.getImageOptions().get(0).equals("default_image")) {
                option1Image.setImageResource(R.drawable.default_image); // Load default drawable
            } else {
                Glide.with(itemView.getContext()).load(poll.getImageOptions().get(0)).into(option1Image); // Load from URL
            }

            if (poll.getImageOptions().get(1).equals("default_image")) {
                option2Image.setImageResource(R.drawable.default_image);
            } else {
                Glide.with(itemView.getContext()).load(poll.getImageOptions().get(1)).into(option2Image);
            }

            // Hide options 3 and 4 by default
            option3Image.setVisibility(View.GONE);
            option4Image.setVisibility(View.GONE);
            option3Text.setVisibility(View.GONE);
            option4Text.setVisibility(View.GONE);

            if (poll.getOptions().size() > 2) {
                option3Text.setText(poll.getOptions().get(2));
                option3Text.setVisibility(View.VISIBLE);
                option3Image.setVisibility(View.VISIBLE);
                if (poll.getImageOptions().size() > 2 && poll.getImageOptions().get(2).equals("default_image")) {
                    option3Image.setImageResource(R.drawable.default_image);
                } else if (poll.getImageOptions().size() > 2) {
                    Glide.with(itemView.getContext()).load(poll.getImageOptions().get(2)).into(option3Image);
                }
            }

            if (poll.getOptions().size() > 3) {
                option4Text.setText(poll.getOptions().get(3));
                option4Text.setVisibility(View.VISIBLE);
                option4Image.setVisibility(View.VISIBLE);
                if (poll.getImageOptions().size() > 3 && poll.getImageOptions().get(3).equals("default_image")) {
                    option4Image.setImageResource(R.drawable.default_image);
                } else if (poll.getImageOptions().size() > 3) {
                    Glide.with(itemView.getContext()).load(poll.getImageOptions().get(3)).into(option4Image);
                }
            }
        }
    }
}
