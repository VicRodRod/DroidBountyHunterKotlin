package mx.dwtraining.droidbountyhunter.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

object PermissionUtils {

    fun permissionReadMemory(context: Activity): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA)) {
                    ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.CAMERA),
                        PictureTools.REQUEST_CODE
                    )
                    return false
                } else {
                    //No explanation needed, we can request the permissions.
                    ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.CAMERA),
                        PictureTools.REQUEST_CODE
                    )
                    return false
                }
            } else {
                return true
            }
        } else {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
                // Should we show an explanation
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA) ) {
                    ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
                        PictureTools.REQUEST_CODE
                    )
                    return false
                } else {
                    //No explanation needed, we can request the permissions.
                    ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
                        PictureTools.REQUEST_CODE
                    )
                    return false
                }
            } else {
                return true
            }
        }
    }

    /** PERMISO PARA GPS **/
    fun permissionUseGPS(context: Activity, requestCode: Int): Boolean {
        return if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(context,Manifest.permission.ACCESS_FINE_LOCATION)){
                /** SE DEBERIA CREAR ALERTDIALOG/ CUANDOD DE OK PEDIR PERMISO**/
                ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), requestCode)
                false
            }else{
                ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), requestCode)
                false
            }
        }else{
            true
        }
    }
}