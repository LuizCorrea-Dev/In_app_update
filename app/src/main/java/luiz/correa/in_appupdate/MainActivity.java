package luiz.correa.in_appupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int ALL_PERMISSIONS_REQUEST = 1000;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);

        TextView txtVersion = findViewById(R.id.txt_version);
        txtVersion.setText(R.string.version);

        requestPermission();


        Button btnUpdate = (Button) findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                progressDialog.show();
             
                String url = "https://github.com/LuizCorrea-Dev/In_app_update/blob/version_2.0.0/debug/app-debug.apk?raw=true";
                String url2 = "https://github.com/TripathiViky/AndroidAppDeploymentTest/blob/main/app-debug.apk?raw=true";

                AppUpdater app = new AppUpdater(MainActivity.this);

                app.updateAppWithUrl(url, "Update 2.0");

            }
        });
    }


    /**
     * Solicitar permissões dinamicamente
     */
    private void requestPermission() {

        if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                this.checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
                this.checkSelfPermission(Manifest.permission.REQUEST_INSTALL_PACKAGES) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.REQUEST_INSTALL_PACKAGES
            }, ALL_PERMISSIONS_REQUEST);

        } else {
            Log.d("Home", "Already granted access");
        }

    }

}
