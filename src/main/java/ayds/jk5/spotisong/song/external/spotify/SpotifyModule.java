package ayds.jk5.spotisong.song.external.spotify;

import ayds.jk5.spotisong.song.external.ExternalService;

public class SpotifyModule {

  private static SpotifyModule instance;

  private final ExternalService externalService;

  private SpotifyModule() {
    SpotifyToSongResolver spotifyToSongResolver = new JsonToSongResolver();
    SpotifyToTokenResolver spotifyToTokenResolver = new JsonToTokenResolver();
    SpotifyAccountService spotifyAccountService = new SpotifyAccountService(spotifyToTokenResolver);

    externalService = new SpotifyService(spotifyAccountService, spotifyToSongResolver);
  }

  public static SpotifyModule getInstance() {
    if (instance == null) {
      instance = new SpotifyModule();
    }
    return instance;
  }

  public ExternalService getExternalService() {
    return externalService;
  }
}
