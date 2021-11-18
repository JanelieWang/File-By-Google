package com.dnielfe.manager.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.dnielfe.manager.R;
import com.dnielfe.manager.adapters.BrowserTabsAdapter;
import com.dnielfe.manager.utils.SimpleUtils;

import java.io.File;

public final class CreateFolderDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Activity a = getActivity();
        final String newfolder = BrowserTabsAdapter.getCurrentBrowserFragment().mCurrentPath
                + "/" + "newfolder";

        // Set an EditText view to get user input
        final EditText inputf = new EditText(a);
        inputf.setHint("Enter path&name");
        inputf.setText(newfolder);

        final AlertDialog.Builder b = new AlertDialog.Builder(a);
        b.setTitle(R.string.createnewfolder);
        b.setMessage(R.string.createmsg);
        b.setView(inputf);
        b.setPositiveButton(R.string.create,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String temp = inputf.getText().toString();//
                        String[] array={};
                        array=temp.split("/");
                        int len=array.length;
                        String name=array[len-1];

                        String location="";//BrowserTabsAdapter.getCurrentBrowserFragment().mCurrentPath;
                        for(int i=0;i<len-1;i++){
                            location+="/";///1/2/3/new
                            location+=array[i];
                        }
                        //String location = BrowserTabsAdapter.getCurrentBrowserFragment().mCurrentPath;
                        boolean success = false;

                        if (name.length() >= 1)
                            success = SimpleUtils.createDir(new File(location, name));

                        if (success)
                            Toast.makeText(a,
                                    name + getString(R.string.created),
                                    Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(a,
                                    getString(R.string.newfolderwasnotcreated),
                                    Toast.LENGTH_SHORT).show();

                        dialog.dismiss();
                    }
                });
        b.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return b.create();
    }
}
