package com.nikvay.schooldemo.firebaseServices;

        import android.support.annotation.NonNull;
        import android.util.Log;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.iid.InstanceIdResult;
        import com.nikvay.schooldemo.shared_pref.SharedPreference;
        import com.google.firebase.iid.FirebaseInstanceId;
        import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseIDService";



   /* @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        *//*String refreshedToken = FirebaseInstanceId. get().getToken();*//*

        Log.d(TAG, "Refreshed token: " + refreshedToken);

        SharedPreference.putDeviceToken(getApplicationContext(),refreshedToken);
    }*/
}
