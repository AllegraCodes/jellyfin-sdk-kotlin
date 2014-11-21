package mediabrowser.apiinteraction.android.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import mediabrowser.apiinteraction.*;
import mediabrowser.apiinteraction.android.*;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.apiinteraction.sync.MultiServerSync;
import mediabrowser.apiinteraction.tasks.CancellationTokenSource;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.ClientCapabilities;

public class MediaSyncAdapter extends AbstractThreadedSyncAdapter {

    public MediaSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {

        Context context = getContext();

        ILogger logger = new ConsoleLogger();
        IJsonSerializer jsonSerializer = new GsonJsonSerializer();

        IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, context);

        ClientCapabilities capabilities = new ClientCapabilities();
        capabilities.setSupportsContentUploading(true);

        ApiEventListener apiEventListener = new ApiEventListener();

        IConnectionManager connectionManager = new AndroidConnectionManager(context,
                jsonSerializer,
                logger,
                volleyHttpClient,
                "App Name",
                "App Version",
                capabilities,
                apiEventListener);

        connectionManager.setReportCapabilitiesEnabled(false);
        connectionManager.setWebSocketEnabled(false);

        CancellationTokenSource source = new CancellationTokenSource();

        new MultiServerSync(connectionManager, logger).Sync(source.getToken(), new Progress<Double>() {

            @Override
            public void onProgress(Double percent) {


            }

            @Override
            public void onComplete() {


            }

            @Override
            public void onCancelled() {

            }

            @Override
            public void onError(Exception ex) {


            }
        });
    }
}