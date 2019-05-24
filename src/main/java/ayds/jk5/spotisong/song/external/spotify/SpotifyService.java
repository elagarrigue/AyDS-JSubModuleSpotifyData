package ayds.jk5.spotisong.song.external.spotify;

import ayds.jk5.spotisong.song.external.ExternalService;
import ayds.jk5.spotisong.song.external.SpotifySong;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

class SpotifyService implements ExternalService {

  private final Retrofit spotifyAPIRetrofit = new Retrofit.Builder()
          .baseUrl("https://api.spotify.com/v1/")
          .addConverterFactory(ScalarsConverterFactory.create())
          .build();

  private final SpotifyAPI spotifyAPI = spotifyAPIRetrofit.create(SpotifyAPI.class);

  private final SpotifyAccountService spotifyAccountService;
  private final SpotifyToSongResolver spotifyToSongResolver;

  SpotifyService(SpotifyAccountService spotifyAccountService, SpotifyToSongResolver spotifyToSongResolver) {
    this.spotifyAccountService = spotifyAccountService;
    this.spotifyToSongResolver = spotifyToSongResolver;
  }

  @Override
  public SpotifySong getSong(String query) throws Exception {
    Response<String> callResponse = getSongFromService(query);
    return spotifyToSongResolver.getSongFromExternalData(callResponse.body());
  }

  private Response<String> getSongFromService(String query) throws Exception {
    return spotifyAPI.getTrackInfo("Bearer " + spotifyAccountService.getToken(), query)
            .execute();
  }
}
