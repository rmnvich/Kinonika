package rmnvich.apps.kinonika.data.repository.local;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

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

    public String saveToInternalStorage(Uri contentUri) {
        try {
            Bitmap loadedBitmap = MediaStore.Images.Media
                    .getBitmap(mContext.getContentResolver(), contentUri);
            ContextWrapper cw = new ContextWrapper(mContext);

            File directory = cw.getDir("Posters", Context.MODE_PRIVATE);
            if (!directory.exists()) {
                directory.mkdir();
            }

            String imageName = "kinonika_" + Calendar.getInstance().getTimeInMillis();
            File file = new File(directory, imageName + ".png");
            String imagePath = directory.getAbsolutePath() + "/" + imageName + ".png";

            FileOutputStream fos;
            fos = new FileOutputStream(file);
            loadedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

            return imagePath;
        } catch (IOException e) {
            Log.d("qwe", e.getMessage());
            return "";
        }
    }

    private Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }
}