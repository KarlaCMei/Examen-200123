package com.example.examen200123;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.examen200123.databinding.FragmentLoginBinding;

import java.util.regex.Pattern;

public class Login extends Fragment {
private FragmentLoginBinding binding;

    public Login() {
    }


    public static Login newInstance() {
        return new Login();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((binding.editTextEmail.getText() == null || binding.editTextEmail.getText().toString().equals("") ||!validarEmail(binding.editTextEmail.getText().toString().trim())) ||
                (binding.editTextPassword.getText() == null  ||  binding.editTextPassword.getText().toString().equals("") || binding.editTextPassword.getText().toString().length()<6 ) ){
                    Toast.makeText(requireContext(), getString(R.string.mensaje_ingresar), Toast.LENGTH_LONG).show();

                } else{
                   CustomSharedPreferences.setSharedBoolean("IS_LOGIN_SHARED_KEY",true);
                    Intent intent = new Intent(requireActivity(), HomeActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                }

            }
        });
    }


    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkSession();
    }

    public void checkSession(){
        boolean isLogin = CustomSharedPreferences.getSharedBoolean("IS_LOGIN_SHARED_KEY");
        if(isLogin){
           startActivity(new Intent(requireContext(),HomeActivity.class));
            requireActivity().finish();
        }
    }


}