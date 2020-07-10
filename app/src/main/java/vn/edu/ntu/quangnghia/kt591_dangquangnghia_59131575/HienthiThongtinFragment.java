package vn.edu.ntu.quangnghia.kt591_dangquangnghia_59131575;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HienthiThongtinFragment extends Fragment {

    TextView txtHienthi;
    String danhsach;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hienthi_thongtin, container, false);
        addViews(view);
        getdata();
        return view;
    }

    private void getdata() {
        Bundle data = getArguments();
        danhsach = data.getString("ds");
        txtHienthi.setText(danhsach);
    }

    private void addViews(View view) {
        txtHienthi = view.findViewById(R.id.txtHienthi);

        navController = NavHostFragment.findNavController(HienthiThongtinFragment.this);
        ((MainActivity)getActivity()).navController = navController;
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}