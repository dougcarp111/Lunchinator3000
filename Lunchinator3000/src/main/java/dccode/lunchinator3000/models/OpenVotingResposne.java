package dccode.lunchinator3000.models;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Doug
 *
 */
public class OpenVotingResposne {

	private OpenVotingSuggestion suggestion;
	private List<OpenVotingChoice> choices = new ArrayList<OpenVotingChoice>();
	
	public OpenVotingResposne() {
		super();
	}
	
	// Getters and Setters
	public OpenVotingSuggestion getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(OpenVotingSuggestion suggestion) {
		this.suggestion = suggestion;
	}
	public List<OpenVotingChoice> getChoices() {
		return choices;
	}
	public void setChoices(List<OpenVotingChoice> choices) {
		this.choices = choices;
	}
	
}
