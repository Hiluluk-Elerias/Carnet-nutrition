package io.github.hilulukelerias.carnetnutrition;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import com.getcapacitor.BridgeActivity;

// Marges autour de l'appli en affichage bord à bord (Android 15+) :
// - en haut : 85 % de la hauteur de la barre d'état (l'heure reste visible, un peu plus compact) ;
// - en bas : la hauteur de la barre de navigation seulement si elle est haute (navigation à 3 boutons),
//   rien avec la navigation par gestes (fine barre).
// Le clavier reste géré par la WebView (insets IME transmis tels quels).
public class MainActivity extends BridgeActivity {
    private static final float TOP_RATIO = 0.85f;
    private static final int GESTURE_MAX_DP = 36;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        View webView = getBridge().getWebView();
        final View parent = (View) webView.getParent();
        parent.setBackgroundColor(Color.parseColor("#14181d"));
        WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView()).setAppearanceLightStatusBars(false);
        final float density = getResources().getDisplayMetrics().density;
        ViewCompat.setOnApplyWindowInsetsListener(parent, (v, insets) -> {
            int types = WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout();
            Insets bars = insets.getInsets(types);
            int top = Math.round(bars.top * TOP_RATIO);
            int bottom = bars.bottom > GESTURE_MAX_DP * density ? bars.bottom : 0;
            v.setPadding(bars.left, top, bars.right, bottom);
            // La WebView ne voit plus les barres système (déjà gérées ici), mais garde le clavier
            return new WindowInsetsCompat.Builder(insets).setInsets(types, Insets.NONE).build();
        });
        ViewCompat.requestApplyInsets(parent);
    }
}
