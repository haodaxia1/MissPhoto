package com.example.administrator.missphoto;

        import android.app.Fragment;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.ImageView;


public class CameraFragment extends Fragment {
    private View view;
    private Context context;
    private ImageView IvCameraHeadportrait;

    @Nullable

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_camera, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        IvCameraHeadportrait=(ImageView)view.findViewById(R.id.IvCameraHeadportrait);
        IvCameraHeadportrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), EntryActivity.class);
                startActivity(intent);
            }
        });
    }
}
