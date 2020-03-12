package cl.ucn.disc.dsm.fuenz.thenews;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApplication extends Application{
    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    /**
     * Called when the application is starting, before any activity, service, or receiver objects (excluding content
     * providers) have been created.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // Day and Night support
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);


        // Facebook fresco
        Fresco.initialize(this);

        log.debug("Initializing: Done.");
    }
}
