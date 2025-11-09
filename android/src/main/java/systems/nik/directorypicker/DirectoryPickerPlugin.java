package systems.nik.directorypicker;

import android.content.Intent;
import android.net.Uri;

import androidx.activity.result.ActivityResult;
import androidx.documentfile.provider.DocumentFile;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.io.InputStream;
import java.io.OutputStream;

@CapacitorPlugin(name = "DirectoryPicker")
public class DirectoryPickerPlugin extends Plugin {

    @PluginMethod
    public void pickDirectory(PluginCall call) {
        // launch directory picker
        var intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

        startActivityForResult(call, intent, "pickDirectoryResult");
    }

    @ActivityCallback
    private void pickDirectoryResult(PluginCall call, ActivityResult result) {
        if (call == null) return;

        if (result.getData() == null) {
            call.reject("Failed to get directory.");
            return;
        }

        var uri = result.getData().getData();
        if (uri == null) {
            call.reject("No directory selected.");
            return;
        }

        // Persist permissions
        final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
        getContext().getContentResolver().takePersistableUriPermission(uri, takeFlags);


        // Return URI string to JS
        JSObject ret = new JSObject();
        ret.put("uri", uri.toString());
        call.resolve(ret);
    }

    @PluginMethod
    public void readFilesFromDirectory(PluginCall call) {
        var directoryUriString = call.getString("uri");
        if (directoryUriString == null) {
            call.reject("Must provide URI string");
            return;
        }

        var directoryUri = Uri.parse(directoryUriString);
        var directory = DocumentFile.fromTreeUri(getContext(), directoryUri);


        if (directory == null || !directory.isDirectory()) {
            call.reject("Invalid directory URI.");
            return;
        }

        var filesArray = new JSArray();

        for (var file : directory.listFiles()) {
            if (!file.isFile()) continue;

            var fileData = new JSObject();
            fileData.put("name", file.getName());
            fileData.put("uri", file.getUri().toString());
            fileData.put("type", file.getType());
            fileData.put("size", file.length());
            fileData.put("lastModified", file.lastModified());

            filesArray.put(fileData);
        }

        var result = new JSObject();
        result.put("files", filesArray);
        call.resolve(result);
    }

    @PluginMethod
    public void copy(PluginCall call) {
        String fromFileUriString = call.getString("from");
        String toDirectoryUriString = call.getString("to");

        if (fromFileUriString == null || fromFileUriString.isEmpty()) {
            call.reject("Missing 'from' file URI.");
            return;
        }
        if (toDirectoryUriString == null || toDirectoryUriString.isEmpty()) {
            call.reject("Missing 'to' directory URI.");
            return;
        }

        try {
            Uri fromFileUri = Uri.parse(fromFileUriString);
            Uri toDirectoryUri = Uri.parse(toDirectoryUriString);

            DocumentFile fromFile = DocumentFile.fromSingleUri(getContext(), fromFileUri);
            if (fromFile == null || !fromFile.isFile()) {
                call.reject("Invalid 'from' URI: not a file.");
                return;
            }
            String fileName = fromFile.getName();
            String mimeType = fromFile.getType();

            DocumentFile toDirectory = DocumentFile.fromTreeUri(getContext(), toDirectoryUri);
            if (toDirectory == null || !toDirectory.isDirectory()) {
                call.reject("Invalid 'to' URI: not a directory.");
                return;
            }

            // check if file already exists
            DocumentFile existingFile = toDirectory.findFile(fileName);
            if (existingFile != null && existingFile.isFile()) {
                call.reject("File already exists in destination: " + fileName);
                return;
            }

            DocumentFile newFile = toDirectory.createFile(mimeType, fileName);
            if (newFile == null) {
                call.reject("Failed to create destination file.");
                return;
            }

            // copy data using streams
            try (
                    InputStream in = getContext().getContentResolver().openInputStream(fromFile.getUri());
                    OutputStream out = getContext().getContentResolver().openOutputStream(newFile.getUri())
            ) {
                if (in == null || out == null) {
                    call.reject("Failed to open file streams.");
                    return;
                }

                byte[] buffer = new byte[4096]; // 4K buffer
                int len;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
            }

            JSObject result = new JSObject();
            result.put("uri", newFile.getUri().toString());
            result.put("name", newFile.getName());
            call.resolve(result);

        } catch (Exception e) {
            call.reject("Copy failed: " + e.getMessage());
        }
    }
}
