package ayds.jk5.spotisong.song.external;

public interface ExternalService {

  SpotifySong getSong(String query) throws Exception;
}
