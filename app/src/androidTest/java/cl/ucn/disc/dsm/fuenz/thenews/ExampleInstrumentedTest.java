package cl.ucn.disc.dsm.fuenz.thenews;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import cl.ucn.disc.dsm.fuenz.thenews.model.Noticia;
import cl.ucn.disc.dsm.fuenz.thenews.services.theguardian.TheGuardianApi;
import cl.ucn.disc.dsm.fuenz.thenews.services.theguardian.TheGuardianNoticiaService;
import cl.ucn.disc.dsm.fuenz.thenews.services.theguardian.TheGuardianResult;
import retrofit2.Call;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ExampleInstrumentedTest.class);
    @Test
    public void useAppContext() {
        log.debug("Obteniendo noticicas");
        TheGuardianNoticiaService theGuardianNoticiaService = new TheGuardianNoticiaService();

        final Call<TheGuardianResult> theGuardianCall = theGuardianNoticiaService.getTheGuardianApi()
                .getContent(
                        TheGuardianApi.API_KEY,
                        20,
                        "standfirst,thumbnail");

        List<Noticia> noticias = theGuardianNoticiaService.getNoticiasFromCall(theGuardianCall);

        for(Noticia noticia : noticias){
            log.debug(noticia.getResumen());

        }
    }
}