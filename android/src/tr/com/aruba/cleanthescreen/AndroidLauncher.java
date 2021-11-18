package tr.com.aruba.cleanthescreen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import tr.com.aruba.cleanthescreen.ads.AdInitializer;
import com.xlipstudio.cleanthescreen.client.Client;
import com.xlipstudio.cleanthescreen.client.GameClient;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class AndroidLauncher extends AndroidApplication implements AndroidUnit {
    private AdInitializer adInitializer;
    private GameClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        adInitializer = new AdInitializer(this);


        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.numSamples = 4;

        final CleanTheScreenGame game = new CleanTheScreenGame();
        View gameView = initializeForView(game, config);


        try {
            client = new StartGameClient().execute(game).get();
            if (client != null) {
                CleanTheScreenGame.setGameClient(client);

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setContentView(adInitializer.getView(gameView));
        CleanTheScreenGame.setAndroidUnit(this);

    }

    @Override
    public void exit() {
        client.disconnect();
        super.exit();
    }

    class StartGameClient extends AsyncTask<CleanTheScreenGame, Void, GameClient> {

        private Exception exception;

        protected GameClient doInBackground(CleanTheScreenGame... games) {
            try {
                String android_id = Settings.Secure.getString(getContext().getContentResolver(),
                        Settings.Secure.ANDROID_ID);

                final Client client = new Client(games[0], android_id, "51.38.126.60" /*"10.0.2.2"*/);
                //final Client client = new Client(games[0], android_id, "192.168.1.24" /*"10.0.2.2"*/);
                client.start();


                return client;
            } catch (Exception e) {
                this.exception = e;

                return null;
            }
        }

        protected void onPostExecute(GameClient feed) {
            if (this.exception != null) {
                Toast.makeText(AndroidLauncher.this, "Not able to connect :(", Toast.LENGTH_SHORT).show();
            }
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }

    @Override
    public void share_score(File f) {

    }

    @Override
    public AchievementService getAchievementService() {
        return null;
    }

    @Override
    public LeaderBoardService getLeaderBoardService() {
        return null;
    }

    @Override
    public AdUnit getAdUnit() {
        return adInitializer;
    }

    @Override
    public AnalyticTracker getAnalyticTracker() {
        return null;
    }
}
