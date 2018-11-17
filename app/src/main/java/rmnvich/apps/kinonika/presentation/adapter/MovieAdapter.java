package rmnvich.apps.kinonika.presentation.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.LinkedList;
import java.util.List;

import rmnvich.apps.kinonika.R;
import rmnvich.apps.kinonika.data.entity.Movie;
import rmnvich.apps.kinonika.databinding.ItemMovieBinding;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements Filterable {

    private OnClickMovieListener mListener;
    private List<Movie> mMovieList = new LinkedList<>();
    private List<Movie> mMovieFilteredList = new LinkedList<>();

    public void setData(List<Movie> data) {
        MovieDiffUtilCallback diffUtilCallback =
                new MovieDiffUtilCallback(mMovieFilteredList, data);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);

        mMovieList = data;
        mMovieFilteredList = data;

        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMovieBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_movie, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("qwe", "position = " + position);
        holder.bind(mMovieFilteredList.get(position));
        setFadeAnimation(holder.itemView);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(450);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return mMovieFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mMovieFilteredList = mMovieList;
                } else {
                    List<Movie> filteredList = new LinkedList<>();
                    for (Movie row : mMovieList) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    mMovieFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mMovieFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mMovieFilteredList = (List<Movie>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface OnClickMovieListener {
        void onClickMovie(long movieId);

        void onLongClickMovie(long movieId, int position);
    }

    public void setOnClickMovieListener(OnClickMovieListener listener) {
        mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {

        ItemMovieBinding binding;

        ViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(this);
            binding.getRoot().setOnLongClickListener(this);
        }

        public void bind(Movie movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            mListener.onClickMovie(mMovieFilteredList.get(getAdapterPosition()).getId());
        }

        @Override
        public boolean onLongClick(View view) {
            mListener.onLongClickMovie(mMovieFilteredList.get(getAdapterPosition())
                    .getId(), getAdapterPosition());
            return false;
        }
    }
}
