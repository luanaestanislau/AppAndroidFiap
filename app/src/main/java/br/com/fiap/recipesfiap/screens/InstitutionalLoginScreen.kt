package br.com.fiap.recipesfiap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.recipesfiap.navigation.Destination
import br.com.fiap.recipesfiap.ui.theme.*

private val hospitalDomains = listOf("hospital.com.br", "hcor.com.br", "einstein.br", "hsl.org.br", "fiap.com.br")

@Composable
fun InstitutionalLoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val domain = email.substringAfter("@", "")
    val isValidDomain = domain.isNotEmpty() && hospitalDomains.any { domain.equals(it, ignoreCase = true) }
    val hasAt = email.contains("@")

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
        Text("Acesso Institucional", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MediOnBackground)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Use o e-mail fornecido pelo hospital", fontSize = 13.sp, color = MediOnSurface, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail institucional") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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

        if (hasAt && isValidDomain) {
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MediSuccess.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                    .border(1.dp, MediSuccess, RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Text("✓ Domínio institucional reconhecido: $domain", color = MediSuccess, fontSize = 13.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { navController.navigate(Destination.ConfirmData.route) },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MediPrimary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Verificar e continuar", color = Color.White, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MediWarning.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
                .border(1.dp, MediWarning, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text(
                "Exemplo de bloqueio: joao@gmail.com — domínio não autorizado. Contate o departamento de TI.",
                color = MediWarning,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Text(
            "Acesso restrito · LGPD · Criptografia ponta a ponta",
            fontSize = 11.sp,
            color = MediOnSurface,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
