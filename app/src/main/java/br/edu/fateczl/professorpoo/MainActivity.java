package br.edu.fateczl.professorpoo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.edu.fateczl.professorpoo.model.Professor;
import br.edu.fateczl.professorpoo.model.ProfessorHorista;
import br.edu.fateczl.professorpoo.model.ProfessorTitular;

public class MainActivity extends AppCompatActivity {
    /*
     *@author: Kelvin Santos Guimarães
     */

    private EditText etNome;
    private EditText etMatricula;
    private EditText etIdade;
    private EditText etAnosInstituicaoHorasAula;
    private EditText etSalarioValorHoraAula;
    private RadioButton rbTitular;
    private RadioButton rbHorista;
    private RadioGroup rgProf;
    private Button btnCalc;
    private TextView tvRes;
    private Professor prof;
    private String instituicaoHint = "Insira a quantidade de anos na instituição";
    private String horasAulaHint = "Insira a quantidade de horas/aula";
    private String salarioHint = "Insira o salário";
    private String valorHoraAulaHint = "Insira o valor hora/aula";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNome = findViewById(R.id.etNome);
        etMatricula = findViewById(R.id.etMatricula);
        etIdade = findViewById(R.id.etIdade);
        etAnosInstituicaoHorasAula = findViewById(R.id.etAnosInstituicaoHorasAula);
        etSalarioValorHoraAula = findViewById(R.id.etSalarioValorHoraAula);
        rbTitular = findViewById(R.id.rbTitular);
        rbTitular.setChecked(true);
        rbHorista = findViewById(R.id.rbHorista);
        btnCalc = findViewById(R.id.btnCalc);
        tvRes = findViewById(R.id.tvRes);
        rgProf = findViewById(R.id.rgProf);
        hintChange();
        rgProf.setOnCheckedChangeListener((buttonView, isChecked) -> hintChange());
        btnCalc.setOnClickListener(op -> calcSalario());
    }

    private void calcSalario() {
        if (rbTitular.isChecked()) {
            prof = new ProfessorTitular(etNome.getText().toString(), etMatricula.getText().toString(), Integer.valueOf(etIdade.getText().toString()), Integer.valueOf(etAnosInstituicaoHorasAula.getText().toString()), Integer.valueOf(etSalarioValorHoraAula.getText().toString()));
        } else {
            prof = new ProfessorHorista(etNome.getText().toString(), etMatricula.getText().toString(), Integer.valueOf(etIdade.getText().toString()), Integer.valueOf(etAnosInstituicaoHorasAula.getText().toString()), Integer.valueOf(etSalarioValorHoraAula.getText().toString()));
        }

        double sal = prof.calcSalario();
        BigDecimal salario = BigDecimal.valueOf(sal).setScale(2, RoundingMode.HALF_UP);
        tvRes.setText("Salário do professor é: R$" + salario);
    }

    private void hintChange() {
        if (rbTitular.isChecked()) {
            etAnosInstituicaoHorasAula.setHint(instituicaoHint);
            etSalarioValorHoraAula.setHint(salarioHint);
        } else {
            etAnosInstituicaoHorasAula.setHint(horasAulaHint);
            etSalarioValorHoraAula.setHint(valorHoraAulaHint);
        }
    }
}