package rmnvich.apps.kinonika.data.repository.local;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;

public class LocalRepository {

    private final Context mContext;

    public LocalRepository(Context mContext) {
        this.mContext = mContext;
    }

    public String getRealPathFromUri(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(mContext, contentUri, projection,
                null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index;
        try {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        } catch (NullPointerException e) {
            return contentUri.getPath();
        }
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    public Bitmap getBitmap(Uri contentUri) throws IOException {
        return MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), contentUri);
    }
}
