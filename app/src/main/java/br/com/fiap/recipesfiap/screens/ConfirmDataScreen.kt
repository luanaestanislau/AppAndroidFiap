package br.com.fiap.recipesfiap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.recipesfiap.navigation.Destination
import br.com.fiap.recipesfiap.ui.theme.*

@Composable
fun ConfirmDataScreen(navController: NavController) {
    var matricula by remember { mutableStateOf("MAT-2024-0892") }
    var departamento by remember { mutableStateOf("Farmácia Hospitalar") }
    var cargo by remember { mutableStateOf("Farmacêutico Responsável") }
    var registro by remember { mutableStateOf("CRF-SP 45321") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = null,
            tint = MediPrimary,
            modifier = Modifier.size(56.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Confirme seus dados funcionais", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MediOnBackground, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(32.dp))

        listOf(
            "Matrícula funcional" to matricula,
            "Departamento" to departamento,
            "Cargo/Função" to cargo,
            "Registro profissional (CRF/CRM/etc.)" to registro
        ).forEach { (label, value) ->
            OutlinedTextField(
                value = value,
                onValueChange = {},
                label = { Text(label) },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MediPrimary,
                    unfocusedBorderColor = MediOnSurface,
                    focusedLabelColor = MediPrimary,
                    unfocusedLabelColor = MediOnSurface,
                    focusedTextColor = MediOnBackground,
                    unfocusedTextColor = MediOnBackground,
                    cursorColor = MediPrimary
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MediSuccess.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                .border(1.dp, MediSuccess, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text("✓ Matrícula encontrada na base do hospital. Dados conferidos com o RH.", color = MediSuccess, fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { navController.navigate(Destination.Dashboard.route) },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MediPrimary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Confirmar e avançar", color = Color.White, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.weight(1f))
        Text(
            "Dados validados pelo sistema de RH hospitalar via API interna. Matrícula inativa ou sem vínculo será recusada.",
            fontSize = 11.sp,
            color = MediOnSurface,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
