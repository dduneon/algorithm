package HackerRank.Match;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FootballMatch {

	public static void main(String[] args) throws Exception {
		FootballMatch match = new FootballMatch();
		System.out.println(match.totalGoals(2011, "Barcelona"));
		System.out.println(match.totalGoals(2014, "Chelsea"));
	}

	private static final String MATCH_URL = "https://jsonmock.hackerrank.com/api/football_matches";

	public int totalGoals(int year, String team) throws Exception {

		String team1Url = String.format(MATCH_URL + "?year=%d&team1=%s", year,
				URLEncoder.encode(team, "UTF-8"));
		String team2Url = String.format(MATCH_URL + "?year=%d&team2=%s", year,
				URLEncoder.encode(team, "UTF-8"));

		return getTeamGoals(team1Url, "team1", 1, 0) + getTeamGoals(team2Url, "team2", 1, 0);
	}

	private int getTeamGoals(String teamUrl, String teamtype, int page, int totalGoals)
			throws Exception {

		String response = getResponsePerPage(teamUrl, page);

		JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
		int totalPages = jsonResponse.get("total_pages").getAsInt();
		JsonArray data = jsonResponse.getAsJsonArray("data");
		for (JsonElement e : data) {
			totalGoals += e.getAsJsonObject().get(teamtype + "goals").getAsInt();
		}

		return totalPages == page ? totalGoals
				: getTeamGoals(teamUrl, teamtype, page + 1, totalGoals);
	}

	private String getResponsePerPage(String endpoint, int page) throws IOException {

		System.out.println(String.format(" URL: %s and page: %d", endpoint, page));

		URL url = new URL(endpoint + "&page=" + page);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.addRequestProperty("Content-Type", "application/json");

		int status = con.getResponseCode();
		if (status < 200 || status >= 300) {
			throw new IOException("Error in reading data with status:" + status);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String response;
		StringBuilder sb = new StringBuilder();
		while ((response = br.readLine()) != null) {
			sb.append(response);
		}

		br.close();
		con.disconnect();

		return sb.toString();
	}


}
