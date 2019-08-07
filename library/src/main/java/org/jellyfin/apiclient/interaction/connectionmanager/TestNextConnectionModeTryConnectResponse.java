package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.ConnectionResult;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.apiclient.ConnectionMode;
import org.jellyfin.apiclient.model.apiclient.ConnectionOptions;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.logging.ILogger;
import org.jellyfin.apiclient.model.system.PublicSystemInfo;

import java.util.ArrayList;

/**
 * Created by Luke on 2/21/2015.
 */
public class TestNextConnectionModeTryConnectResponse extends Response<PublicSystemInfo> {

    private ConnectionManager connectionManager;
    private ServerInfo server;
    private ArrayList<ConnectionMode> tests;
    private ConnectionMode mode;
    private String address;
    private int finalTimeout;
    private ConnectionOptions options;
    private int index;
    private boolean isLocalNetworkAvailable;
    private long wakeOnLanSendTime;
    private ILogger logger;
    private Response<ConnectionResult> response;

    public TestNextConnectionModeTryConnectResponse(ConnectionManager connectionManager, ServerInfo server, ArrayList<ConnectionMode> tests, ConnectionMode mode, String address, int finalTimeout, ConnectionOptions options, int index, boolean isLocalNetworkAvailable, long wakeOnLanSendTime, ILogger logger, Response<ConnectionResult> response) {
        this.connectionManager = connectionManager;
        this.server = server;
        this.tests = tests;
        this.mode = mode;
        this.address = address;
        this.finalTimeout = finalTimeout;
        this.options = options;
        this.index = index;
        this.isLocalNetworkAvailable = isLocalNetworkAvailable;
        this.wakeOnLanSendTime = wakeOnLanSendTime;
        this.logger = logger;
        this.response = response;
    }

    @Override
    public void onResponse(PublicSystemInfo result) {

        if (result != null){
            if (this.server.getId() != null && !this.server.getId().equals(result.getId())) {
                onError(new Exception("Invalid server"));
            } else {
                connectionManager.OnSuccessfulConnection(server, result, mode, options, response);
            }
        }

        else{

            logger.Error("Somehow we got into TestNextConnectionModeTryConnectResponse.onResponse with a null response.");
            onError(new Exception());
        }
    }

    @Override
    public void onError(Exception ex) {

        connectionManager.TestNextConnectionMode(tests, index + 1, isLocalNetworkAvailable, server, wakeOnLanSendTime, options, response);
    }

}