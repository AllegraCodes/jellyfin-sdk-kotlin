package mediabrowser.apiinteraction.playback;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.QueryStringDictionary;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.dlna.*;
import mediabrowser.model.dto.MediaSourceInfo;
import mediabrowser.model.mediainfo.LiveStreamRequest;
import mediabrowser.model.mediainfo.LiveStreamResponse;
import mediabrowser.model.mediainfo.MediaProtocol;
import mediabrowser.model.mediainfo.PlaybackInfoResponse;
import mediabrowser.model.session.PlayMethod;

import java.util.ArrayList;

/**
 * Created by Luke on 3/24/2015.
 */
public class GetPlaybackInfoResponse extends Response<PlaybackInfoResponse> {

    private PlaybackManager playbackManager;
    private ApiClient apiClient;
    private AudioOptions options;
    private Response<StreamInfo> response;
    private boolean isVideo;
    private Long startPositionTicks;

    public GetPlaybackInfoResponse(PlaybackManager playbackManager, ApiClient apiClient, AudioOptions options, Response<StreamInfo> response, boolean isVideo, Long startPositionTicks) {
        super(response);
        this.playbackManager = playbackManager;
        this.apiClient = apiClient;
        this.options = options;
        this.response = response;
        this.isVideo = isVideo;
        this.startPositionTicks = startPositionTicks;
    }

    @Override
    public void onResponse(final PlaybackInfoResponse playbackInfo) {

        if (playbackInfo.getErrorCode() != null){

            PlaybackException error = new PlaybackException();
            error.setErrorCode(playbackInfo.getErrorCode());
            response.onError(error);
            return;
        }

        MediaSourceInfo mediaSourceInfo = getOptimalMediaSource(playbackInfo.getMediaSources());

        if (mediaSourceInfo == null){
            playbackManager.SendResponse(response, null);
            return;
        }

        if (mediaSourceInfo.getRequiresOpening()){

            LiveStreamRequest request = new LiveStreamRequest(options);
            request.setUserId(apiClient.getCurrentUserId());
            request.setOpenToken(mediaSourceInfo.getOpenToken());
            request.setPlaySessionId(playbackInfo.getPlaySessionId());

            apiClient.OpenLiveStream(request, new Response<LiveStreamResponse>(response){

                @Override
                public void onResponse(LiveStreamResponse liveStreamResponse){

                    playbackInfo.getMediaSources().clear();
                    playbackInfo.getMediaSources().add(liveStreamResponse.getMediaSource());
                    onResponseInternal(playbackInfo, liveStreamResponse.getMediaSource());
                }

            });
            return;
        }

        onResponseInternal(playbackInfo, mediaSourceInfo);
    }

    private void onResponseInternal(PlaybackInfoResponse playbackInfo, MediaSourceInfo mediaSourceInfo) {

        if (playbackInfo.getErrorCode() != null){

            PlaybackException error = new PlaybackException();
            error.setErrorCode(playbackInfo.getErrorCode());
            response.onError(error);
            return;
        }

        final StreamInfo streamInfo;

        streamInfo = new StreamInfo();
        streamInfo.setItemId(options.getItemId());
        streamInfo.setMediaType(DlnaProfileType.Audio);

        streamInfo.setMediaSource(mediaSourceInfo);
        streamInfo.setRunTimeTicks(mediaSourceInfo.getRunTimeTicks());

        streamInfo.setContext(options.getContext());
        streamInfo.setItemId(options.getItemId());
        streamInfo.setDeviceId(apiClient.getDeviceId());
        streamInfo.setDeviceProfile(options.getProfile());
        streamInfo.setPlaySessionId(playbackInfo.getPlaySessionId());
        streamInfo.setAllMediaSources(playbackInfo.getMediaSources());
        streamInfo.setStartPositionTicks(startPositionTicks);

        if (options.getEnableDirectPlay() && mediaSourceInfo.getSupportsDirectPlay() && canDirectPlay(mediaSourceInfo)){
            streamInfo.setPlayMethod(PlayMethod.DirectPlay);
            streamInfo.setContainer(mediaSourceInfo.getContainer());
            streamInfo.setMediaUrl(mediaSourceInfo.getPath());
        } else if (options.getEnableDirectStream() && mediaSourceInfo.getSupportsDirectStream()){

            String outputContainer = mediaSourceInfo.getContainer();
            if (outputContainer == null){
                outputContainer = "";
            }
            outputContainer = outputContainer.toLowerCase();

            streamInfo.setPlayMethod(PlayMethod.DirectStream);
            streamInfo.setContainer(mediaSourceInfo.getContainer());

            QueryStringDictionary dict = new QueryStringDictionary();
            dict.put("Static", "true");
            dict.put("MediaSourceId", mediaSourceInfo.getId());
            dict.put("DeviceId", apiClient.getDeviceId());
            dict.put("api_key", apiClient.getAccessToken());

            if (mediaSourceInfo.getETag() != null && mediaSourceInfo.getETag().length() > 0){
                dict.put("Tag", mediaSourceInfo.getETag());
            }

            if (mediaSourceInfo.getLiveStreamId() != null && mediaSourceInfo.getLiveStreamId().length() > 0){
                dict.put("LiveStreamId", mediaSourceInfo.getLiveStreamId());
            }

            String handler = isVideo ? "Videos" : "Audio";
            String mediaUrl = apiClient.GetApiUrl(handler + "/"+options.getItemId()+"/stream." + outputContainer, dict);
            //mediaUrl += seekParam;

            streamInfo.setMediaUrl(mediaUrl);

        } else if (mediaSourceInfo.getSupportsTranscoding()){
            streamInfo.setPlayMethod(PlayMethod.Transcode);
            streamInfo.setContainer(mediaSourceInfo.getTranscodingContainer());
            streamInfo.setMediaUrl(apiClient.GetApiUrl(mediaSourceInfo.getTranscodingUrl()));
        }

        playbackManager.SendResponse(response, streamInfo);
    }

    private MediaSourceInfo getOptimalMediaSource(ArrayList<MediaSourceInfo> mediaSourceInfos){

        if (options.getEnableDirectPlay()){
            for (MediaSourceInfo mediaSourceInfo : mediaSourceInfos){
                if (canDirectPlay(mediaSourceInfo)){
                    return mediaSourceInfo;
                }
            }
        }

        if (options.getEnableDirectStream()){
            for (MediaSourceInfo mediaSourceInfo : mediaSourceInfos){
                if (mediaSourceInfo.getSupportsDirectStream()){
                    return mediaSourceInfo;
                }
            }
        }

        for (MediaSourceInfo mediaSourceInfo : mediaSourceInfos){
            if (mediaSourceInfo.getSupportsTranscoding()){
                return mediaSourceInfo;
            }
        }

        if (mediaSourceInfos.size() == 0){
            return null;
        }

        return mediaSourceInfos.get(0);
    }

    private boolean canDirectPlay(MediaSourceInfo mediaSourceInfo){

        if (mediaSourceInfo.getProtocol() == MediaProtocol.Http && mediaSourceInfo.getRequiredHttpHeaders().size() == 0) {

            if (!mediaSourceInfo.getSupportsDirectStream() && !mediaSourceInfo.getSupportsTranscoding()){
                // If this is the only way it can be played, then allow it
                return true;
            }

            if (mediaSourceInfo.getIsRemote()){
                return true;
            }

            return mediaSourceInfo.getPath().toLowerCase().replace("https", "http").indexOf(apiClient.getServerAddress().toLowerCase().replace("https", "http").substring(0, 14)) == 0;
        }

        return false;
    }
}
