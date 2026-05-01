package br.com.fiap.recipesfiap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.recipesfiap.navigation.Destination
import br.com.fiap.recipesfiap.ui.theme.MediBackground
import br.com.fiap.recipesfiap.ui.theme.MediBlue
import br.com.fiap.recipesfiap.ui.theme.MediCardBg
import br.com.fiap.recipesfiap.ui.theme.MediIconBg
import br.com.fiap.recipesfiap.ui.theme.MediPrimary
import br.com.fiap.recipesfiap.ui.theme.MediSubtext
import br.com.fiap.recipesfiap.ui.theme.MediSuccess

@Composable
fun SplashScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Logo e título
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(MediIconBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Assignment,
                        contentDescription = "MediStock logo",
                        tint = MediPrimary,
                        modifier = Modifier.size(56.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = MediPrimary,
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.End)
                        .padding(end = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "MediStock",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Gestão inteligente de insumos\nhospitalares com IA",
                    fontSize = 14.sp,
                    color = MediSubtext,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }

            // Features
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FeatureItem(
                    dotColor = MediBlue,
                    text = "Monitoramento de estoque em tempo real"
                )
                FeatureItem(
                    dotColor = MediPrimary,
                    text = "Previsão de demanda com inteligência artificial"
                )
                FeatureItem(
                    dotColor = MediSuccess,
                    text = "Rastreamento de entregas e alertas automáticos"
                )
            }

            // Botões
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate(Destination.LoginScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MediPrimary)
                ) {
                    Text(text = "Entrar", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
                OutlinedButton(
                    onClick = { navController.navigate(Destination.CreditsScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Text(text = "Saiba mais", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "v1.0 · Seguro e conforme com a LGPD",
                    fontSize = 11.sp,
                    color = MediSubtext
                )
            }
        }
    }
}

@Composable
private fun FeatureItem(dotColor: Color, text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MediCardBg)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(RoundedCornerShape(50))
                    .background(dotColor)
            )
            Text(text = text, fontSize = 14.sp, color = Color.White)
        }
    }
}
