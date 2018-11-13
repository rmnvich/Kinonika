package rmnvich.apps.kinonika.presentation.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rmnvich.apps.kinonika.R;

public class DialogFilter extends MaterialDialog.Builder implements MaterialDialog.SingleButtonCallback {

    private DialogFilterCallbackListener mListener;

    @BindView(R.id.spinner_genre)
    Spinner mSpinnerGenre;

    @BindView(R.id.spinner_tag)
    Spinner mSpinnerTag;

    @BindView(R.id.spinner_rating)
    Spinner mSpinnerRating;

    @BindView(R.id.spinner_years)
    Spinner mSpinnerYears;

    private String genre = "";
    private String tag = "";
    private String year = "";
    private int rating = 0;

    @SuppressLint({"InflateParams", "NewApi"})
    public DialogFilter(Context context, ArrayAdapter<String> adapterGenre,
                        ArrayAdapter<String> adapterRating,
                        ArrayAdapter<String> adapterYears) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert inflater != null;
        View view = inflater.inflate(R.layout.dialog_filter, null);
        ButterKnife.bind(this, view);

        mSpinnerGenre.setAdapter(adapterGenre);
        mSpinnerYears.setAdapter(adapterYears);
        mSpinnerRating.setAdapter(adapterRating);

        mSpinnerGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genre = adapterGenre.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        mSpinnerRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rating = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        mSpinnerYears.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = adapterYears.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        this.title(R.string.filter)
                .customView(view, true)
                .backgroundColor(context.getColor(R.color.colorPrimary))
                .icon(context.getDrawable(R.drawable.ic_action_filter_inverted))
                .titleColor(Color.WHITE)
                .positiveText(R.string.apply)
                .negativeText(R.string.cancel)
                .autoDismiss(false)
                .cancelable(false)
                .onPositive(this)
                .onNegative(((currentDialog, which) -> currentDialog.dismiss()));
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        if (genre.isEmpty() && tag.isEmpty() && year.isEmpty() && rating == 0) {
            dialog.dismiss();
        } else {
            mListener.onClickApply(genre, tag, rating, year);
            dialog.dismiss();
        }
    }

    public void show(DialogFilterCallbackListener listener, List<String> tags) {
        if (mListener == null) {
            mListener = listener;
        }
        tags.add(0, "");
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, tags);
        mSpinnerTag.setAdapter(adapter);
        mSpinnerTag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tag = (String) adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        this.show();
    }

    public interface DialogFilterCallbackListener {
        void onClickApply(String genre, String tag, int rating, String year);
    }
}
