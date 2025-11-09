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
}
