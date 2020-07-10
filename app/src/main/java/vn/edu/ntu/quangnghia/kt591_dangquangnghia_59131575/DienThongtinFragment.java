package vn.edu.ntu.quangnghia.kt591_dangquangnghia_59131575;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class DienThongtinFragment extends Fragment {
    EditText edtDate,edtMua,edtBan;
    ImageView imvDate;
    Spinner spinner1;
    Button btnThem, btnXem;

    NavController navController;
    String loai;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_dien_thongtin, container, false);
        addView(view);
        getdata();
        addEvents();
        return view;
    }

    private void getdata() {
        imvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        StringBuilder builder = new StringBuilder();
                        builder.append(dayOfMonth)
                                .append("/")
                                .append(++month)
                                .append("/")
                                .append(year);
                        edtDate.setText(builder.toString());
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),listener
                        ,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    private void addEvents() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loai = spinner1.getSelectedItem().toString();

                ((MainActivity)getActivity()).Danhsach += edtDate.getText().toString() + "\n" + loai + "\nMua vào:" + edtMua.getText().toString()
                        +"      Bán ra: " + edtBan.getText().toString() + "\n";

                edtDate.setText("");
                edtMua.setText("");
                edtBan.setText("");
                Toast.makeText(getActivity(),"Đã thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("ds",((MainActivity)getActivity()).Danhsach);
                navController.navigate(R.id.action_dienThongtinFragment_to_hienthiThongtinFragment,data);
            }
        });
    }

    private void addView(View view) {
        edtMua = view.findViewById(R.id.edtMua);
        edtDate = view.findViewById(R.id.edtDate);
        edtBan = view.findViewById(R.id.edtBan);
        imvDate = view.findViewById(R.id.imvDate);
        btnThem = view.findViewById(R.id.btnThem);
        btnXem = view.findViewById(R.id.btnXem);
        spinner1 = view.findViewById(R.id.spinner1);

        navController = NavHostFragment.findNavController(DienThongtinFragment.this);
        ((MainActivity)getActivity()).navController = navController;

        final String[] loaidv = new String[]{"DOJI","SJC","Thế giới"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DienThongtinFragment.this.getActivity(), R.layout.support_simple_spinner_dropdown_item,loaidv);
        spinner1.setAdapter(arrayAdapter);

    }
}