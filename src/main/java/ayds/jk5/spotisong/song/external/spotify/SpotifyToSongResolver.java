package ayds.jk5.spotisong.song.external.spotify;

import ayds.jk5.spotisong.song.external.SpotifySong;

public interface SpotifyToSongResolver {

  SpotifySong getSongFromExternalData(String serviceData) throws Exception;
}

